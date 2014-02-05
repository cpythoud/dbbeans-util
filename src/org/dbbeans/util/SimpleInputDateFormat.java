package org.dbbeans.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ...
 */
public class SimpleInputDateFormat {

    public static enum ElementOrder {
        YYMD, DMYY, MDYY
    }

    public SimpleInputDateFormat(final ElementOrder order, final String separator) {
        this.order = order;
        List<String> seps = new ArrayList<String>();
        seps.add(separator);
        this.separators = Collections.unmodifiableList(seps);
    }

    public SimpleInputDateFormat(final ElementOrder order, final List<String> separators) {
        this.order = order;
        List<String> seps = new ArrayList<String>();
        seps.addAll(separators);
        this.separators = Collections.unmodifiableList(seps);
    }

    public ElementOrder getOrder() {
        return order;
    }

    public List<String> getSeparators() {
        return separators;
    }

    public boolean validate(final String dateString) {
        for (String separator: separators) {
            switch (order) {
                case YYMD:
                    if (Dates.isYYMDDateOK(dateString, separator))
                        return true;
                    break;
                case DMYY:
                    if (Dates.isDMYYDateOK(dateString, separator))
                        return true;
                    break;
                case MDYY:
                    if (Dates.isMDYYDateOK(dateString, separator))
                        return true;
                    break;
                default:
                    throw new IllegalStateException("Cannot get here.");
            }
        }

        return false;
    }

    private final ElementOrder order;
    private final List<String> separators;
}
