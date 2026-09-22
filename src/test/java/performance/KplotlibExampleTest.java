package performance;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

class KplotlibExampleTest {
    @TempDir Path temporaryDirectory;

    @Test void writesANonEmptyHeadlessSvg() throws Exception {
        Path output = temporaryDirectory.resolve("example.svg");
        KplotlibExample.writeExample(output);
        assertTrue(Files.size(output) > 0);
    }
}
