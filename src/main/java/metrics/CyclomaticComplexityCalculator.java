package metrics;

import org.eclipse.jdt.core.dom.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class CyclomaticComplexityCalculator {

    public static void main(String[] args) {
        String sourceDirectory = "src/main/java/";
        String filePattern = args.length > 0 ? args[0] : "";

        System.out.println("File pattern: " + filePattern);
        processSourceDirectory(sourceDirectory, filePattern);
        System.exit(0);
    }

    public static void processSourceDirectory(String sourceDirectory, String filePattern) {
        File dir = new File(sourceDirectory);
        if (dir.exists() && dir.isDirectory()) {
            processDirectoryRecursively(dir, filePattern);
        } else {
            System.out.println("Invalid directory: " + sourceDirectory);
        }
    }

    private static void processDirectoryRecursively(File dir, String filePattern) {
        Map<String, Integer> classComplexities = new HashMap<>();
        List<Map.Entry<String, Integer>> allMethods = new ArrayList<>();

        for (File file : Objects.requireNonNull(dir.listFiles())) {
            if (file.isDirectory()) {
                processDirectoryRecursively(file, filePattern);
            } else if (file.getName().endsWith(".java") &&
                (filePattern.isEmpty() || file.getName().contains(filePattern))) {
                processJavaFile(file, classComplexities, allMethods);
            }
        }

        if (!classComplexities.isEmpty()) {
            System.out.println("\nClass-Level Cyclomatic Complexities:");
            classComplexities.forEach((className, complexity) ->
                System.out.println("Class: " + className + ", Total Complexity: " + complexity));
        }

        if (!allMethods.isEmpty()) {
            System.out.println("\nMethod Complexities:");
            allMethods.sort(Map.Entry.comparingByValue());
            allMethods.forEach(entry ->
                System.out.println("Method: " + entry.getKey() + ", Cyclomatic Complexity: " + entry.getValue()));
        }
    }

    private static void processJavaFile(File file, Map<String, Integer> classComplexities,
                                        List<Map.Entry<String, Integer>> allMethods) {
        try {
            String source = Files.readString(file.toPath());

            ASTParser parser = ASTParser.newParser(AST.JLS17);
            parser.setSource(source.toCharArray());
            parser.setKind(ASTParser.K_COMPILATION_UNIT);
            CompilationUnit cu = (CompilationUnit) parser.createAST(null);

            MethodCyclomaticComplexityVisitor visitor = new MethodCyclomaticComplexityVisitor();
            cu.accept(visitor);

            String className = file.getName().replace(".java", "");
            int totalComplexity = visitor.getMethodComplexities().values().stream().mapToInt(Integer::intValue).sum();
            if (totalComplexity > 0)
                classComplexities.put(className, classComplexities.getOrDefault(className, 0) + totalComplexity);

            visitor.getMethodComplexities().forEach((methodName, cc) ->
                allMethods.add(new AbstractMap.SimpleEntry<>(className + "." + methodName, cc)));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error processing file: " + file.getPath());
            e.printStackTrace();
        }
    }

    // --- Improved visitor ----------------------------------------------------
    static class MethodCyclomaticComplexityVisitor extends ASTVisitor {
        private final Map<String, Integer> methodComplexities = new HashMap<>();
        private int complexity;

        @Override
        public boolean visit(MethodDeclaration node) {
            complexity = 1; // base path
            if (node.getBody() != null) {
                node.getBody().accept(new DecisionCountingVisitor());
                methodComplexities.put(node.getName().toString(), complexity);
            }
            return false;
        }

        public Map<String, Integer> getMethodComplexities() {
            return methodComplexities;
        }

        // Inner visitor that counts all decision constructs
        private class DecisionCountingVisitor extends ASTVisitor {

            private void inc() { complexity++; }

            @Override
            public boolean visit(IfStatement node) { inc(); return super.visit(node); }

            @Override
            public boolean visit(ForStatement node) { inc(); return super.visit(node); }

            @Override
            public boolean visit(EnhancedForStatement node) { inc(); return super.visit(node); }

            @Override
            public boolean visit(WhileStatement node) { inc(); return super.visit(node); }

            @Override
            public boolean visit(DoStatement node) { inc(); return super.visit(node); }

            @Override
            public boolean visit(CatchClause node) { inc(); return super.visit(node); }

            @Override
            public boolean visit(ConditionalExpression node) { inc(); return super.visit(node); }

            @Override
            public boolean visit(AssertStatement node) { inc(); return super.visit(node); }

            @Override
            public boolean visit(SwitchStatement node) {
                // Count only the number of case/default labels
                for (Object st : node.statements()) {
                    if (st instanceof SwitchCase) inc();
                }
                return super.visit(node);
            }

            @Override
            public boolean visit(InfixExpression node) {
                // Count each && or || as an additional decision
                InfixExpression.Operator op = node.getOperator();
                if (op == InfixExpression.Operator.CONDITIONAL_AND || op == InfixExpression.Operator.CONDITIONAL_OR)
                    inc();
                return super.visit(node);
            }
        }
    }
}
