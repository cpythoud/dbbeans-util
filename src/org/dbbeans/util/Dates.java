package org.dbbeans.util;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * This class contains static function to help with Dates manipulations in the context of a database application.
 * Only Gregorian calendar dates are supported.
 */
public class Dates {

    /**
     * Given a java.sql.Date, returns the day of month.
     * @param date, a java.sql.Date.
     * @return day of month.
     */
    public static int getDay(final Date date) {
        return Integer.valueOf(date.toString().substring(8, 10));
    }

    /**
     * Given a java.sql.Date, returns the month.
     * @param date, a java.sql.Date.
     * @return month.
     */
    public static int getMonth(final Date date) {
        return Integer.valueOf(date.toString().substring(5, 7));
    }

    /**
     * Given a java.sql.Date, returns the year.
     * @param date, a java.sql.Date.
     * @return year.
     */
    public static int getYear(final Date date) {
        return Integer.valueOf(date.toString().substring(0, 4));
    }

    /**
     * Returns a java.sql.Date initialized with the current date.
     * @return a java.sql.Date initialized with the current date.
     */
    public static Date getCurrentDate() {
        return new Date((new java.util.Date()).getTime());
    }

    /**
     * Returns a java.sql.Time initialized with the current time.
     * @return a java.sql.Time initialized with the current time.
     */
    public static Time getCurrentTime() {
        return new Time((new java.util.Date()).getTime());
    }

    /**
     * Returns the current year on the computer clock
     * @return the current year
     */
    public static int getCurrentYear() {
        return getYear(getCurrentDate());
    }

    /**
     * Returns the current month (1-12) on the computer clock
     * @return the current month
     */
    public static int getCurrentMonth() {
        return getMonth(getCurrentDate());
    }

    /**
     * Returns the current day of the month (1-31) on the computer clock
     * @return the current day of the month
     */
    public static int getCurrentDayOfMonth() {
        return getDay(getCurrentDate());
    }

    /**
     * Returns a java.sql.Timestamp initialized with the current date and time.
     * @return a java.sql.Timestamp initialized with the current date and time.
     */
    public static Timestamp getCurrentTimestamp() {
        return new Timestamp((new java.util.Date()).getTime());
    }

    /**
     * Returns a number that can be used as a timestamp. Useful for recording creation dates or generating
     * time dependant, unique, names. (For temporary files or directories for example.)
     * @return a long. Its first four digits represent the current year, the next two the current month, the next
     * two the current day of month, the next two the current hour, the next two the current minutes, the next
     * two the current seconds and the next three the current milliseconds.
     */
    public static long getMeaningfulTimeStamp() {
        return Long.valueOf(MTS_DATE_FORMAT.format(new java.util.Date()));
    }

    private static final DateFormat MTS_DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmmssSSS");

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
     * @see Dates#isShortTimeOK(String, String)
     */
    public static boolean isTimeOK(final String time, final String separator) {
        final String[] parts = time.split(separator);
        return parts.length == 3 && isTimeOK(parts[0], parts[1], parts[2]);
    }

    /**
     * Check if a time String, without seconds, is correctly formatted.
     * The time string must be comprised of hours and minutes only.
     * The two parts of the time string must be between acceptable range for hours and minutes,
     * respectively 0-23 and 0-59.
     * @param time string to be checked.
     * @param separator between the hours and minutes.
     * @return true if time string can be validated, false otherwise.
     * @see Dates#isTimeOK(String, String, String)
     */
    public static boolean isShortTimeOK(final String time, final String separator) {
        return isTimeOK(time + separator + "00", separator);
    }

    /**
     * Check if a date in the format YYMD is correct. That is a date in the format: 4-digits-year separator
     * 2-digits-month separator 2-digits-day-of-month.
     * @param date to be checked.
     * @param separator character(s) used to separate the digits.
     * @return true if the date is correct, false otherwise.
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
     * @param date to be checked.
     * @param separator character(s) used to separate the digits.
     * @return true if the date is correct, false otherwise.
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
     * @param date to be checked.
     * @param separator character(s) used to separate the digits.
     * @return true if the date is correct, false otherwise.
     * @see Dates#isYYMDDateOK(String, String)
     * @see Dates#isDMYYDateOK(String, String)
     */
    public static boolean isMDYYDateOK(final String date, final String separator) {
        final String[] parts = date.split(separator);
        return parts.length == 3 && isDateOK(parts[1], parts[0], parts[2]);
    }

