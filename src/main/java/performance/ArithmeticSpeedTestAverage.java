package performance;

import java.util.Random;

public class ArithmeticSpeedTestAverage {
    public static void main(String[] args) {
        long x = 0;
        long startTime = System.nanoTime();
        long nReps = 10_000_000L;
        System.out.println("nReps = " + nReps);
        Random random = new Random();
        for (long i = 0; i < nReps; i++) {
            // run with either or both commented out
//            x+= random.nextInt(2);
            x++;
        }
        long endTime = System.nanoTime();

        System.out.println("Elapsed time: " + (endTime - startTime) / 1_000_000 + "ms");
        System.out.println(String.format("Average elapsed time: %.4f nano-seconds",
            ((double) (endTime - startTime)) / nReps));
        System.out.println("x = " + x);
    }
}
