package blocks;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public final class BlockShapes {
    private BlockShapes() { }

    public record Cell(int x, int y) { }

    /** Immutable relative-cell shape. Empty shapes and duplicate cells are rejected. */
    public static final class Shape implements Iterable<Cell> {
        private final List<Cell> cells;
        private final Set<Cell> cellSet;

        public Shape(List<Cell> cells) {
            if (cells == null || cells.isEmpty()) {
                throw new IllegalArgumentException("A shape must contain at least one cell");
            }
            Set<Cell> unique = new LinkedHashSet<>(cells);
            if (unique.size() != cells.size()) {
                throw new IllegalArgumentException("A shape cannot contain duplicate cells");
            }
            this.cells = List.copyOf(unique);
            this.cellSet = Set.copyOf(unique);
        }

        public List<Cell> cells() { return cells; }
        public int size() { return cells.size(); }
        public Cell get(int index) { return cells.get(index); }
        public Stream<Cell> stream() { return cells.stream(); }
        @Override public Iterator<Cell> iterator() { return cells.iterator(); }
        @Override public boolean equals(Object o) { return o instanceof Shape s && cellSet.equals(s.cellSet); }
        @Override public int hashCode() { return cellSet.hashCode(); }
        @Override public String toString() { return cells.toString(); }
    }

    public record Piece(Shape shape, Cell origin) {
        public Piece {
            if (shape == null || origin == null) throw new NullPointerException();
        }

        public List<Cell> cells() {
            return shape.stream()
                    .map(cell -> new Cell(cell.x() + origin.x(), cell.y() + origin.y()))
                    .toList();
        }
    }

    public record PixelLoc(int x, int y) { }
    public enum SpriteState { IN_PLAY, IN_PALETTE, PLACED }

    public static final class Sprite {
        private final Shape shape;
        private int px;
        private int py;
        private SpriteState state = SpriteState.IN_PALETTE;

        public Sprite(Shape shape, int px, int py) {
            this.shape = shape;
            moveTo(px, py);
        }

        public Shape shape() { return shape; }
        public int px() { return px; }
        public int py() { return py; }
        public SpriteState state() { return state; }
        public void state(SpriteState state) { this.state = state; }
        public void moveTo(int px, int py) { this.px = px; this.py = py; }

        public boolean contains(PixelLoc point, int cellSize) {
            return shape.stream().anyMatch(cell -> {
                int cx = px + cell.x() * cellSize;
                int cy = py + cell.y() * cellSize;
                return cx <= point.x() && cy <= point.y()
                        && cx + cellSize > point.x() && cy + cellSize > point.y();
            });
        }

        public Piece snapToGrid(int margin, int cellSize) {
            int gx = Math.floorDiv(px - margin + cellSize / 2, cellSize);
            int gy = Math.floorDiv(py - margin + cellSize / 2, cellSize);
            return new Piece(shape, new Cell(gx, gy));
        }
    }

    public static final class ShapeSet {
        private final List<Shape> shapes = List.of(
                shape(0, 0),
                shape(0, 0, 1, 0),
                shape(0, 0, 1, 0, 2, 0),
                shape(0, 0, 0, 1, 1, 1),
                shape(0, 0, 1, 0, 1, 1),
                shape(0, 0, 1, 0, 1, 1, 2, 1),
                shape(0, 0, 1, 0, 1, 1, 2, 0),
                shape(0, 0, 1, 0, 2, 0, 3, 0),
                shape(0, 0, 0, 1, 0, 2, 0, 3)
        );

        public List<Shape> getShapes() { return shapes; }

        private static Shape shape(int... coordinates) {
            var cells = new java.util.ArrayList<Cell>();
            for (int i = 0; i < coordinates.length; i += 2) {
                cells.add(new Cell(coordinates[i], coordinates[i + 1]));
            }
            return new Shape(cells);
        }
    }
}
