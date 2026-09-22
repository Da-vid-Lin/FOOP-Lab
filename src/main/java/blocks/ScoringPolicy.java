package blocks;

import blocks.BlockShapes.Piece;
import blocks.BlockShapes.Shape;
import java.util.List;

@FunctionalInterface
public interface ScoringPolicy {
    int score(Piece piece, List<Shape> completedRegions);

    static ScoringPolicy standard() {
        return (piece, regions) -> piece.cells().size() + 10 * regions.size();
    }
}