    /**
     * Check if a timestamp, of which the date part is in the format YYMD, is correct.
     * @param timestamp to be checked.
     * @param dateSeparator to be used to separate the date elements (year, month, day).
     * @param timeSeparator to be used to separate the time elements (hours, minutes, seconds).
     * @return true if the timestamp is correct, false otherwise.
     * @see Dates#isYYMDTimestampOK(String, String, String, String)
     * @see Dates#isYYMDDateOK(String, String)
     * @see Dates#isTimeOK(String, String)
     */
    public static boolean isYYMDTimestampOK(final String timestamp, final String dateSeparator, final String timeSeparator) {
        return isYYMDTimestampOK(timestamp, dateSeparator, timeSeparator, "\\.");
    }

    /**
     * Check if a timestamp, of which the date part is in the format YYMD, is correct.
     * @param timestamp to be checked.
     * @param dateSeparator to be used to separate the date elements (year, month, day).
     * @param timeSeparator to be used to separate the time elements (hours, minutes, seconds).
     * @param millisecondsSeparator to be used to separate the milliseconds from the time. If you don't need
     *                              to check for milliseconds, use
     *                              {@link Dates#isYYMDTimestampOK(String, String, String)}.
     * @return true if the timestamp is correct, false otherwise.
     * @see Dates#isYYMDTimestampOK(String, String, String)
     * @see Dates#isYYMDDateOK(String, String)
     * @see Dates#isTimeOK(String, String)
     */
    public static boolean isYYMDTimestampOK(final String timestamp, final String dateSeparator, final String timeSeparator, final String millisecondsSeparator) {
        if (dateSeparator.equals(timeSeparator) || dateSeparator.equals(millisecondsSeparator) || timeSeparator.equals(millisecondsSeparator))
            throw new IllegalArgumentException("Separators must be distincts");

        String parts[] = timestamp.split(millisecondsSeparator);
        if (parts.length > 2)
            return false;
        final String milliseconds;
        if (parts.length == 1)
            milliseconds = "0";
        else
            milliseconds = parts[1];

        parts = parts[0].split("[\\s]+");
        if (parts.length != 2)
            return false;
        final String date = parts[0];
        final String time = parts[1];

        return isYYMDDateOK(date, dateSeparator) && isTimeOK(time, timeSeparator) && representsNumber(milliseconds);
    }

    private static boolean representsNumber(final String s) {
        return s.matches("[0-9]+");
    }

    /**
     * Check if a timestamp, of which the date part is in the format DMYY, is correct.
     * @param timestamp to be checked.
     * @param dateSeparator to be used to separate the date elements (year, month, day).
     * @param timeSeparator to be used to separate the time elements (hours, minutes, seconds).
     * @return true if the timestamp is correct, false otherwise.
     * @see Dates#isDMYYDateOK(String, String)
     * @see Dates#isTimeOK(String, String)
     */
    public static boolean isDMYYTimestampOK(final String timestamp, final String dateSeparator, final String timeSeparator) {
        final String[] parts = timestamp.split("[\\s]+");
        return parts.length == 2 && isDMYYDateOK(parts[0], dateSeparator) && isTimeOK(parts[1], timeSeparator);
    }

    /**
     * Check if a timestamp, of which the date part is in the format MDYY, is correct.
     * @param timestamp to be checked.
     * @param dateSeparator to be used to separate the date elements (year, month, day).
     * @param timeSeparator to be used to separate the time elements (hours, minutes, seconds).
     * @return true if the timestamp is correct, false otherwise.
     * @see Dates#isMDYYDateOK(String, String)
     * @see Dates#isTimeOK(String, String)
     */
    public static boolean isMDYYTimestampOK(final String timestamp, final String dateSeparator, final String timeSeparator) {
        final String[] parts = timestamp.split("[\\s]+");
        return parts.length == 2 && isMDYYDateOK(parts[0], dateSeparator) && isTimeOK(parts[1], timeSeparator);
    }

