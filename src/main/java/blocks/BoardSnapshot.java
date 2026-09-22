package blocks;

import blocks.BlockShapes.Cell;
import java.util.Set;

/** Read-only state supplied to views and clients. */
public record BoardSnapshot(BoardSpec spec, Set<Cell> occupiedCells, int score) {
    public BoardSnapshot { occupiedCells = Set.copyOf(occupiedCells); }
}
