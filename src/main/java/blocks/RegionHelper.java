package blocks;

import blocks.BlockShapes.Cell;
import blocks.BlockShapes.Shape;

import java.util.ArrayList;
import java.util.List;

public final class RegionHelper {
    private final int width;
    private final int height;
    private final int subSize;

    public RegionHelper(int width, int height, int subSize) {
        this.width = width;
        this.height = height;
        this.subSize = subSize;
    }

    public BoardSpec boardSpec() {
        if (width <= 0 || height <= 0 || subSize <= 0
                || width % subSize != 0 || height % subSize != 0) {
            throw new IllegalArgumentException("Invalid board region dimensions");
        }
        return new BoardSpec(width, height, subSize, allRegions());
    }

    public List<Shape> allRegions() {
        List<Shape> regions = new ArrayList<>();
        for (int y = 0; y < height; y++) regions.add(row(y));
        for (int x = 0; x < width; x++) regions.add(column(x));
        for (int x = 0; x < width; x += subSize) {
            for (int y = 0; y < height; y += subSize) regions.add(subGrid(x, y));
        }
        return List.copyOf(regions);
    }

    private Shape row(int y) {
        List<Cell> cells = new ArrayList<>();
        for (int x = 0; x < width; x++) cells.add(new Cell(x, y));
        return new Shape(cells);
    }

    private Shape column(int x) {
        List<Cell> cells = new ArrayList<>();
        for (int y = 0; y < height; y++) cells.add(new Cell(x, y));
        return new Shape(cells);
    }

    private Shape subGrid(int x0, int y0) {
        List<Cell> cells = new ArrayList<>();
        for (int x = x0; x < x0 + subSize; x++) {
            for (int y = y0; y < y0 + subSize; y++) cells.add(new Cell(x, y));
        }
        return new Shape(cells);
    }
}
