package iterators;

import java.util.Iterator;

public record FibonacciRange(int start, int end) implements Iterable<Integer> {

    @Override
    public Iterator<Integer> iterator() {
        return new FibonacciRangeIterator(start, end);
    }

    private static class FibonacciRangeIterator implements Iterator<Integer> {
        private final int end;
        private int a = 0; // first fib
        private int b = 1; // second fib
        private Integer nextValue = null;

        public FibonacciRangeIterator(int start, int end) {
            this.end = end;
            // todo
        }

        @Override
        public boolean hasNext() {
            return nextValue != null;
        }

        @Override
        public Integer next() {
            // todo
            return null;
        }
    }

    public static void main(String[] args) {
        FibonacciRange fibs = new FibonacciRange(5, 50);
        for (int num : fibs) {
            System.out.println(num);
        }
    }
}
