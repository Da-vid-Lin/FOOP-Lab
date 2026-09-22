package performance;

import genlists.GenericLinkedList;
import genlists.GenericArrayList;
import genlists.GenericLinkedListRecord;
import intlists.GenIntListWrapper;
import intlists.IntList;

record Range(int start, int end, int step) {
}

public class SingleListAppendEval {

    public static long elapsedTimeTrial(int n) {
        IntList list = new GenIntListWrapper(new GenericLinkedListRecord<>());
        long start = System.nanoTime();
        for (int i = 0; i < n; i++) {
            list.append(i);
        }
        long end = System.nanoTime();
        return end - start;
    }


    public static void main(String[] args) {
        Range range = new Range(100_000, 1_000_000, 100_000);
        // use an inclusive end range
        for (int i = range.start(); i <= range.end(); i += range.step()) {
            long t = elapsedTimeTrial(i);
            String resultString = String.format("%d\t %d", i, t);
            System.out.println(resultString);
        }
    }
}
