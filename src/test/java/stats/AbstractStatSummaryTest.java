package stats;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractStatSummaryTest {
    protected abstract StatSummary createSummary();

    @Test void newSummaryHasCountAndSumZero() {
        StatSummary summary = createSummary();
        assertEquals(0, summary.n());
        assertEquals(0.0, summary.sum());
    }

    @Test void computesMeanForAnOrdinarySample() {
        StatSummary summary = createSummary().add(List.of(1, 2, 6));
        assertEquals(3.0, summary.mean(), 1e-10);
    }

    @Test void computesSampleStandardDeviation() {
        StatSummary summary = createSummary().add(List.of(1, 2, 3));
        assertEquals(1.0, summary.standardDeviation(), 1e-10);
    }

    @Test void emptyMeanIsUndefined() {
        assertThrows(NotEnoughDataException.class, () -> createSummary().mean());
    }
}
