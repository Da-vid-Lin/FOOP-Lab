package stats;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StringSelectorsTest {
    @Test void selectsLongestString() {
        Selector<String> selector = new StringSelectors().longestString();
        selector.add("cat");
        selector.add("giraffe");
        assertEquals("giraffe", selector.getSelection());
    }

    @Test void emptySelectorHasNoSelection() {
        assertNull(new StringSelectors().shortestString().getSelection());
    }
}
