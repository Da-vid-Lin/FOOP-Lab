package intlists;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IntLinkedListCycleTest {
    @Test void emptyListHasNoCycle() {
        assertFalse(new IntLinkedList().containsCycle());
    }

    @Test void detectsASelfLoop() {
        IntLinkedList list = new IntLinkedList();
        list.head = new IntNode(1);
        list.head.next = list.head;
        assertTrue(list.containsCycle());
    }
}
