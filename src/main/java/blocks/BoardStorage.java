package blocks;

import blocks.BlockShapes.Cell;
import java.util.Set;

/** Minimal storage strategy used by the shared game-rule implementation. */
interface BoardStorage {
    boolean contains(Cell cell);
    void addAll(Set<Cell> cells);
    void removeAll(Set<Cell> cells);
    Set<Cell> snapshot();
}
