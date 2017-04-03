package org.dbbeans.util;

/**
 * This class contains a few constants to help work with durations in milliseconds.
 */
public class MilliSeconds {

    public static final long ONE_SECOND =        1000L;
    public static final long ONE_MINUTE =       60000L;
    public static final long ONE_HOUR   =     3600000L;
    public static final long ONE_DAY    =    86400000L;
    public static final long ONE_WEEK   =   604800000L;
    public static final long ONE_MONTH  =  2592000000L;  // 30 days
    public static final long ONE_YEAR   = 31536000000L;  // 365 days

    public static final long DAYS_28 = 2419200000L;
    public static final long DAYS_29 = DAYS_28 + ONE_DAY;
    public static final long DAYS_30 = ONE_MONTH;
    public static final long DAYS_31 = DAYS_30 + ONE_DAY;
}
