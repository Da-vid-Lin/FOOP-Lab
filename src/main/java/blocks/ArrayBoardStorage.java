package blocks;

import blocks.BlockShapes.Cell;
import java.util.HashSet;
import java.util.Set;

final class ArrayBoardStorage implements BoardStorage {
    private final boolean[][] occupied;

    ArrayBoardStorage(BoardSpec spec) {
        occupied = new boolean[spec.width()][spec.height()];
    }

    public boolean contains(Cell cell) { return occupied[cell.x()][cell.y()]; }

    public void addAll(Set<Cell> cells) {
        cells.forEach(cell -> occupied[cell.x()][cell.y()] = true);
    }

    public void removeAll(Set<Cell> cells) {
        cells.forEach(cell -> occupied[cell.x()][cell.y()] = false);
    }

    public Set<Cell> snapshot() {
        Set<Cell> result = new HashSet<>();
        for (int x = 0; x < occupied.length; x++) {
            for (int y = 0; y < occupied[x].length; y++) {
                if (occupied[x][y]) result.add(new Cell(x, y));
            }
        }
        return Set.copyOf(result);
    }
}
