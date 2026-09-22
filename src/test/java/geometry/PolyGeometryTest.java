package geometry;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PolyGeometryTest {
    private static ArrayList<Vec2d> square(double x0, double y0, double x1, double y1) {
        return new ArrayList<>(List.of(new Vec2d(x0, y0), new Vec2d(x1, y0),
                new Vec2d(x1, y1), new Vec2d(x0, y1)));
    }

    @Test void detectsPositiveAreaOverlap() {
        assertTrue(PolyGeometry.polygonsOverlap(square(0, 0, 2, 2), square(1, 1, 3, 3)));
    }

    @Test void touchingEdgesAreNotOverlap() {
        assertFalse(PolyGeometry.polygonsOverlap(square(0, 0, 2, 2), square(2, 0, 4, 2)));
    }

    @Test void containmentIncludesBoundary() {
        assertTrue(PolyGeometry.contains(square(0, 0, 2, 2), new Vec2d(0, 1)));
    }
}
