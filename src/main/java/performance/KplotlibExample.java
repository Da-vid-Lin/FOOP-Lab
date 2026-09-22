package performance;

import sml.plotlib.core.Plot;

import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;

/** Minimal Java/headless example used in Lab 3. */
public final class KplotlibExample {
    private KplotlibExample() { }

    public static void main(String[] args) throws Exception {
        Path output = Path.of("build/plots/kplotlib-example.svg");
        writeExample(output);
        System.out.println("Plot saved to " + output);
    }

    public static void writeExample(Path output) throws Exception {
        List<Double> x = List.of(1.0, 2.0, 3.0, 4.0, 5.0);
        List<Double> linear = x.stream().map(value -> 2 * value).toList();
        List<Double> quadratic = x.stream().map(value -> value * value).toList();

        Plot plot = new Plot("Kplotlib from Java", "Input size", "Measured value");
        plot.addSeries("linear", x, linear, null);
        plot.addSeries("quadratic", x, quadratic, null);
        Path parent = output.toAbsolutePath().getParent();
        if (parent != null) Files.createDirectories(parent);
        plot.save(output.toString());
    }
}
