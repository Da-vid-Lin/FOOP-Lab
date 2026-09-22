# Further OOP — Lab 3

## Performance, running statistics and Kplotlib

### Measurement principles

Study the examples in `performance`. Explain timer resolution, JVM warm-up,
JIT compilation, garbage collection, dead-code elimination and environmental
noise. Use `System.nanoTime()` for elapsed time.

Do not attempt to infer the cost of a fast operation from one invocation.
Include warm-up, run multiple trials, retain an observable result and report a
summary rather than a single best run.

### List experiment

Design one experiment capable of timing all five list implementations without
duplicating the timing logic. Configuration—list factories, input sizes,
warm-up and trial count—should be changed in one place.

Measure a clearly stated quantity. For example, if timing `n` appends to a new
list, explain why this is not the same as timing one append to a list of size
`n`.

### Kplotlib requirement

All plots for this module must be generated using Kplotlib from Java. Run
`performance.KplotlibExample`; it writes
`build/plots/kplotlib-example.svg` without requiring a desktop display.

Use `sml.plotlib.core.Plot`, add labelled series, label both axes and save SVG
or PNG output. Do not call `show()` in code intended for automated assessment.
Your performance plot should show uncertainty where it materially affects the
interpretation and should not hide faster series behind an unsuitable scale.

### `RunningSummary`

Implement `RunningSummary` so `add`, `n`, `sum`, `mean` and sample standard
deviation are O(1). `mean()` requires at least one value; sample standard
deviation requires at least two. Otherwise throw `NotEnoughDataException`.

Use a numerically stable running algorithm: held-out evaluation includes large
values with small differences, for which `sumOfSquares - sum*sum/n` can lose
substantial precision.

Record experimental notes in `docs/lab03/lab_notes_template.md`.