    /**
     * Transform a String in a {@link java.sql.Date} object. The separator between the date elements
     * (year, month, day in this order) must be specified.
     * @param string to be converted.
     * @param separator used to separate the time elements.
     * @return a Date object.
     */
    public static Date getDateFromYYMD(final String string, final String separator) {
        final String[] parts = string.split(separator);
        if (parts.length != 3)
            throw new IllegalArgumentException("Invalid format: must be  yyyy" + separator + "[m]m" + separator + "[d]d, received " + string);
        if (!isDateOK(parts[2], parts[1], parts[0]))
            throw new IllegalArgumentException("Submitted date (" + string + ") is invalid.");

        Calendar cal = new GregorianCalendar(Integer.valueOf(parts[0]), Integer.valueOf(parts[1]) - 1, Integer.valueOf(parts[2]));
        return new Date(cal.getTimeInMillis());
    }

    /**
     * Transform a String in a {@link java.sql.Date} object. The separator between the date elements
     * (day, month, year in this order) must be specified.
     * @param string to be converted.
     * @param separator used to separate the time elements.
     * @return a Date object.
     */
    public static Date getDateFromDMYY(final String string, final String separator) {
        final String[] parts = string.split(separator);
        if (parts.length != 3)
            throw new IllegalArgumentException("Invalid format: must be  [d]d" + separator + "[m]m" + separator + "yyyy, received " + string);
        if (!isDateOK(parts[0], parts[1], parts[2]))
            throw new IllegalArgumentException("Submitted date (" + string + ") is invalid.");

        Calendar cal = new GregorianCalendar(Integer.valueOf(parts[2]), Integer.valueOf(parts[1]) - 1, Integer.valueOf(parts[0]));
        return new Date(cal.getTimeInMillis());
    }

    /**
     * Transform a String in a {@link java.sql.Date} object. The separator between the date elements
     * (month, day, year in this order) must be specified.
     * @param string to be converted.
     * @param separator used to separate the time elements.
     * @return a Date object.
     */
    public static Date getDateFromMDYY(final String string, final String separator) {
        final String[] parts = string.split(separator);
        if (parts.length != 3)
            throw new IllegalArgumentException("Invalid format: must be  yyyy" + separator + "[d]d" + separator + "[m]m, received " + string);
        if (!isDateOK(parts[1], parts[0], parts[2]))
            throw new IllegalArgumentException("Submitted date (" + string + ") is invalid.");

        Calendar cal = new GregorianCalendar(Integer.valueOf(parts[2]), Integer.valueOf(parts[0]) - 1, Integer.valueOf(parts[1]));
        return new Date(cal.getTimeInMillis());
    }

    /**
     * Transform a String in a {@link java.sql.Time} object. The separator between the time elements
     * (hours, minutes, seconds) must be specified.
     * @param string to be converted.
     * @param separator used to separate the time elements.
     * @return a Time object.
     */
    public static Time getTimeFromString(final String string, final String separator) {
        final String[] parts = string.split(separator);
        if (parts.length != 3)
            throw new IllegalArgumentException("Invalid format: must be   [h]h" + separator + "[m]m" + separator + "[s]s, received " + string);
        if (!isTimeOK(parts[0], parts[1], parts[2]))
            throw new IllegalArgumentException("Submitted time (" + string + ") is invalid.");

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, Integer.valueOf(parts[0]));
        cal.set(Calendar.MINUTE, Integer.valueOf(parts[1]));
        cal.set(Calendar.SECOND, Integer.valueOf(parts[2]));

