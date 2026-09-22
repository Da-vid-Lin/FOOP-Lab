package graphics;

import org.junit.jupiter.api.Test;
import java.awt.geom.Point2D;
import static org.junit.jupiter.api.Assertions.*;

class LineModelTest {
    @Test void newModelHasNoDrawableSegments() {
        assertTrue(new LineModel().getLines().isEmpty());
    }

    @Test void twoPointsProduceOneSegment() {
        LineModel model = new LineModel();
        Point2D first = new Point2D.Double(1, 2);
        Point2D second = new Point2D.Double(3, 4);
        model.startNewLine(first);
        model.closeLine(second);
        assertEquals(1, model.getLines().size());
        assertEquals(first, model.getLines().get(0).from());
        assertEquals(second, model.getLines().get(0).to());
    }

    @Test void resetRemovesDrawing() {
        LineModel model = new LineModel();
        model.startNewLine(new Point2D.Double());
        model.closeLine(new Point2D.Double(1, 1));
        model.reset();
        assertTrue(model.getLines().isEmpty());
    }
}
