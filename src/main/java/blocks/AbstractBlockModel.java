package blocks;

import blocks.BlockShapes.Cell;
import blocks.BlockShapes.Piece;
import blocks.BlockShapes.Shape;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Shared rules for both storage representations. Several methods are deliberately
 * incomplete student exercises; their contracts are specified in Lab 7.
 */
abstract class AbstractBlockModel implements ModelInterface {
    private final BoardSpec spec;
    private final BoardStorage storage;
    private final ScoringPolicy scoring;
    private int score;

    protected AbstractBlockModel(BoardSpec spec, BoardStorage storage, ScoringPolicy scoring) {
        this.spec = spec;
        this.storage = storage;
        this.scoring = scoring;
    }

    @Override public BoardSpec spec() { return spec; }

    @Override
    public PlacementPreview preview(Piece piece) {
        // TODO: return illegal for out-of-bounds, duplicate or occupied absolute cells.
        // For a legal move, also report every region completed by the prospective cells.
        return PlacementPreview.illegal();
    }

    @Override
    public MoveResult place(Piece piece) {
        // TODO: rejected moves must not mutate state. Accepted moves are added atomically,
        // all simultaneously completed regions are cleared, and scoring is applied once.
        return MoveResult.rejected(score);
    }

    @Override
    public boolean canPlaceAnywhere(Shape shape) {
        // TODO: search every board origin at which this shape might fit.
        return false;
    }

    @Override
    public BoardSnapshot snapshot() {
        return new BoardSnapshot(spec, storage.snapshot(), score);
    }

    protected boolean inBounds(Cell cell) {
        return cell.x() >= 0 && cell.x() < spec.width()
                && cell.y() >= 0 && cell.y() < spec.height();
    }

    protected boolean occupied(Cell cell) { return storage.contains(cell); }
    protected void occupy(Set<Cell> cells) { storage.addAll(cells); }
    protected void clear(Set<Cell> cells) { storage.removeAll(cells); }
    protected int score(Piece piece, List<Shape> regions) { return scoring.score(piece, regions); }
    protected void addScore(int delta) { score += delta; }

    protected Set<Cell> absoluteCells(Piece piece) {
        return new LinkedHashSet<>(piece.cells());
    }
}
