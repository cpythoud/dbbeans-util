package org.dbbeans.util;

import org.apache.commons.lang3.text.translate.AggregateTranslator;
import org.apache.commons.lang3.text.translate.CharSequenceTranslator;
import org.apache.commons.lang3.text.translate.EntityArrays;
import org.apache.commons.lang3.text.translate.LookupTranslator;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.List;

/**
 * This class contains static function to help with manipulating text in an HTML/web application context.
 */
public class HTMLText {

    /**
     * Replace all newlines in a string by the html tag &lt;br&gt;
     * @param string to be converted
     * @return a string with all new lines replaced by the &lt;br&gt; tag
     */
    public static String linefeed2br(final String string) {
        final String[] lines = string.split("\\\n");
        final StringBuilder buf = new StringBuilder();
        for (int i = 0; i < lines.length - 1; i++) {
            buf.append(lines[i]);
            buf.append("<br/>");
        }
        buf.append(lines[lines.length - 1]);
        return buf.toString();
    }

    /**
     * Given a String containing HTML Unicode escape sequence (like {@literal &#233;}), this function returns
     * a similar string with all Unicode escape sequence replaced by the corresponding named entity
     * (like {@literal &eacute;}).
     * @param text to be processed.
     * @return a string with Unicode escape sequences replaced by the corresponding named entities.
     */
    public static String numberToEscape(final String text) {
        return swapCharEntities(text, NUMBER_PATTERN, ESCAPE_PATTERN);
    }

    /**
     * Given a String containing accented characters (like &eacute;), this function returns
     * a similar string with all accented characters replaced by the corresponding named entity
     * (like {@literal &eacute;}).
     * @param text to be processed.
     * @return a string with accented characters replaced by the corresponding named entities.
     */
    public static String accentsToEscape(final String text) {
        return HTMl_ACCENTED_CHARACTERS_ESCAPE.translate(text);
    }

    private static final CharSequenceTranslator HTMl_ACCENTED_CHARACTERS_ESCAPE =
            new AggregateTranslator(
                    new LookupTranslator(EntityArrays.ISO8859_1_ESCAPE()),
                    new LookupTranslator(EntityArrays.HTML40_EXTENDED_ESCAPE())
            );

    /**
     * Given a String containing named entities (like {@literal &eacute;}), this function returns
     * a similar string with all named entities replaced by the corresponding accented character (like &eacute;).
     * @param text to be processed.
     * @return a string with named entities replaced by the corresponding accented characters.
     */
    public static String escapeToAccents(final String text) {
        return swapCharEntities(text, ESCAPE_PATTERN, ACCENTED_CHARACTERS);
    }

    private static String swapCharEntities(final String text, final String[] source, final String[] target) {
        String result = text;
        if (source.length != target.length)
            throw new IllegalStateException(); // should never happen ...
        int max = source.length;
        for (int i = 0; i < max; i++)
            result = result.replaceAll(source[i], target[i]);
        return result;
    }

    /**
     * Returns a String where the following characters are escaped to their HTML entity equivalents:
     * &lt;, &gt;, ", ', \, &amp;.
     * @param text to be escaped.
     * @return escaped version of text.
     * @see #escapeEssentialHTMLtext(String)
     */
    public static String escapeHTMLtext(final String text) {
        final StringBuilder result = new StringBuilder();
        final StringCharacterIterator iterator = new StringCharacterIterator(text);
        char character =  iterator.current();
        while (character != CharacterIterator.DONE ){
            if (character == '<') {
                result.append("&lt;");
            } else if (character == '>') {
                result.append("&gt;");
            } else if (character == '\"') {
                result.append("&quot;");
            } else if (character == '\'') {
                result.append("&#039;");
            } else if (character == '\\') {
                result.append("&#092;");
            } else if (character == '&') {
                result.append("&amp;");
            } else {
                result.append(character);
            }
            character = iterator.next();
        }
        return result.toString();
    }

    /**
     * Returns a String where the following characters are escaped to their HTML entity equivalents:
     * &lt;, &gt;, &amp;.
     * @param text to be escaped.
     * @return escaped version of text.
     * @see #escapeHTMLtext(String)
     */
    public static String escapeEssentialHTMLtext(final String text) {
        final StringBuilder result = new StringBuilder();
        final StringCharacterIterator iterator = new StringCharacterIterator(text);
        char character =  iterator.current();
        while (character != CharacterIterator.DONE ){
            if (character == '<') {
                result.append("&lt;");
            } else if (character == '>') {
                result.append("&gt;");
            } else if (character == '&') {
                result.append("&amp;");
            } else {
                result.append(character);
            }
            character = iterator.next();
        }
        return result.toString();
    }

    /**
     * Transforms a List of Strings in a String where every item in the list is concatenated to the previous
     * one with a &lt;br&gt; tag.
     * @param items
     * @return items concatenated, separated by BRs.
     */
    public static String brList(final List<String> items) {
        if (items.isEmpty())
            return "";

        final StringBuilder buf = new StringBuilder();

        for (String item: items)
            buf.append(item).append("<br>");

        buf.delete(buf.length() - 4, buf.length());

        return buf.toString();
    }

    private final static String[] NUMBER_PATTERN = CharacterLists.getNumberPatternArray();

    private final static String[] ESCAPE_PATTERN = CharacterLists.getEscapePatternArray();

    private final static String[] ACCENTED_CHARACTERS = CharacterLists.getAccentedCharacterArray();
}
