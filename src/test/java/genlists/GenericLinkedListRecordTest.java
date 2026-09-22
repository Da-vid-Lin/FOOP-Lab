package genlists;

import intlists.AbstractIntListTest;
import intlists.GenIntListWrapper;
import intlists.IntList;

public class GenericLinkedListRecordTest extends AbstractIntListTest {

    @Override
    protected IntList createList() {

        // return an GenIntListWrapper object that wraps around a GenericArrayList object
        return new GenIntListWrapper(new GenericLinkedListRecord<>());
    }
}

