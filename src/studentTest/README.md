# Student-written tests

Tests supplied with the module remain in `src/test/java`. Put tests that you
design yourself under `src/studentTest/java`, using directories that match the
Java package. For example, a test declared as `package intlists;` might be:

```text
src/studentTest/java/intlists/MyIntListTest.java
```

Run only your tests with:

```bash
./gradlew studentTest
```

Run the supplied and student-written suites together with:

```bash
./gradlew check
```

Student tests should:

- check behaviour from the documented contract rather than implementation
  details;
- include meaningful assertions and descriptive method names;
- pass for every correct implementation to which they apply;
- be deterministic and independent of test execution order; and
- avoid network access, interactive windows and machine-specific timing
  thresholds.

Do not edit supplied tests or copy them into this directory. Where a lab asks
you to add tests, files outside `src/studentTest/java` do not count as your
student-written tests. AutoGrader may later run these tests against correct and
deliberately faulty implementations, and you may be asked to explain one of
your tests in the mini-viva.
