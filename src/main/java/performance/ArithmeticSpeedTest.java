package performance;

public class ArithmeticSpeedTest {
    public static void main(String[] args) {
        int x = 0;
        long startTime = System.nanoTime();
        x++;
        long endTime = System.nanoTime();
        System.out.println(
            String.format("Elapsed time: %d nano-seconds", endTime - startTime));
        System.out.println(x);
    }
}
