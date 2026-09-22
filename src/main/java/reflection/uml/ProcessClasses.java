package reflection.uml;

import reflection.uml.ReflectionData.CallableData;
import reflection.uml.ReflectionData.ClassData;
import reflection.uml.ReflectionData.ClassType;
import reflection.uml.ReflectionData.DiagramData;
import reflection.uml.ReflectionData.FieldData;
import reflection.uml.ReflectionData.Link;
import reflection.uml.ReflectionData.LinkType;
import reflection.uml.ReflectionData.Visibility;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/** Converts Java reflection objects into the standalone UML data model. */
public class ProcessClasses {

    ClassType getClassType(Class<?> type) {
        // TODO: distinguish interfaces, abstract classes, enums, records and classes.
        return ClassType.CLASS;
    }

    List<FieldData> getFields(Class<?> type) {
        List<FieldData> fields = new ArrayList<>();
        for (Field field : type.getDeclaredFields()) {
            fields.add(new FieldData(
                    field.getName(),
                    typeName(field.getGenericType()),
                    visibility(field.getModifiers()),
                    Modifier.isStatic(field.getModifiers())
            ));
        }
        return List.copyOf(fields);
    }

    List<CallableData> getConstructors(Class<?> type) {
        // TODO: map declared constructors, their generic parameter types and visibility.
        return List.of();
    }

    List<CallableData> getMethods(Class<?> type) {
        // TODO: map declared methods, generic signatures, visibility and modifiers.
        return List.of();
    }

    Set<Link> getInheritanceLinks(Class<?> type, Set<Class<?>> includedClasses) {
        // TODO: add EXTENDS and IMPLEMENTS links only when both endpoints are included.
        return Set.of();
    }

    Set<Link> getDependencyLinks(Class<?> type, Set<Class<?>> includedClasses) {
        // TODO: inspect fields, constructor parameters, method parameters and return types.
        // TypeWalker below handles nested generic, array and wildcard Type values.
        return Set.of();
    }

    /**
     * Builds a diagram from seed classes and recursively discovered non-JDK dependencies.
     * Each class must be processed once even when the dependency graph contains cycles.
     */
    public DiagramData process(List<Class<?>> seedClasses) {
        // TODO: extend this queue with recursively discovered course classes.
        Queue<Class<?>> work = new ArrayDeque<>(seedClasses);
        Set<Class<?>> included = new HashSet<>(seedClasses);
        List<ClassData> classes = new ArrayList<>();
        Set<Link> links = new HashSet<>();

        while (!work.isEmpty()) {
            Class<?> type = work.remove();
            classes.add(new ClassData(
                    type.getName(),
                    type.getSimpleName(),
                    getClassType(type),
                    getFields(type),
                    getConstructors(type),
                    getMethods(type)
            ));
            links.addAll(getInheritanceLinks(type, included));
            links.addAll(getDependencyLinks(type, included));
        }
        return new DiagramData(classes, links);
    }

    static String typeName(Type type) {
        return type.getTypeName().replace("java.lang.", "");
    }

    static Visibility visibility(int modifiers) {
        if (Modifier.isPublic(modifiers)) return Visibility.PUBLIC;
        if (Modifier.isProtected(modifiers)) return Visibility.PROTECTED;
        if (Modifier.isPrivate(modifiers)) return Visibility.PRIVATE;
        return Visibility.PACKAGE;
    }

    static boolean isJdkClass(Class<?> type) {
        String packageName = type.getPackageName();
        return packageName.startsWith("java.") || packageName.startsWith("javax.")
                || packageName.startsWith("jdk.") || packageName.startsWith("sun.");
    }
}
