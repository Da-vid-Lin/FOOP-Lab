# Further OOP — Lab 5

## UML output, layout and Java graphics

This lab consumes the `DiagramData` produced in Lab 4. Keep extraction,
layout and rendering as separate concerns.

### Mermaid writer

Complete and improve `MermaidWriter`. The output must distinguish inheritance,
interface implementation and dependency; show visibility and static members;
and include constructor and method parameter types. Test output properties
rather than comparing one enormous string character-for-character.

### Layout

Study `UMLLayout`. Parents must appear above subclasses and implementations.
Improve it so that boxes do not overlap and simple diagrams avoid crossed
connectors. Results must be deterministic for the same `DiagramData`.

The supplied layout is intentionally modest. You may replace the algorithm,
but the renderer must not need to know how positions were chosen.

### Java graphics

Complete `GreenComponent`, `BorderLayoutExample` and the Scribble application.
Keep drawing state in a model that can be tested without opening a window.
Swing applications must be created on the Event Dispatch Thread and close
cleanly.

Extend `DisplayUML` to draw class compartments and relationship connectors,
then export a PNG example that can be retained as possible coursework evidence.
Avoid tests that depend on exact pixels, installed fonts or a desktop display.
