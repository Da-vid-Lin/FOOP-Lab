package genlists;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GenericListEqualityTest {
    @Test
    void equalityUsesLogicalElementValuesAcrossRepresentations() {
        GenericList<String> array = new GenericArrayList<>();
        GenericList<String> linked = new GenericLinkedList<>();
        array.append(new String("same value"));
        linked.append(new String("same value"));

        assertTrue(array.contains(new String("same value")));
        assertEquals(array, linked);
        assertEquals(linked, array);
        assertEquals(array.hashCode(), linked.hashCode());
    }
}
