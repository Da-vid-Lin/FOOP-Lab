# Further OOP — Lab 6

## Geometry, drawable shapes and Tangram

### Vector and shape contracts

Complete `Vec2d`, `Rectangle` and `Polygon`. Polygon area must be non-negative
for clockwise and counter-clockwise vertex order; perimeter includes the final
edge back to the first vertex. Shapes reject negative dimensions, and a polygon
requires at least three vertices.

Complete `DrawableCircle` and `DrawableRectangle`, treating each shape position
as its centre where documented. Keep geometry independent of rendering.

### Polygon predicates

`PolyGeometry.polygonsOverlap` uses the Separating Axis Theorem and therefore
accepts convex polygons. In this module:

- Boundary points count as contained.
- Positive-area intersection counts as overlap.
- Merely touching at an edge or vertex does not count as overlap.

These definitions matter: correctly placed Tangram tiles touch one another but
must not be reported as overlapping.

### Tangram

Complete `Tile` and `PuzzleModel`. Selection uses reverse drawing order so the
front-most containing tile is returned and brought to the front. A solved
puzzle has every tile inside the box and no pair with positive-area overlap.

Write your own `TileTest` and `PuzzleModelTest`; those files are intentionally
empty in the starter. Include boundary contact and overlapping z-order cases.

