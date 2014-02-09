package org.dbbeans.util;

/**
 * ...
 */
public class HTMLText {

    public static String numberToEscape(final String text) {
        return swapCharEntities(text, NUMBER_PATTERN, ESCAPE_PATTERN);
    }

    public static String accentsToEscape(final String text) {
        return swapCharEntities(text, ACCENTED_CHARACTERS, ESCAPE_PATTERN);
    }

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
