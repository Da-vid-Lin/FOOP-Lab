package intlists;

public class IntLinkedList implements IntList {
    IntNode head;
    int len;

    public IntLinkedList() {
        head = null;
        len = 0;
    }

    public boolean contains(int value) {
        // todo: implement this properly
        return false;
    }

    public void append(int value) {
        // todo: implement this properly
        len += 2;
    }

    public int length() {
        return len;
    }

    @Override
    public int nth(int index) {
        // todo: provide a working version
        return 0;
    }

    /**
     * Reports whether following next references eventually revisits a node.
     * Required complexity: O(n) time and O(1) additional space. The list must
     * not be modified. See Lab 2; the implementation is intentionally omitted.
     */
    public boolean containsCycle() {
        // todo: implement without allocating a collection of visited nodes
        return false;
    }

    public static void main(String[] args) {
        IntLinkedList list = new IntLinkedList();
        list.append(1);
        list.append(2);
        list.append(3);
        System.out.println(list.contains(2));
        System.out.println(list.contains(4));
        System.out.println(list.length());
    }
}

// a IntNode for each element in LinkedList
class IntNode {
    int value;
    IntNode next;

    public IntNode(int value) {
        this.value = value;
        this.next = null;
    }
}
