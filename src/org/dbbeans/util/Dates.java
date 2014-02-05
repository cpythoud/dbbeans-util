package org.dbbeans.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * ...
 */
public class Dates {

    public static boolean isDateOK(final int day, final int month, final int year) {
        Calendar cal = new GregorianCalendar(year, month - 1, day);
        cal.setLenient(false);
        boolean result = true;
        try {
            cal.getTime();
        } catch (final IllegalArgumentException ex) {
            result = false;
        }
        return result;
    }

    public static boolean isDateOK(final String day, final String month, final String year) {
        boolean result = false;
        try {
            result = isDateOK(Integer.valueOf(day), Integer.valueOf(month), Integer.valueOf(year));
        } catch (final NumberFormatException ex) {
            // result = false !
        }
        return result;
    }

    public static boolean isYYMDDateOK(final String s, final String separator) {
        final String[] parts = s.split(separator);
        return parts.length == 3 && isDateOK(parts[2], parts[1], parts[0]);
    }

    public static boolean isDMYYDateOK(final String s, final String separator) {
        final String[] parts = s.split(separator);
        return parts.length == 3 && isDateOK(parts[0], parts[1], parts[2]);
    }

    public static boolean isMDYYDateOK(final String s, final String separator) {
        final String[] parts = s.split(separator);
        return parts.length == 3 && isDateOK(parts[1], parts[0], parts[2]);
    }
}
