package org.dbbeans.util;

import java.util.ArrayList;
import java.util.List;

public class Types {

    public static <T> List<T> getSubtypeList(final List<? extends T> supertypes) {
        return new ArrayList<T>(supertypes);
    }
}
