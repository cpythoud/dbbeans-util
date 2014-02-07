package org.dbbeans.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimpleInputTimestampFormat {

    public SimpleInputTimestampFormat(final SimpleInputDateFormat.ElementOrder order, final String dateSeparator, final String timeSeparator) {
        this.order = order;
        List<String> dateSeps = new ArrayList<String>();
        List<String> timeSeps = new ArrayList<String>();
        dateSeps.add(dateSeparator);
        timeSeps.add(timeSeparator);
        dateSeparators = Collections.unmodifiableList(dateSeps);
        timeSeparators = Collections.unmodifiableList(timeSeps);
    }

    public SimpleInputTimestampFormat(final SimpleInputDateFormat.ElementOrder order, final List<String> dateSeparators, final List<String> timeSeparators) {
        this.order = order;
        List<String> dateSeps = new ArrayList<String>();
        List<String> timeSeps = new ArrayList<String>();
        dateSeps.addAll(dateSeparators);
        timeSeps.addAll(timeSeparators);
        this.dateSeparators = Collections.unmodifiableList(dateSeps);
        this.timeSeparators = Collections.unmodifiableList(timeSeps);
    }

    public SimpleInputDateFormat.ElementOrder getOrder() {
        return order;
    }

    public List<String> getDateSeparators() {
        return dateSeparators;
    }

    public List<String> getTimeSeparators() {
        return timeSeparators;
    }

    public boolean validate(final String timestampString) {
        for (String dateSeparator: dateSeparators) {
            for (String timeSeparator: timeSeparators) {
                switch (order) {
                    case YYMD:
                        if (Dates.isYYMDTimestampOK(timestampString, dateSeparator, timeSeparator))
                            return true;
                        break;
                    case DMYY:
                        if (Dates.isDMYYTimestampOK(timestampString, dateSeparator, timeSeparator))
                            return true;
                        break;
                    case MDYY:
                        if (Dates.isMDYYTimestampOK(timestampString, dateSeparator, timeSeparator))
                            return true;
                        break;
                    default:
                        throw new IllegalStateException("Cannot get here.");
                }
            }
        }

        return false;
    }

    private final SimpleInputDateFormat.ElementOrder order;
    private final List<String> dateSeparators;
    private final List<String> timeSeparators;
}
