# Further OOP — Lab 1

## References, object diagrams and integer lists

### Learning outcomes

- Build and test the repository with Gradle and Java 17.
- Reason about reference identity and object graphs.
- Use an interface with two substitutable implementations.
- Distinguish a behavioural contract from a collection of test examples.

## 1. Repository check

Run `hello.HelloWorld`, then run `HelloWorldTest`. Correct `getGreeting()` and
commit the change. Use `./gradlew test` before relying on an IDE-only result.

## 2. Object graph

Study `hello.ObjectRefs` before running it. Predict the output, then inspect the
objects at both debugger breakpoints. Save an object diagram as
`docs/lab01/ObjectDiagram.png`. Show object identity and every reference,
including `null` and cycles.

## 3. `IntList`

Complete `IntArrayList` and `IntLinkedList`. Both implement this contract:

- A new list has length zero.
- `append(v)` adds `v` after every existing element and increases length by one.
- `contains(v)` is true exactly when an element equal to `v` is present.
- `nth(i)` uses zero-based indexing and throws `IndexOutOfBoundsException` when
  `i < 0` or `i >= length()`.
- Duplicate and negative integer values are permitted.

For this lab the implementations may be inefficient. Lab 2 improves them.

The supplied tests contain representative examples, not every assessed case.
Write at least three additional tests of your own before completing the code.
Do not modify a supplied test merely to make an incorrect implementation pass.

## Reflection questions

1. Why can the same abstract test class exercise both implementations?
2. What representation invariants should each class maintain?
3. Which operations currently require time proportional to list length?

