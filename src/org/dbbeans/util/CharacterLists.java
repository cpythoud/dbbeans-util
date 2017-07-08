package org.dbbeans.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Contains list of character for conversion and transformation operations on Strings
 */
public class CharacterLists {

    private static final String[] NUMBER_PATTERNS = {
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

    private static final String[] ESCAPE_PATTERNS = {
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

    private static final String[] ACCENTED_CHARACTERS = {
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

    private static final String[] UNACCENTED_CHARACTERS = {
            "a", "a", "a", "a",
            "e", "e", "e", "e",
            "i", "i", "i", "i",
            "o", "o", "o", "o",
            "u", "u", "u", "u",
            "A", "A", "A", "A",
            "E", "E", "E", "E",
            "I", "I", "I", "I",
            "O", "O", "O", "O",
            "U", "U", "U", "U",
            "c", "C", "y", "Y",
            "a", "n", "o",
            "A", "N", "O" };

    private static final String[] URL_RESERVED_CHARACTERS = {
            "!", "*", "'", "(", ")", ";", ":", "@", "&", "=", "+", "$", ",", "/", "?", "#", "[", "]"
    };

    private static final String[] URL_UNRESERVED_CHARACTERS = {
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
            "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
            "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "-", "_", ".", "~"
    };

    public static String[] getNumberPatternArray() {
        return Arrays.copyOf(NUMBER_PATTERNS, NUMBER_PATTERNS.length);
    }

    public static String[] getEscapePatternArray() {
        return Arrays.copyOf(ESCAPE_PATTERNS, ESCAPE_PATTERNS.length);
    }

    public static String[] getAccentedCharacterArray() {
        return Arrays.copyOf(ACCENTED_CHARACTERS, ACCENTED_CHARACTERS.length);
    }

    public static String[] getUnaccentedCharacterArray() {
        return Arrays.copyOf(UNACCENTED_CHARACTERS, UNACCENTED_CHARACTERS.length);
    }

    public static String[] getUrlReservedCharacterArray() {
        return Arrays.copyOf(URL_RESERVED_CHARACTERS, URL_RESERVED_CHARACTERS.length);
    }

    public static String[] getUrlUnreservedCharacterArray() {
        return Arrays.copyOf(URL_UNRESERVED_CHARACTERS, URL_UNRESERVED_CHARACTERS.length);
    }

    public static final List<String> NUMBER_PATTERN_LIST =
            Collections.unmodifiableList(Arrays.asList(NUMBER_PATTERNS));

    public static final List<String> ESCAPE_PATTERN_LIST =
            Collections.unmodifiableList(Arrays.asList(ESCAPE_PATTERNS));

    public static final List<String> ACCENTED_CHARACTER_LIST =
            Collections.unmodifiableList(Arrays.asList(ACCENTED_CHARACTERS));

    public static final List<String> UNACCENTED_CHARACTER_LIST =
            Collections.unmodifiableList(Arrays.asList(UNACCENTED_CHARACTERS));

    public static final List<String> URL_RESERVED_CHARACTER_LIST =
            Collections.unmodifiableList(Arrays.asList(URL_RESERVED_CHARACTERS));

    public static final List<String> URL_UNRESERVED_CHARACTER_LIST =
            Collections.unmodifiableList(Arrays.asList(URL_UNRESERVED_CHARACTERS));

    public static final String ACCENTED_CHARACTER_STRING = Strings.concat(ACCENTED_CHARACTER_LIST);

    public static final String UNACCENTED_CHARACTER_STRING = Strings.concat(UNACCENTED_CHARACTER_LIST);

    public static final String URL_RESERVED_CHARACTER_STRING = Strings.concat(URL_RESERVED_CHARACTER_LIST);

    public static final String URL_UNRESERVED_CHARACTER_STRING = Strings.concat(URL_UNRESERVED_CHARACTER_LIST);
}
