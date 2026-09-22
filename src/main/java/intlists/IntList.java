package intlists;

public interface IntList {
    // this non-generic interface
    // is used as a starting point
    boolean contains(int value);

    void append(int value);

    int length();

    /**
     * Returns the element at the specified index.
     * @param index position of the element to return (0-based)
     * @return the integer at the given index
     * @throws IndexOutOfBoundsException if index < 0 or index >= length()
     */
    int nth(int index);
}

