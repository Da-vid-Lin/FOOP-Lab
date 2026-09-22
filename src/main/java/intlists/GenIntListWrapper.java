package intlists;

import genlists.GenericList;

public class GenIntListWrapper implements IntList {
    @Override
    public boolean contains(int value) {
        return list.contains(value);
    }

    @Override
    public void append(int value) {
        list.append(value);
    }

    @Override
    public int length() {
        return list.length();
    }

    @Override
    public int nth(int i) { return list.nth(i);}

    @Override
    public boolean equals(Object other) {
        // todo: compare through the IntList contract, not wrapped-object identity
        return super.equals(other);
    }

    @Override
    public int hashCode() {
        // todo: return the same ordered hash as every other IntList
        return super.hashCode();
    }

    // This is wraps a generic list as an IntList
    private final GenericList<Integer> list;

    public GenIntListWrapper(GenericList<Integer> list) {
        this.list = list;
    }
}
