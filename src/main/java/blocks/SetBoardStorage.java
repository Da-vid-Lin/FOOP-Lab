package blocks;

import blocks.BlockShapes.Cell;
import java.util.HashSet;
import java.util.Set;

final class SetBoardStorage implements BoardStorage {
    private final Set<Cell> occupied = new HashSet<>();
    public boolean contains(Cell cell) { return occupied.contains(cell); }
    public void addAll(Set<Cell> cells) { occupied.addAll(cells); }
    public void removeAll(Set<Cell> cells) { occupied.removeAll(cells); }
    public Set<Cell> snapshot() { return Set.copyOf(occupied); }
}
