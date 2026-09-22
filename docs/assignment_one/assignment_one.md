# Further OOP Assignment 1 — Autumn 2026

**Name:**  
**Student ID:**  
**Private GitHub repository:**

## Generative-AI policy

Generative-AI tools may be used to help you understand concepts and develop the
assignment. Their use is not penalised when declared honestly. You remain
responsible for testing the result and must be able to explain, evaluate and
modify all submitted work during the mini-viva. Automated AI detectors are not
used as evidence of misconduct.

## Declaration of generative-AI use

_Replace this line with the tools used, what you used them for, how you checked
their output, and one brief reflection on what you learned. If you used none,
state that explicitly._

## Part I — Foundational implementation and object diagram (40%)

- 37%: assessed behaviour from Labs 1–4 in `hello`, `intlists`, `genlists`,
  `iterators`, `stats` and `reflection.gson`.
- 3%: Lab 1 object diagram.

Assessment includes held-out tests. Marks are allocated by behaviour and
complexity category rather than raw test count. The written contracts in the
labs and APIs are authoritative.

Link the object diagram here:  
`![Object diagram](../lab01/ObjectDiagram.png)`

## Part II — Performance investigation with Kplotlib (20%)

Investigate a meaningful performance difference between course structures or
algorithms. Use repeatable Java measurement code and Kplotlib-generated plots.
Submit no more than three graphs and at most 300 words covering method,
observations, limitations and implications for Java/OOP design.

- Scope and experimental design: 4%
- Clear, informative Kplotlib graphs: 6%
- Interpretation of evidence: 7%
- Design implications: 3%

Place plot files in `docs/assignment_one/images/`.

## Part III — UML class-diagram generator (30%)

Starting from a list of seed `Class<?>` objects:

1. Produce complete `DiagramData`, recursively including relevant non-JDK
   dependencies found in inheritance, fields and callable signatures.
2. Correctly handle generic arguments, arrays, wildcards, cycles and classes
   sharing a simple name.
3. Render a UML diagram in which parents appear above children, boxes do not
   overlap, relationship types are distinct and member signatures are legible.
4. Submit a concise design/limitations discussion of at most 300 words.

- Reflection and model correctness on held-out cases: 10%
- Diagram layout and rendering: 15%
- Code quality, metrics and discussion: 5%

Include at least one generated diagram in `docs/assignment_one/images/`.

## Part IV — UML object-diagram generator (10%)

Extend the same pipeline to an object graph. Distinguish multiple instances of
the same class, preserve reference identity and terminate on cyclic graphs.
Include one generated example and brief implementation notes.

## Mini-viva

You must demonstrate and explain selected parts of your work and make a small
change when requested. Record the date and demonstrator here.

**Date:**  
**Demonstrator:**

## Checklist

- [ ] Completed code committed and pushed to the private repository.
- [ ] Public tests and `./gradlew build` run successfully.
- [ ] Object diagram linked.
- [ ] Kplotlib plots linked.
- [ ] UML class and object diagrams linked.
- [ ] Word limits observed.
- [ ] AI declaration completed.
- [ ] Mini-viva completed.
