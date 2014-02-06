package org.dbbeans.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * This class contains static function to help with Dates manipulations in the context of a database application.
 * Only Gregorian calendar dates are supported.
 */
public class Dates {

    /**
     * Check if a date is correct.
     * @param day of the month (1-31)
     * @param month of the year (1-12)
     * @param year (1583+)
     * @return true if the date is correct, false otherwise.
     * @see Dates#isDateOK(String, String, String)
     */
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

    /**
     * Check if a date is correct.
     * @param day of the month (1-31)
     * @param month of the year (1-12)
     * @param year (1583+)
     * @return true if the date is correct, false otherwise.
     * @see Dates#isDateOK(int, int, int)
     */
    public static boolean isDateOK(final String day, final String month, final String year) {
        boolean result = false;
        try {
            result = isDateOK(Integer.valueOf(day), Integer.valueOf(month), Integer.valueOf(year));
        } catch (final NumberFormatException ex) {
            // result = false !
        }
        return result;
    }

    /**
     * Check if a time is correct. This function checks if the hours, minutes and seconds of a time are in
     * acceptable range, respectively 0-23, 0-59 and 0-59
     * @param hours of time to be checked.
     * @param minutes of time to be checked.
     * @param seconds of time to be checked.
     * @return true if time can be validated, false otherwise.
     * @see Dates#isTimeOK(String, String, String)
     */
    public static boolean isTimeOK(final int hours, final int minutes, final int seconds) {
        return !(hours < 0 || hours > 23 || minutes < 0 || minutes > 59 || seconds < 0 || seconds > 59);
    }

    /**
     * Check if a time is correct. This function checks if the hours, minutes and seconds of a time are in
     * acceptable range, respectively 0-23, 0-59 and 0-59.
     * @param hours of time to be checked.
     * @param minutes of time to be checked.
     * @param seconds of time to be checked.
     * @return true if time can be validated, false otherwise.
     * @see Dates#isTimeOK(int, int, int)
     */
    public static boolean isTimeOK(final String hours, final String minutes, final String seconds) {
        boolean result = false;
        try {
            result = isTimeOK(Integer.valueOf(hours), Integer.valueOf(minutes), Integer.valueOf(seconds));
        } catch (final NumberFormatException ex) {
            // result = false !
        }
        return result;
    }

    /**
     * Check if a time String is correctly formatted.
     * The time string must be comprised of hours, minutes and seconds. Precision beyond seconds is not supported.
     * The three parts of the time string must be between acceptable range for hours, minutes and seconds,
     * respectively 0-23, 0-59 and 0-59.
     * @param time string to be checked.
     * @param separator between the time string parts.
     * @return true if time string can be validated, false otherwise.
     */
    public static boolean isTimeOK(final String time, final String separator) {
        final String[] parts = time.split(separator);
        return parts.length == 3 && isTimeOK(parts[0], parts[1], parts[2]);
    }

    /**
     * Check if a date in the format YYMD is correct. That is a date in the format: 4-digits-year separator
     * 2-digits-month separator 2-digits-day-of-month.
     * @param date to be checked
     * @param separator character(s) used to separate the digits
     * @return true if the date is correct, false otherwise
     * @see Dates#isDMYYDateOK(String, String)
     * @see Dates#isMDYYDateOK(String, String)
     */
    public static boolean isYYMDDateOK(final String date, final String separator) {
        final String[] parts = date.split(separator);
        return parts.length == 3 && isDateOK(parts[2], parts[1], parts[0]);
    }

    /**
     * Check if a date in the format DMYY is correct. That is a date in the format: 2-digits-day-of-month separator
     * 2-digits-month separator 4-digits-year.
     * @param date to be checked
     * @param separator character(s) used to separate the digits
     * @return true if the date is correct, false otherwise
     * @see Dates#isYYMDDateOK(String, String)
     * @see Dates#isDMYYDateOK(String, String)
     */
    public static boolean isDMYYDateOK(final String date, final String separator) {
        final String[] parts = date.split(separator);
        return parts.length == 3 && isDateOK(parts[0], parts[1], parts[2]);
    }

    /**
     * Check if a date in the format DMYY is correct. That is a date in the format: 2-digits-month separator
     * 2-digits-day-of-month separator 4-digits-year.
     * @param date to be checked
     * @param separator character(s) used to separate the digits
     * @return true if the date is correct, false otherwise
     * @see Dates#isYYMDDateOK(String, String)
     * @see Dates#isDMYYDateOK(String, String)
     */
    public static boolean isMDYYDateOK(final String date, final String separator) {
        final String[] parts = date.split(separator);
        return parts.length == 3 && isDateOK(parts[1], parts[0], parts[2]);
    }
}