        return new Time(cal.getTimeInMillis());
    }

    /**
     * Transform a 'short' String, including only hours and minutes,
     * in a {@link java.sql.Time} object. The separator between hours and minutes must be specified.
     * @param string to be converted.
     * @param separator used to separate hours and minutes.
     * @return a Time object.
     */
    public static Time getTimeFromShortString(final String string, final String separator) {
        final String[] parts = string.split(separator);
        if (parts.length != 2)
            throw new IllegalArgumentException("Invalid format: must be   [h]h" + separator + "[m]m, received " + string);
        if (!isTimeOK(parts[0], parts[1], "00"))
            throw new IllegalArgumentException("Submitted time (" + string + ") is invalid.");

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, Integer.valueOf(parts[0]));
        cal.set(Calendar.MINUTE, Integer.valueOf(parts[1]));
        cal.set(Calendar.SECOND, 0);

        return new Time(cal.getTimeInMillis());
    }

    /**
     * Transform a String in a {@link java.sql.Timestamp} object. The separators between the date elements
     * (years, month, days) and time elements (hours, minutes, seconds) must be specified.
     * @param string to be converted.
     * @param dateSeparator to separate date elements.
     * @param timeSeparator to separate time elements.
     * @return a Timestamp object.
     */
    public static Timestamp getTimestampFromYYMD(final String string, final String dateSeparator, final String timeSeparator) {
        return getTimestampFromYYMD(string, dateSeparator, timeSeparator, "\\.");
    }

    /**
     * Transform a String in a {@link java.sql.Timestamp} object. The separators between the date elements
     * (years, month, days) and time elements (hours, minutes, seconds), and between time and milliseconds,
     * must be specified.
     * @param string to be converted.
     * @param dateSeparator to separate date elements.
     * @param timeSeparator to separate time elements.
     * @param millisecondsSeparator to separate time and milliseconds.
     * @return a Timestamp object.
     */
    public static Timestamp getTimestampFromYYMD(final String string, final String dateSeparator, final String timeSeparator, final String millisecondsSeparator) {
        final String[] milliParts = string.split(millisecondsSeparator);
        if (milliParts.length > 2)
            throw new IllegalArgumentException("Format invalide : doit être yyyy" + dateSeparator + "[m]m" + dateSeparator + "[d]d [h]h" + timeSeparator + "[m]m" + timeSeparator + "[s]s[" + millisecondsSeparator + "mmm], reçu " + string);
        final String milliseconds;
        if (milliParts.length == 1)
            milliseconds = "0";
        else
            milliseconds = milliParts[1];

        final String[] parts = milliParts[0].split("[\\s]+");
        if (parts.length != 2)
            throw new IllegalArgumentException("Format invalide : doit être yyyy" + dateSeparator + "[m]m" + dateSeparator + "[d]d [h]h" + timeSeparator + "[m]m" + timeSeparator + "[s]s[" + millisecondsSeparator + "mmm], reçu " + string);

        final String[] dateParts = parts[0].split(dateSeparator);
        if (dateParts.length != 3)
            throw new IllegalArgumentException("Format de date invalide : doit être yyyy" + dateSeparator + "[m]m" + dateSeparator + "[d]d, reçu " + parts[0]);
        if (!isDateOK(dateParts[2], dateParts[1], dateParts[0]))
            throw new IllegalArgumentException("La date fournie (" + parts[0] + ") est invalide !");

        final String[] timeParts = parts[1].split(timeSeparator);
        if (timeParts.length != 3)
            throw new IllegalArgumentException("Format d'heure invalide : doit être [h]h" + timeSeparator + "[m]m" + timeSeparator + "[s]s, reçu " + parts[1]);
        if (!isTimeOK(timeParts[0], timeParts[1], timeParts[2]))
            throw new IllegalArgumentException("L'heure fournie (" + string + ") est invalide !");

        Calendar cal = new GregorianCalendar(Integer.valueOf(dateParts[0]), Integer.valueOf(dateParts[1]) - 1, Integer.valueOf(dateParts[2]));
        cal.set(Calendar.HOUR_OF_DAY, Integer.valueOf(timeParts[0]));
        cal.set(Calendar.MINUTE, Integer.valueOf(timeParts[1]));
        cal.set(Calendar.SECOND, Integer.valueOf(timeParts[2]));
        cal.set(Calendar.MILLISECOND, Integer.valueOf(milliseconds));

        return new Timestamp(cal.getTimeInMillis());
    }

    /**
     * Compare two dates (java.sql.Date). If the first date is the same as the second date, returns 0.
     * If the first date is before the second date, returns a negative number (i.e., -1).
     * If the first date is after the second date, returns a positive number (i.e., 1).
     * This method should be used instead of the compareTo() method inherited from java.util.Date which does not
     * work as expected when the dates are the same (problem with the normalization of the time parts in
     * java.util.Date).
     * @param date1
     * @param date2
     * @return
     */
    public static int compare(final Date date1, final Date date2) {
        return date1.toString().compareTo(date2.toString());
    }

    /**
     * Checks if a date is between two other dates.
     * @param date to check
     * @param start of period
     * @param end of period
     * @return true if date is between start and end or if date == start or date == end, false otherwise
     */
    public static boolean isBetween(final Date date, final Date start, final Date end) {
        if (compare(start, end) > 0)  // end before start
            return compare(date, end) >= 0 && compare(date, start) <= 0;

        return compare(date, start) >= 0 && compare(date, end) <= 0;
    }
}
