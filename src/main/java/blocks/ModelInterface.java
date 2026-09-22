package blocks;

import blocks.BlockShapes.Cell;
import blocks.BlockShapes.Piece;
import blocks.BlockShapes.Shape;

import java.util.Collection;
import java.util.Set;

/** Public contract for a Blocks board. Implementations must not expose mutable state. */
public interface ModelInterface {
    BoardSpec spec();
    PlacementPreview preview(Piece piece);
    MoveResult place(Piece piece);
    boolean canPlaceAnywhere(Shape shape);
    BoardSnapshot snapshot();

    default boolean canPlace(Piece piece) { return preview(piece).legal(); }

    default boolean isGameOver(Collection<Shape> availableShapes) {
        return availableShapes.stream().noneMatch(this::canPlaceAnywhere);
    }

    default Set<Cell> getOccupiedCells() { return snapshot().occupiedCells(); }
    default int getScore() { return snapshot().score(); }
}
