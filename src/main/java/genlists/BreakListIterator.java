package genlists;

public class BreakListIterator {
    // try to break the iterator
    public static void main(String[] args) {
        GenericLinkedList<Integer> list = new GenericLinkedList<>();
        list.append(1);
        list.append(2);
        list.append(5);
        for (Integer i : list) {
            System.out.println(i);
        }
        System.out.println();
        var it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        // what will happen if we uncomment the next line?
//        System.out.println(it.next());
    }
}
