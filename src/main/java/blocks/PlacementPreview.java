package blocks;

import blocks.BlockShapes.Cell;
import blocks.BlockShapes.Shape;
import java.util.List;
import java.util.Set;

public record PlacementPreview(boolean legal, Set<Cell> cells, List<Shape> completedRegions) {
    public PlacementPreview {
        cells = Set.copyOf(cells);
        completedRegions = List.copyOf(completedRegions);
    }

    public static PlacementPreview illegal() {
        return new PlacementPreview(false, Set.of(), List.of());
    }
}
