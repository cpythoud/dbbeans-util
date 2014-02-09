package org.dbbeans.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * This class contains static function to help with Strings manipulations.
 */
public class Strings {

    /**
     * Check if a String is empty. A String is considered empty if it's null, its length is zero or it contains
     * only spaces and/or tabulations.
     * @param string to be checked.
     * @return true if string is empty as per the above definition, false otherwise.
     */
    public static boolean isEmpty(final String string) {
        return string == null || string.matches("\\s*");
    }

    /**
     * Capitalize a String.
     * If the first character of the String is lowercase, an identical String is returned with this first character converted to uppercase.
     * An identical String is returned otherwise.
     * @param string to be capitalized.
     * @return capitalized String.
     */
    public static String capitalize(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1, string.length());
    }

    /**
     * Uncapitalize a String.
     * If the first character of the String is uppercase, an identical String is returned with this first character converted to lowercase.
     * An identical String is returned otherwise.
     * @param string to be uncapitalized.
     * @return uncapitalized String.
     */
    public static String uncapitalize(String string) {
        return string.substring(0, 1).toLowerCase() + string.substring(1, string.length());
    }

    /**
     * Camelize a String by suppressing underscores and capitalizing the following letter.
     * Given a String of the form 'some_kind_of_string', this function returns a String of the form 'someKindOfString'.
     * This function is mostly used in programs that generate or manipulate source code.
     * @param string to be camelized.
     * @return camelizedString.
     * @throws java.lang.IllegalArgumentException if the String contains anything but alphanumeric ASCII characters, or
     * the String starts or ends with an underscore characters, or the String contains two consecutive underscore
     * characters.
     */
    public static String camelize(final String string) {
        if (!string.matches("^[a-zA-Z0-9_]+$"))
            throw new IllegalArgumentException("Illegal identifier character");
        if (string.startsWith("_"))
            throw new IllegalArgumentException("String cannot start with underscore character");
        if (string.contains("__"))
            throw new IllegalArgumentException("String cannot contain two consecutive underscore characters");
        if (string.endsWith("_"))
            throw new IllegalArgumentException("String cannot end with underscore character");
        String[] parts = string.split("_");
        StringBuilder buf = new StringBuilder();
        for (String part: parts)
            buf.append(capitalize(part.toLowerCase()));
        return buf.toString();
    }

    /**
     * Uncamelize a String by transforming any uppercase letter to its lowercase equivalent and preceding it by an
     * underscore.
     * Given a String of the form 'someKindOfString', this function returns a String of the form 'some_kind_of_string'.
     * This function is mostly used in programs that generate or manipulate source code.
     * @param string to be uncamelized.
     * @return uncamelized String.
     * @throws java.lang.IllegalArgumentException if the String contains anything but alphanumeric ASCII characters.
     */
    public static String uncamelize(final String string) {
        if (!string.matches("^[a-zA-Z0-9]+$"))
            throw new IllegalArgumentException("Illegal identifier character");
        List<String> parts = new ArrayList<String>();
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (i > 0 && c >= 'A' && c <= 'Z') {
                parts.add(buf.toString());
                buf = new StringBuilder();
            }
            buf.append(c);
        }
        if (buf.length() > 0)
            parts.add(buf.toString());
        buf = new StringBuilder();
        for (int i = 0; i < parts.size() - 1; i++) {
            buf.append(parts.get(i));
            buf.append('_');
        }
        buf.append(parts.get(parts.size() - 1));
        return buf.toString().toLowerCase();
    }

    /**
     * Count how many time a certain substring appears inside an other string.
     * @param string to be parsed for occurrences of the substring.
     * @param substring which occurrences are to be counted.
     * @return the number of occurrences of <code>substring</code> in <code>string</code>.
     */
    public static int occurrenceCount(final String string, final String substring) {
        int count = 0;
        int index = 0;
        while (string.indexOf(substring, index) != -1) {
            count++;
            index = string.indexOf(substring, index) + 1;
            if (index > string.length())
                break;
        }
        return count;
    }

    /**
     * Returns all the letters in a given String as an array of Strings, each String representing a letter.
     * The concept of letter is used loosely here to refer to code points, but represented as Strings and not ints.
     * @param string which letters should be enumerated.
     * @return list of <code>string</code> letters.
     */
    public static List<String> toLetterList(final String string) {
        List<String> letterList = new ArrayList<String>();

        for (int i = 0; i < string.length(); i++)
            letterList.add(string.substring(i, i + 1));

        return letterList;
    }

    /**
     * Replace part of a String by another String.
     * @param content the String to altered.
     * @param target the part of the String to be replaced.
     * @param replacement the String to use as a replacement.
     * @return a new String identical to <code>content</code> except where <code>target</code> is replaced by
     * <code>replacement</code>.
     * @see Strings#replaceMany(String, java.util.Map)
     */
    public static String replace(final String content, final String target, final String replacement) {
        CharSequence a	= target.subSequence(0, target.length());
        CharSequence b	= replacement.subSequence(0, replacement.length());
        return content.replace(a, b);
    }

    /**
     * Replace parts of a String by other Strings.
     * @param content the String to altered.
     * @param replacements a Map of parts to be replaced with their replacement.
     * @return a new String identical to <code>content</code> except where parts of it have been replaced according
     * to the <code>replacements</code> Map.
     * @see Strings#replace(String, String, String)
     */
    public static String replaceMany(final String content, Map<String, String> replacements) {
        String result = content;
        for (String target: replacements.keySet())
            result = replace(result, target, replacements.get(target));
        return result;
    }

    /**
     * Checks if a String is quoted.
     * <br/>This function checks if the String starts and ends with a quote character. It also checks if any quote
     * character inside the String is properly escaped, i.e., preceded by a '\' character.
     * @param string to be checked.
     * @param openingQuote the character to be used as opening quote.
     * @param closingQuote the character to be used as closing quote.
     * @return true if the String is enclosed in quotes, false otherwise.
     * @throws java.lang.NullPointerException if <code>string</code> is <code>null</code>.
     * @see Strings#isQuoted(String)
     */
    public static boolean isQuoted(final String string, final int openingQuote, final int closingQuote) {
        if (string == null)
            throw new NullPointerException("String to examine cannot be null.");
        if (string.length() < 2)
            return false;

        if (!(string.indexOf(openingQuote) == 0 && string.lastIndexOf(closingQuote) == string.length() - 1 && string.codePointAt(string.length() - 2) != '\\'))
            return false;

        if (openingQuote == closingQuote) {
            if (!quotingCharsEscapedOK(string, openingQuote))
                return false;
        } else {
            if (!quotingCharsEscapedOK(string, openingQuote))
                return false;
            if (!quotingCharsEscapedOK(string, closingQuote))
                return false;
        }

        return true;
    }

    private static boolean quotingCharsEscapedOK(final String s, final int quotingChar) {
        for (int i = 1; i < s.length() - 1; i++)
            if (s.codePointAt(i) == quotingChar && s.codePointAt(i - 1) != '\\')
                return false;

        return true;
    }

    /**
     * Checks if a String is quoted.
     * <br/>This function checks if the String starts and ends with a quote character ("). It also checks if any quote
     * character inside the String is properly escaped, i.e., preceded by a '\' character (\").
     * @param string to be checked.
     * @return true if the String is enclosed in quotes ("), false otherwise.
     * @throws java.lang.NullPointerException if <code>string</code> is <code>null</code>.
     * @see Strings#isQuoted(String, int, int)
     */
    public static boolean isQuoted(final String string) {
        return isQuoted(string, '"', '"');
    }

    /**
     * Insert quotes around a String.
     * <br/>This function insert the specified quote characters around a String. It also escape any such character
     * found inside the String by having preceded by the character '\'.
     * @param string to quote.
     * @param openingChar to use for the opening quote character.
     * @param closingChar to use for the closing quote character.
     * @return the quoted String
     * @throws java.lang.NullPointerException if <code>string</code> is <code>null</code>.
     * @see Strings#quickQuote(String, String, String)
     * @see Strings#quickQuote(String)
     */
    public static String quote(final String string, final int openingChar, final int closingChar) {
        if (string == null)
            throw new NullPointerException("String to quote cannot be null.");

        int c;
        int l = string.length();
        StringBuilder buf = new StringBuilder();

        buf.appendCodePoint(openingChar);
        for (int i = 0; i < l; i++) {
            c = string.codePointAt(i);
            if (c == openingChar || c == closingChar)
                buf.append("\\");
            buf.appendCodePoint(c);
        }
        buf.appendCodePoint(closingChar);

        return buf.toString();
    }

    /**
     * Insert quotes around a String.
     * <br/>This function insert the specified quote characters around a String. It assumes those characters are not
     * present inside the String and makes no attempt to escape them. If you expect quoting characters to be present
     * inside the String and need them escaped, you should use {@link Strings#quote(String, int, int)} instead.
     * @param string to quote.
     * @param openingQuote to use for the opening quote character(s).
     * @param closingQuote to use for the closing quote character(s).
     * @return the quoted String
     * @throws java.lang.NullPointerException if <code>string</code> is <code>null</code>.
     * @see Strings#quote(String, int, int)
     * @see Strings#quickQuote(String)
     */
    public static String quickQuote(final String string, final String openingQuote, final String closingQuote) {
        return openingQuote + string + closingQuote;
    }

    /**
     * Insert quotes (") around a String.
     * <br/>This function inserts quotes (") around a String. It assumes there are no quotes (") present
     * inside the String and makes no attempt to escape them. If you expect quotes to be present inside the
     * String and need them escaped, you should use {@link Strings#quote(String, int, int)} instead.
     * @param string to quote.
     * @return the quoted String
     * @throws java.lang.NullPointerException if <code>string</code> is <code>null</code>.
     * @see Strings#quote(String, int, int)
     * @see Strings#quickQuote(String, String, String)
     */
    public static String quickQuote(final String string) {
        return quickQuote(string, "\"", "\"");
    }

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
