package genlists;

import java.util.Iterator;


// a IntNode for each element in LinkedList
class GenericNode<T> {
    T value;
    GenericNode<T> next;

    public GenericNode(T value) {
        this.value = value;
        this.next = null;
    }
}


public class GenericLinkedList<T> implements GenericList<T> {
    GenericNode<T> head;
    int len;

    public GenericLinkedList() {
        head = null;
        len = 0;
    }

    public boolean contains(T value) {
        // todo: implement this properly
        return false;
    }

    public void append(T value) {
        // todo: implement an efficient append method
    }

    public int length() {
        return len;
    }

    @Override
    public T nth(int index) {
        // todo: implement a working version
        return null;
    }

    /**
     * Reports whether following next references eventually revisits a node.
     * Required complexity: O(n) time and O(1) additional space.
     */
    public boolean containsCycle() {
        // todo: implement without allocating a collection of visited nodes
        return false;
    }

    @Override
    public boolean equals(Object other) {
        // todo: implement GenericList value equality, independent of representation
        return super.equals(other);
    }

    @Override
    public int hashCode() {
        // todo: return an ordered, null-safe hash consistent with equals
        return super.hashCode();
    }

    @Override
    public Iterator<T> iterator() {
        return new GenericLinkedListIterator<T>(this);
    }

    private static class GenericLinkedListIterator<T> implements Iterator<T> {
        private GenericNode<T> current;

        public GenericLinkedListIterator(GenericLinkedList<T> list) {
            current = list.head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T value = current.value;
            current = current.next;
            return value;
        }
    }

    public static void main(String[] args) {
        GenericLinkedList<Integer> list = new GenericLinkedList<>();
        list.append(1);
        list.append(2);
        list.append(3);
        for (Integer i : list) {
            System.out.println(i);
        }
    }
}
