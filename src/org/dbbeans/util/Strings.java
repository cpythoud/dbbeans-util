package org.dbbeans.util;

import java.util.Arrays;
import java.util.List;

/**
 * This class contains static function to help with Strings manipulations.
 */
public class Strings {

    /**
     * Concatenate the Strings passed as parameters.
     * @param strings to be concatenated.
     * @return the result of the concatenation.
     */
    public static String concat(final String... strings) {
        return concat(Arrays.asList(strings));
    }

    /**
     * Takes a list of Strings and concatenate them together.
     * @param strings a list of Strings to be concatenated together.
     * @return the result of the concatenation.
     */
    public static String concat(final List<String> strings) {
        final StringBuilder buf = new StringBuilder();
        for (String s: strings)
            buf.append(s);
        return buf.toString();
    }

    /**
     * Concatenate Strings together, inserting a specified separator between them.
     * @param separator to be inserted between Strings.
     * @param strings to be concatenated.
     * @return the result of the concatenation.
     */
    public static String concatWithSeparator(final String separator, final String... strings) {
        return concatWithSeparator(separator, Arrays.asList(strings));
    }

    /**
     * Takes a list of Strings and concatenate them together, inserting a specified separator between them.
     * @param separator to be inserted between Strings.
     * @param strings a list of Strings to be concatenated together.
     * @return the result of the concatenation.
     */
    public static String concatWithSeparator(final String separator, final List<String> strings) {
        final StringBuilder buf = new StringBuilder();

        for (String s: strings) {
            buf.append(s);
            buf.append(separator);
        }
        buf.delete(buf.length() - separator.length(), buf.length());

        return buf.toString();
    }

}
