package geometry;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Vec2dTest {
    @Test void computesMagnitude() { assertEquals(5, new Vec2d(3, 4).magnitude(), 1e-10); }
    @Test void addsVectors() { assertEquals(new Vec2d(4, 6), new Vec2d(1, 2).add(new Vec2d(3, 4))); }
    @Test void subtractsVectors() { assertEquals(new Vec2d(2, 2), new Vec2d(3, 4).subtract(new Vec2d(1, 2))); }
    @Test void computesDotProduct() { assertEquals(11, new Vec2d(1, 2).dot(new Vec2d(3, 4)), 1e-10); }
}
