package iterators;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.stream.StreamSupport;
import static org.junit.jupiter.api.Assertions.*;

class FibonacciRangeTest {
    @Test void returnsFibonacciValuesWithinHalfOpenRange() {
        assertEquals(List.of(5, 8, 13, 21, 34),
                StreamSupport.stream(new FibonacciRange(5, 50).spliterator(), false).toList());
    }

    @Test void rangeWithNoFibonacciValueIsEmpty() {
        assertFalse(new FibonacciRange(4, 5).iterator().hasNext());
    }
}
