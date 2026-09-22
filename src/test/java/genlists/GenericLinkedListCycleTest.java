package genlists;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GenericLinkedListCycleTest {
    @Test void emptyListHasNoCycle() {
        assertFalse(new GenericLinkedList<>().containsCycle());
    }

    @Test void detectsASelfLoop() {
        GenericLinkedList<String> list = new GenericLinkedList<>();
        list.head = new GenericNode<>("loop");
        list.head.next = list.head;
        assertTrue(list.containsCycle());
    }
}
