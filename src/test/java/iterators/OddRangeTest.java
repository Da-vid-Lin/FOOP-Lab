package iterators;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.stream.StreamSupport;
import static org.junit.jupiter.api.Assertions.*;

class OddRangeTest {
    @Test void returnsOddValuesInRange() {
        assertEquals(List.of(-3, -1, 1, 3),
                StreamSupport.stream(new OddRange(-4, 5).spliterator(), false).toList());
    }

    @Test void backwardsRangeIsEmpty() {
        assertFalse(new OddRange(5, 1).iterator().hasNext());
    }
}
