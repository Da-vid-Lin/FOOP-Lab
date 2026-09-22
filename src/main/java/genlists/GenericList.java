package genlists;

public interface GenericList<T> extends Iterable<T> {
    boolean contains(T value);

    void append(T value);

    int length();

    /**
     * Returns the element at the specified index.
     * @param index position of the element to return (0-based)
     * @return the element at the given index
     * @throws IndexOutOfBoundsException if index < 0 or index >= length()
     */
    T nth(int index);

}
