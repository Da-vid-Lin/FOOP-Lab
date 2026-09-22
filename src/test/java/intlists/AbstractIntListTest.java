package intlists;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/** Representative examples only; see the IntList contract and Lab 1. */
public abstract class AbstractIntListTest {
    protected abstract IntList createList();

    @Test void newListIsEmpty() {
        IntList list = createList();
        assertEquals(0, list.length());
        assertFalse(list.contains(7));
    }

    @Test void appendPreservesAnOrdinarySequence() {
        IntList list = createList();
        list.append(10);
        list.append(20);
        assertEquals(2, list.length());
        assertTrue(list.contains(20));
        assertEquals(10, list.nth(0));
        assertEquals(20, list.nth(1));
    }

    @Test void negativeIndexIsRejected() {
        assertThrows(IndexOutOfBoundsException.class, () -> createList().nth(-1));
    }
}
