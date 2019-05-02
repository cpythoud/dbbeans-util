package org.dbbeans.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class ListWrapper<T> {

    private final List<T> list = new ArrayList<T>();

    protected void initializeList(final List<T> list) {
        this.list.addAll(list);
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
