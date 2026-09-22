package intlists;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntListEqualityTest {
    @Test
    void equalityUsesValuesAcrossRepresentations() {
        IntList array = new IntArrayList();
        IntList linked = new IntLinkedList();
        for (int value : new int[]{4, -2, 4}) {
            array.append(value);
            linked.append(value);
        }

        assertEquals(array, linked);
        assertEquals(linked, array);
        assertEquals(array.hashCode(), linked.hashCode());
    }
}
