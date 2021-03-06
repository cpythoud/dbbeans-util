package org.dbbeans.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
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
     * Transforms a String in an int value. If the String is null or cannot be converted to an int, returns 0.
     * @param string to be converted.
     * @return the int value represented by the String or 0 if the value cannot be extracted.
     */
    public static int getIntVal(final String string) {
        int val = 0;
        try {
            val = Integer.valueOf(string);
        } catch (final NumberFormatException nfex) {
            // val = 0 !
        }
        return val;
    }

    /**
     * Transforms a String in a long value. If the String is null or cannot be converted to a long, returns 0.
     * @param string to be converted.
     * @return the long value represented by the String or 0 if the value cannot be extracted.
     */
    public static long getLongVal(final String string) {
        long val = 0;
        try {
            val = Long.valueOf(string);
        } catch (final NumberFormatException nfex) {
            // val = 0 !
        }
        return val;
    }

    /**
     * Transforms a String in a float value. If the String is null or cannot be converted to a float, returns 0.
     * @param string to be converted.
     * @return the float value represented by the String or 0 if the value cannot be extracted.
     */
    public static float getFloatVal(final String string) {
        float val = 0.0f;
        try {
            val = Float.valueOf(string);
        } catch (final NumberFormatException nfex) {
            // val = 0 !
        }
        return val;
    }

    /**
     * Transforms a String in a double value. If the String is null or cannot be converted to a double, returns 0.
     * @param string to be converted.
     * @return the double value represented by the String or 0 if the value cannot be extracted.
     */
    public static double getDoubleVal(final String string) {
        double val = 0.0d;
        try {
            val = Double.valueOf(string);
        } catch (final NumberFormatException nfex) {
            // val = 0 !
        }
        return val;
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
     * Scan a String for a pair of delimiters and returns all occurences of substrings between these delimiters.
     * @param string to be scanned.
     * @param startDelimiter, can be identical to endDelimiter or different.
     * @param endDelimiter, can be identical to startDelimiter of different.
     * @return a list of the substrings that can be found in the passed string between the specified delimiters.
     * @throws IllegalArgumentException if the start delimiter is not closed (i.e., there is no end delimiter)
     * or if a second start delimiter appears before an end delimiter (this restriction does of course not
     * apply if start and end delimiter are identical).
     */
    public static List<String> extractBetweenDelimiters(final String string, final String startDelimiter, final String endDelimiter) {
        final List<String> results = new ArrayList<String>();

        int index = 0;
        while (index < string.length()) {
            final int startIdx = string.indexOf(startDelimiter, index);
            if (startIdx == -1)
                break;

            final int endIdx = string.indexOf(endDelimiter, startIdx + startDelimiter.length());
            if (endIdx == -1)
                throw new IllegalArgumentException("Missing corresponding end delimiter in String.");

            final String result = string.substring(startIdx + startDelimiter.length(), endIdx);
            if (result.contains(startDelimiter))
                throw new IllegalArgumentException("Nested delimiters not allowed.");
            results.add(result);
            index = endIdx + endDelimiter.length();
        }

        return results;
    }

    /**
     * Scan a String for a pair of delimiters and returns all occurences of substrings between these delimiters.
     * This functions works like {@link #extractBetweenDelimiters(String, String, String)}, but never throws
     * an IllegalArgumentException if there is a problem with the delimiter. This function can be useful to
     * process user input in some cases, but in general {@link #extractBetweenDelimiters(String, String, String)}
     * should be preferred.
     * @param string to be scanned.
     * @param startDelimiter, can be identical to endDelimiter or different.
     * @param endDelimiter, can be identical to startDelimiter of different.
     * @return a list of the substrings that can be found in the passed string between the specified delimiters.
     * @see #extractBetweenDelimiters(String, String, String)
     */
    public static List<String> lenientExtractBetweenDelimiters(
            final String string,
            final String startDelimiter,
            final String endDelimiter)
    {
        final List<String> results = new ArrayList<String>();

        int index = 0;
        while (index < string.length()) {
            final int startIdx = string.indexOf(startDelimiter, index);
            if (startIdx == -1)
                break;

            final int endIdx = string.indexOf(endDelimiter, startIdx + startDelimiter.length());
            if (endIdx == -1)
                break;

            final String result = string.substring(startIdx + startDelimiter.length(), endIdx);
            index = endIdx + endDelimiter.length();
            if (result.contains(startDelimiter))
                continue;
            results.add(result);
        }

        return results;
    }

    /**
     * Replace all accented characters in a String with the unaccented equivalent. As the equivalent is language
     * dependent, the list of accented characters and their corresponding unaccented characters must be provided
     * in the form of two Strings of equal length. This function does not support (yet) the substitution of one
     * accented character by two or more characters, as it might happen in German for example.
     * @param string to be unaccented.
     * @param accentedCharacters list of accented characters.
     * @param unaccentedCharacters list of corresponding unaccented characters.
     * @return unaccented version of string passed as a parameter.
     * @throws java.lang.IllegalArgumentException if the two lists of characters are not of equal length.
     */
    public static String getUnaccentedString(final String string, final String accentedCharacters, final String unaccentedCharacters) {
        if (accentedCharacters.length() != unaccentedCharacters.length())
            throw new IllegalArgumentException("length mismatch");
        String result = string;
        for (int i = 0; i < accentedCharacters.length(); i++)
            result = result.replaceAll(accentedCharacters.substring(i, i+1), unaccentedCharacters.substring(i, i+1));
        return result;
    }

    /**
     * Replace part of a String by another String.
     * @param content the String to be altered.
     * @param target the part of the String to be replaced.
     * @param replacement the String to use as a replacement.
     * @return a new String identical to <code>content</code> except where <code>target</code> is replaced by
     * <code>replacement</code>.
     * @see Strings#replaceMany(String, java.util.Map)
     * @see Strings#regexReplace(String, String, String)
     */
    public static String replace(final String content, final String target, final String replacement) {
        CharSequence a	= target.subSequence(0, target.length());
        CharSequence b	= replacement.subSequence(0, replacement.length());
        return content.replace(a, b);
    }

    /**
     * Replace parts of a String by other Strings.
     * @param content the String to be altered.
     * @param replacements a Map of parts to be replaced with their replacement.
     * @return a new String identical to <code>content</code> except where parts of it have been replaced according
     * to the <code>replacements</code> Map.
     * @see Strings#replace(String, String, String)
     * @see Strings#regexReplaceMany(String, Map)
     */
    public static String replaceMany(final String content, Map<String, String> replacements) {
        String result = content;
        for (String target: replacements.keySet())
            result = replace(result, target, replacements.get(target));
        return result;
    }

    /**
     * Replace part of a String by another String.
     * @param content the String to be altered.
     * @param regex a regular expression representing the part of the String to be replaced.
     * @param replacement the String to use as a replacement.
     * @return a new String identical to <code>content</code> except where <code>target</code> is replaced by
     * <code>replacement</code>.
     * @see Strings#replace(String, String, String)
     * @see Strings#regexReplaceMany(String, java.util.Map)
     */
    public static String regexReplace(final String content, final String regex, final String replacement) {
        return content.replaceAll(regex, replacement);
    }

    /**
     * Replace parts of a String by other Strings.
     * @param content the String to be altered.
     * @param replacements a Map of regular expressions to their replacements.
     * @return a new String identical to <code>content</code> except where parts of it have been replaced according
     * to the <code>replacements</code> Map.
     * @see Strings#regexReplace(String, String, String)
     * @see Strings#replaceMany(String, Map)
     */
    public static String regexReplaceMany(final String content, Map<String, String> replacements) {
        String result = content;
        for (String target: replacements.keySet())
            result = regexReplace(result, target, replacements.get(target));
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
        return concatStringCollectionWithSeparator(separator, strings);
    }

    /**
     * Takes a Collection of Strings and concatenate them together, inserting a specified separator between them.
     * @param separator to be inserted between Strings.
     * @param strings a list of Strings to be concatenated together.
     * @return the result of the concatenation.
     */
    public static String concatWithSeparator(final String separator, final Collection<String> strings) {
        return concatStringCollectionWithSeparator(separator, strings);
    }

    private static String concatStringCollectionWithSeparator(final String separator, final Collection<String> strings) {
        if (strings.isEmpty())
            return "";

        final StringBuilder buf = new StringBuilder();

        for (String s: strings) {
            buf.append(s);
            buf.append(separator);
        }
        buf.delete(buf.length() - separator.length(), buf.length());

        return buf.toString();
    }

    /**
     * Takes a long or compatible value and returns a string representing that value that starts with as many zeros
     * as necessary to reach the count of characters specified by the digits parameter.
     * @param value to be displayed with leading zeros.
     * @param digits how many digits to represent the value (2-18)
     * @return a String representing value, with leading zeros.
     * @throws java.lang.IllegalArgumentException if value is negative, or if value cannot be represented
     * with the number of digits specified by <code>digits</code> or less.
     */
    public static String zeroFill(final long value, final int digits) {
        if (value < 0)
            throw new IllegalArgumentException("Illegal value " + value + " < 1");
        if (digits < 2 || digits > 18)
            throw new IllegalArgumentException("Illegal digits number: " + digits + ", must be between 2 and 19.");

        if (value == 0)
            return repeatString("0", digits);

        final long maxVal = pow10(digits);

        if (value > maxVal)
            throw new IllegalArgumentException("Illegal value " + value + " > " + maxVal);

        final StringBuilder buf = new StringBuilder();
        long curMax = maxVal;
        for (int i = digits; i >= 0; i--) {
            curMax = curMax / 10;
            if (value >= curMax)
                break;
            else
                buf.append("0");
        }
        buf.append(value);

        return buf.toString();
    }

    /**
     * Takes one String and return it after removing all new line (whatever the platform your program is running on)
     * and tab characters. This function was added to facilitate string related unit test in a multi platform
     * context.
     * @param string to be stripped of new lines and tabs
     * @return same string without new lines and tabs
     */
    public static String putOnOneLineNoTabs(final String string) {
        return string.replaceAll("\\n|\\r\\n|\\r|\\t", "");
    }

    /**
     * Takes one String and return it after removing all new line (whatever the platform your program is running on).
     * @param string to be stripped of new lines
     * @return same string without new lines
     */
    public static String putOnOneLine(final String string) {
        return string.replaceAll("\\n|\\r\\n|\\r", "");
    }

    private static long pow10(final int power) {
        return recursivePow10(10, power);
    }

    private static long recursivePow10(final long base, final int power) {
        if (power == 1)
            return base;

        return recursivePow10(base * 10, power - 1);
    }

    /**
     * Takes one String and normalizes white space. Removes white space at beginning and end of string and transforms
     * any multiple spaces, tabs or line feeds inside the string into a single white space.
     * @param string to normalize
     * @return normalized string
     */
    public static String normalize(final String string) {
        return string.replaceAll("\\s+", " ").trim();
    }

    /**
     * Takes one string and removes all white space from it.
     * @param string
     * @return string without any whitespace
     */
    public static String removeWhiteSpace(final String string) {
        return string.replaceAll("\\s+", "").trim();
    }

    /**
     * Add characters to a String to bring it to a certain length.
     *
     * Characters can be added at the end or at the beginning of the String.
     * @param complemented String to be lengthened
     * @param complement characters to use to lengthen the String
     * @param minCharCount minimum length required
     * @param before true if the complement characters are to be inserted at the beginning of the String,
     *               otherwise they will be inserted at the end of it.
     * @return the complemented String.
     */
    public static String complementString(
            final String complemented,
            final String complement,
            final int minCharCount,
            final boolean before)
    {
        if (isEmpty(complemented))
            throw new IllegalArgumentException("Complement character is empty or null");

        final int complementedLength = complemented.length();
        if (complementedLength >= minCharCount)
               return complemented;

        final int complementLength = complement.length();
        int iterations = (minCharCount - complementedLength) / complementLength;
        if (complementedLength + iterations * complementLength < minCharCount)
            ++iterations;

        final StringBuilder buf = new StringBuilder();
        if (!before)
            buf.append(complemented);
        for (int i = 0; i < iterations; ++i)
            buf.append(complement);
        if (before)
            buf.append(complemented);

        return buf.toString();
    }

    /**
     * Given a String returns the same String repeated n times
     * @param s the String to repeat
     * @param times how many times to repeat the String
     * @return s repeated times times
     */
    public static String repeatString(final String s, final int times) {
        if (times < 0)
            throw new IllegalArgumentException("Multiplier must be >= 0");

        final StringBuilder repeated = new StringBuilder();
        for (int i = 0; i < times; ++i)
            repeated.append(s);

        return repeated.toString();
    }

    /**
     * Return a List of Strings from a List of any type by calling toString().
     * On each element in the parameter List, toString() is called to create the corresponding element in the
     * returned List.
     * @param objects list of objects from which the List of Strings will be produced
     * @param <T> any java Object
     * @return a List of Strings
     */
    public static <T> List<String> asListOfStrings(final List<T> objects) {
        final List<String> strings = new ArrayList<String>();

        for (T object: objects)
            strings.add(object.toString());

        return strings;
    }

    /**
     * Given a String, use a name/value map to replace parameters in that string. Parameter format
     * must be ${parameter-name}, à la JSP.
     *
     * This is a very simple facility for quick resolution of parameterized Strings.
     * @param target the String containing the parameters.
     * @param parameters a Map of String parameter names to Object. The parameter names should not contain
     *                   the ${} characters
     * @return String with replaced parameters
     * @throws NullPointerException if the parameter map does not contain a required value or if this value
     * is null.
     */
    public static String replaceWithParameters(final String target, final Map<String, Object> parameters) {
        return replaceMany(target, getParameterReplacementMap(parameters));
    }

    private static Map<String, String> getParameterReplacementMap(final Map<String, Object> parameters) {
        final Map<String, String> replacementMap = new HashMap<String, String>();

        for (String key: parameters.keySet())
            replacementMap.put("${" + key + "}", parameters.get(key).toString());

        return replacementMap;
    }

    /**
     * Shorten a String to a maximum length while respecting word boundaries. I.e., the String will be shortened
     * so that no word is cut in the middle.
     * @param text, to be shortened
     * @param maxLength, of the string to be returned
     * @return the shortened String
     */
    public static String getShortenedText(final String text, final int maxLength) {
        if (maxLength < 10)
            throw new IllegalArgumentException("Max length must be at least 10 characters");

        if (maxLength > text.length())
            return text;

        String workingString = text.substring(0, maxLength);
        int lastSpace = workingString.lastIndexOf(" ");
        if (lastSpace == -1)
            workingString = workingString + " ...";
        else
            workingString = workingString.substring(0, lastSpace) + " ...";

        return workingString;
    }

    /**
     * Splits a long String in a List of lines. The lines are created on word boundaries. New lines
     * are considered to indicate a new paragraph being started, therefore the current line will end
     * at the newline character. Newlines are removed from the output.
     * @param text to be decomposed in lines
     * @param maxLength a line can reach
     * @return a List of lines
     */
    public static List<String> splitIntoLines(final String text, final int maxLength) {
        if (maxLength < 10)
            throw new IllegalArgumentException("Max length must be at least 10 characters");

        String[] paragraphs = text.split("\\\n");
        List<String> lines = new ArrayList<String>();
        for (String paragraph: paragraphs) {
            StringBuilder buf = new StringBuilder(paragraph);
            while (buf.length() > maxLength) {
                String workingString = buf.substring(0, maxLength);
                int lastSpace = workingString.lastIndexOf(" ");
                if (lastSpace == -1)
                    throw new IllegalStateException("Could not split line on word boundary. Found a word at least "
                            + maxLength + " characters long.");
                lines.add(putOnOneLine(buf.substring(0, lastSpace)));
                buf.delete(0, lastSpace + 1);
            }
            if (buf.length() > 0)
                lines.add(putOnOneLine(buf.toString()));
        }

        return lines;
    }

    public static String hideZeros(int val) {
        if (val == 0)
            return "";

        return Integer.toString(val);
    }

    public static String hideZeros(long val) {
        if (val == 0)
            return "";

        return Long.toString(val);
    }

    public static String hideZeros(double val) {
        if (val == 0)
            return "";

        return Double.toString(val);
    }

    public static String passwordify(String password) {
        return repeatString("*", password.length());
    }
}
