package reflection.uml;

import reflection.uml.ReflectionData.CallableData;
import reflection.uml.ReflectionData.ClassData;
import reflection.uml.ReflectionData.DiagramData;
import reflection.uml.ReflectionData.FieldData;
import reflection.uml.ReflectionData.Link;
import reflection.uml.ReflectionData.Visibility;

import java.util.stream.Collectors;

public final class MermaidWriter {
    public String writeMermaid(DiagramData diagram) {
        StringBuilder out = new StringBuilder("classDiagram\n");
        for (ClassData type : diagram.classes()) {
            String id = id(type.qualifiedName());
            out.append("class ").append(id).append("[\"").append(type.displayName()).append("\"] {\n");
            for (FieldData field : type.fields()) {
                out.append("  ").append(symbol(field.visibility()))
                        .append(field.isStatic() ? "$" : "")
                        .append(field.name()).append(" : ").append(field.type()).append("\n");
            }
            for (CallableData callable : type.constructors()) writeCallable(out, callable);
            for (CallableData callable : type.methods()) writeCallable(out, callable);
            out.append("}\n");
        }
        for (Link link : diagram.links()) {
            out.append(id(link.from())).append(switch (link.type()) {
                case EXTENDS -> " --|> ";
                case IMPLEMENTS -> " ..|> ";
                case DEPENDENCY -> " ..> ";
            }).append(id(link.to())).append("\n");
        }
        return out.toString();
    }

    private static void writeCallable(StringBuilder out, CallableData callable) {
        String parameters = callable.parameterTypes().stream().collect(Collectors.joining(", "));
        out.append("  ").append(symbol(callable.visibility()))
                .append(callable.isStatic() ? "$" : "")
                .append(callable.name()).append("(").append(parameters).append(")");
        if (callable.returnType() != null && !callable.returnType().isBlank()) {
            out.append(" : ").append(callable.returnType());
        }
        out.append("\n");
    }

    private static String symbol(Visibility visibility) {
        return switch (visibility) {
            case PUBLIC -> "+";
            case PRIVATE -> "-";
            case PROTECTED -> "#";
            case PACKAGE -> "~";
        };
    }

    private static String id(String qualifiedName) {
        return qualifiedName.replaceAll("[^A-Za-z0-9_]", "_");
    }
}
