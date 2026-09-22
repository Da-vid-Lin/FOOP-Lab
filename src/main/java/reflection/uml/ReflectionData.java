package reflection.uml;

import java.util.List;
import java.util.Set;

/** Language-neutral data model produced by the reflection exercise. */
public final class ReflectionData {
    private ReflectionData() { }

    public enum LinkType { EXTENDS, IMPLEMENTS, DEPENDENCY }
    public enum ClassType { CLASS, ABSTRACT_CLASS, INTERFACE, ENUM, RECORD }
    public enum Visibility { PUBLIC, PRIVATE, PROTECTED, PACKAGE }

    public record FieldData(
            String name,
            String type,
            Visibility visibility,
            boolean isStatic
    ) { }

    public record CallableData(
            String name,
            List<String> parameterTypes,
            String returnType,
            Visibility visibility,
            boolean isStatic,
            boolean isAbstract
    ) {
        public CallableData {
            parameterTypes = List.copyOf(parameterTypes);
        }
    }

    public record ClassData(
            String qualifiedName,
            String displayName,
            ClassType classType,
            List<FieldData> fields,
            List<CallableData> constructors,
            List<CallableData> methods
    ) {
        public ClassData {
            fields = List.copyOf(fields);
            constructors = List.copyOf(constructors);
            methods = List.copyOf(methods);
        }
    }

    /** Link endpoints use qualified names; writers may display simple names. */
    public record Link(String from, String to, LinkType type) { }

    public record DiagramData(List<ClassData> classes, Set<Link> links) {
        public DiagramData {
            classes = List.copyOf(classes);
            links = Set.copyOf(links);
        }
    }
}
