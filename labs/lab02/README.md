# Further OOP — Lab 2

## Generics, iterators and linked structures

### 1. Generic selectors

Study `MostRecentObject<T>` and `Selector<T>`, then implement
`StringSelectors.longestString()` and `shortestString()`. Selection is based on
string length. When lengths tie, retaining the first value is acceptable and
must be consistent. An empty selector returns `null`.

### 2. Half-open ranges

Complete `OddRange` and `FibonacciRange`. All range classes use an inclusive
start and exclusive end. A backwards or empty range produces no values.

Every iterator must obey the Java `Iterator` contract:

- Repeated `hasNext()` calls do not advance it.
- `next()` advances by exactly one value.
- `next()` throws `NoSuchElementException` after exhaustion.
- Two iterators over the same iterable have independent state.

Account for negative inputs and integer overflow rather than relying solely on
the supplied examples.

### 3. Efficient generic lists

Complete `GenericArrayList<T>` and `GenericLinkedList<T>`.

- `append` must take amortized O(1) time for the array implementation.
- `append` must take O(1) time for the linked implementation.
- `contains` uses logical equality (`Objects.equals`), including a consistent
  policy for `null`.
- `nth` follows the same bounds contract as `IntList`.
- Iteration returns elements in append order.

Explain why `GenIntListWrapper` lets the generic implementations reuse the
integer-list contract tests.

### 4. Cycle detection

Implement `containsCycle()` in both mutable linked-list classes. It must detect
self-loops and cycles beginning anywhere in a list, terminate on cyclic input,
leave the structure unchanged, run in O(n) time, and use O(1) additional space.

The complexity requirements are assessed as well as the returned value. The
record-based linked list cannot normally contain a cycle because its links are
immutable; explain why this is a useful design distinction.

### 5. Immutable record list

Repair `GenericLinkedListRecord`. Observe the copying required by an immutable
node chain and compare its append complexity with the mutable linked list.

