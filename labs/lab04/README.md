# Further OOP — Lab 4

## Serialization and reflection

### Gson

Complete the `HelloGson` and `HelloGsonRecord` round trips. Then study the
cyclic object example and implement a documented policy that prevents infinite
recursive serialization. Be ready to compare at least two possible policies.

### Reflection model

The Java reflection API exposes `Class`, `Field`, `Method`, `Constructor` and
`Type`. The course deliberately converts those objects into the standalone
records in `ReflectionData`, allowing writers and layout code to remain
independent of reflection.

Implement `ProcessClasses` in these stages:

1. Classify ordinary classes, abstract classes, interfaces, enums and records.
2. Extract fields, constructors and methods with generic type names,
   visibility, parameters and relevant modifiers.
3. Create distinct `EXTENDS`, `IMPLEMENTS` and `DEPENDENCY` links.
4. Discover dependencies in fields, return types and callable parameters.
5. Starting from seed classes, recursively include non-JDK dependencies while
   processing each class at most once.

Use qualified names as identities and simple names only for display. This
avoids silently merging unrelated classes that share a simple name.

`TypeWalker` demonstrates how concrete classes can be nested in parameterized,
array, wildcard and type-variable `Type` values. Standard-library types may
occur in displayed signatures but are not recursively added as diagram nodes.

The public tests illustrate only the first steps. Add tests for at least one
parameter dependency and one cyclic dependency graph.

