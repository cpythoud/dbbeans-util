package org.dbbeans.util;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

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
        return swapCharEntities(text, ACCENTED_CHARACTERS, ESCAPE_PATTERN);
    }

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
     * Returns a String where the following characters are escaped to their HTML entity equivalents: &lt;, &gt;, ", ', \.
     * @param text to be escaped.
     * @return escaped version of text.
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

    private final static String[] NUMBER_PATTERN = {
            "&#225;", "&#224;", "&#226;", "&#228;",
            "&#233;", "&#232;", "&#234;", "&#235;",
            "&#237;", "&#236;", "&#238;", "&#239;",
            "&#243;", "&#242;", "&#244;", "&#246;",
            "&#250;", "&#249;", "&#251;", "&#252;",
            "&#193;", "&#192;", "&#194;", "&#196;",
            "&#201;", "&#200;", "&#202;", "&#203;",
            "&#205;", "&#204;", "&#206;", "&#207;",
            "&#211;", "&#210;", "&#212;", "&#214;",
            "&#218;", "&#217;", "&#219;", "&#220;",
            "&#231;", "&#199;", "&#253;", "&#221;",
            "&#227;", "&#241;", "&#245;",
            "&#195;", "&#209;", "&#213;" };

    private final static String[] ESCAPE_PATTERN = {
            "&aacute;", "&agrave;", "&acirc;", "&auml;",
            "&eacute;", "&egrave;", "&ecirc;", "&euml;",
            "&iacute;", "&igrave;", "&icirc;", "&iuml;",
            "&oacute;", "&ograve;", "&ocirc;", "&ouml;",
            "&uacute;", "&ugrave;", "&ucirc;", "&uuml;",
            "&Aacute;", "&Agrave;", "&Acirc;", "&Auml;",
            "&Eacute;", "&Egrave;", "&Ecirc;", "&Euml;",
            "&Iacute;", "&Igrave;", "&Icirc;", "&Iuml;",
            "&Oacute;", "&Ograve;", "&Ocirc;", "&Ouml;",
            "&Uacute;", "&Ugrave;", "&Ucirc;", "&Uuml;",
            "&ccedil;", "&Ccedil;", "&yacute;", "&Yacute;",
            "&atilde;", "&ntilde;", "&otilde;",
            "&Atilde;", "&Ntilde;", "&Otilde;" };

    private final static String[] ACCENTED_CHARACTERS = {
            "á", "à", "â", "ä",
            "é", "è", "ê", "ë",
            "í", "ì", "î", "ï",
            "ó", "ò", "ô", "ö",
            "ú", "ù", "û", "ü",
            "Á", "À", "Â", "Ä",
            "É", "È", "Ê", "Ë",
            "Í", "Ì", "Î", "Ï",
            "Ó", "Ò", "Ô", "Ö",
            "Ú", "Ù", "Û", "Ü",
            "ç", "Ç", "ý", "Ý",
            "ã", "ñ", "õ",
            "Ã", "Ñ", "Õ" };
}
