package blocks;

import blocks.BlockShapes.Shape;
import java.util.List;

/** Immutable board dimensions and completion regions. */
public record BoardSpec(int width, int height, int subSize, List<Shape> regions) {
    public BoardSpec {
        if (width <= 0 || height <= 0 || subSize <= 0) {
            throw new IllegalArgumentException("Board dimensions and sub-size must be positive");
        }
        if (width % subSize != 0 || height % subSize != 0) {
            throw new IllegalArgumentException("Sub-size must divide both board dimensions");
        }
        regions = List.copyOf(regions);
    }

    public static BoardSpec standard() { return new RegionHelper(9, 9, 3).boardSpec(); }
}
