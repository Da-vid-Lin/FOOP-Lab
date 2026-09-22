package blocks;

import blocks.BlockShapes.Cell;
import blocks.BlockShapes.Piece;
import blocks.BlockShapes.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/** Public smoke tests. The assessment suite exercises further contracts from Lab 7. */
abstract class AbstractModelTest {
    private ModelInterface model;
    abstract ModelInterface createModel();

    @BeforeEach void setUp() { model = createModel(); }

    @Test void emptyBoardAcceptsAnInBoundsCell() {
        Piece piece = new Piece(new Shape(List.of(new Cell(0, 0))), new Cell(1, 1));
        assertTrue(model.preview(piece).legal());
    }

    @Test void acceptedMoveAppearsInSnapshot() {
        Piece piece = new Piece(new Shape(List.of(new Cell(0, 0))), new Cell(1, 1));
        MoveResult result = model.place(piece);
        assertTrue(result.accepted());
        assertTrue(model.snapshot().occupiedCells().contains(new Cell(1, 1)));
    }

    @Test void negativeOriginIsRejectedWithoutMutation() {
        Piece piece = new Piece(new Shape(List.of(new Cell(0, 0))), new Cell(-1, 0));
        assertFalse(model.place(piece).accepted());
        assertTrue(model.snapshot().occupiedCells().isEmpty());
    }

    @Test void boardUsesTheSuppliedSpecification() {
        assertEquals(9, model.spec().width());
        assertEquals(9, model.spec().height());
    }
}
