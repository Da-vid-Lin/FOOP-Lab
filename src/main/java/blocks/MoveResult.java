package blocks;

import blocks.BlockShapes.Shape;
import java.util.List;

public record MoveResult(boolean accepted, List<Shape> clearedRegions, int scoreDelta, int totalScore) {
    public MoveResult { clearedRegions = List.copyOf(clearedRegions); }
    public static MoveResult rejected(int score) { return new MoveResult(false, List.of(), 0, score); }
}
