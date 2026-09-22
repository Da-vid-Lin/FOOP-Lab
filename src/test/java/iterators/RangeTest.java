package iterators;

import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.StreamSupport;
import static org.junit.jupiter.api.Assertions.*;

class RangeTest {
    @Test void usesInclusiveStartAndExclusiveEnd() {
        List<Integer> values = StreamSupport.stream(new Range(-1, 2).spliterator(), false).toList();
        assertEquals(List.of(-1, 0, 1), values);
    }

    @Test void exhaustedIteratorRejectsNext() {
        Iterator<Integer> iterator = new Range(0, 0).iterator();
        assertThrows(NoSuchElementException.class, iterator::next);
    }
}
