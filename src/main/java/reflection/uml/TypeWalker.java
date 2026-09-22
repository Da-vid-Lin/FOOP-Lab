package reflection.uml;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.LinkedHashSet;
import java.util.Set;

/** Utility used by the reflection exercise to find concrete classes nested in a Type. */
final class TypeWalker {
    private TypeWalker() { }

    static Set<Class<?>> classesIn(Type type) {
        Set<Class<?>> result = new LinkedHashSet<>();
        collect(type, result);
        return Set.copyOf(result);
    }

    private static void collect(Type type, Set<Class<?>> result) {
        if (type instanceof Class<?> clazz) {
            if (clazz.isArray()) collect(clazz.getComponentType(), result);
            else result.add(clazz);
        } else if (type instanceof ParameterizedType parameterized) {
            collect(parameterized.getRawType(), result);
            for (Type argument : parameterized.getActualTypeArguments()) collect(argument, result);
        } else if (type instanceof GenericArrayType array) {
            collect(array.getGenericComponentType(), result);
        } else if (type instanceof WildcardType wildcard) {
            for (Type bound : wildcard.getUpperBounds()) collect(bound, result);
            for (Type bound : wildcard.getLowerBounds()) collect(bound, result);
        } else if (type instanceof TypeVariable<?> variable) {
            for (Type bound : variable.getBounds()) collect(bound, result);
        }
    }
}
