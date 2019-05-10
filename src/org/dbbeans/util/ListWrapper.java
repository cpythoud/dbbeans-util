package org.dbbeans.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class ListWrapper<T> {

    private final List<T> list = new ArrayList<T>();

    protected ListWrapper(final List<T> list) {
        this.list.addAll(list);
    }

    protected ListWrapper(final List<T> list,  final List<T> filteringList) {
        for (T item: list)
            if (filteringList.contains(item))
                this.list.add(item);
    }

    public List<T> getList() {
        return Collections.unmodifiableList(list);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}
