package org.dbbeans.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ...
 */
public class SimpleInputTimeFormat {

    public SimpleInputTimeFormat(final String separator) {
        List<String> seps = new ArrayList<String>();
        seps.add(separator);
        this.separators = Collections.unmodifiableList(seps);
    }

    public SimpleInputTimeFormat(final List<String> separators) {
        List<String> seps = new ArrayList<String>();
        seps.addAll(separators);
        this.separators = Collections.unmodifiableList(seps);
    }

    public List<String> getSeparators() {
        return separators;
    }

    public boolean validate(final String timeString) {
        for (String separator: separators) {
            if (Dates.isTimeOK(timeString, separator))
                return true;
        }

        return false;
    }

    private final List<String> separators;
}
