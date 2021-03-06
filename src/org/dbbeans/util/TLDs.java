package org.dbbeans.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * This class contains static functions to check the top level part of domain names.
 * It is optionally used with the EmailValidator} class.
 * WARNING: this class does not know about the new TLDs released in February 2014.
 */
@Deprecated
public class TLDs {

    /**
     * Check if argument is a valid top level domain name.
     * @param domain name to be checked.
     * @return true if the domain name is valid, false otherwise.
     */
    public static boolean isValidTLD(final String domain) {
        return isGenericTLD(domain) || isUSATLD(domain) || isCountryTLD(domain);
    }

    /**
     * Check if argument is a valid generic top level domain name (aero, asia, biz, cat, com, coop, info, int, jobs,
     * mobi, museum, name, net, org, pro, tel, travel, xxx).
     * @param domain name to be checked.
     * @return true if the domain name is valid, false otherwise.
     */
    public static boolean isGenericTLD(final String domain) {
        return GENERIC_TLD.contains(domain);
    }

    /**
     * Check if argument is a valid generic top level domain name in the USA (edu, gov, mil).
     * @param domain name to be checked.
     * @return true if the domain name is valid, false otherwise.
     */
    public static boolean isUSATLD(final String domain) {
        return USA_TLD.contains(domain);
    }

    /**
     * Check if argument is a valid country top level domain name.
     * @param domain name to be checked.
     * @return true if the domain name is valid, false otherwise.
     */
    public static boolean isCountryTLD(final String domain) {
        return COUNTRY_TLD.contains(domain);
    }

    /**
     * Get the list of generic TLDs.
     * @return the list of generic TLDs.
     */
    public static List<String> getGenericTLDs() {
        return Collections.unmodifiableList(GENERIC_TLD);
    }

    /**
     * Get the list of US specific generic TLDs.
     * @return the list of US specific generic TLDs.
     */
    public static List<String> getUSATLDs() {
        return Collections.unmodifiableList(USA_TLD);
    }

    /**
     * Get the list of country TLDs.
     * @return the list of country TLDs.
     */
    public static List<String> getCountryTLDs() {
        return Collections.unmodifiableList(COUNTRY_TLD);
    }

    private final static List<String> GENERIC_TLD = Arrays.asList("aero", "asia", "biz", "cat", "com", "coop", "info", "int", "jobs", "mobi", "museum", "name", "net", "org", "pro", "tel", "travel", "xxx");
    private final static List<String> USA_TLD     = Arrays.asList("edu", "gov", "mil");
    private final static List<String> COUNTRY_TLD = Arrays.asList("ac", "ad", "ae", "af", "ag", "ai", "al", "am", "an", "ao", "aq", "ar", "as", "at", "au", "aw", "ax", "az",
            "ba", "bb", "bd", "be", "bf", "bg", "bh", "bi", "bj", "bm", "bn", "bo", "br", "bs", "bt", "bv", "bw", "by", "bz",
            "ca", "cd", "cf", "cg", "ch", "ci", "ck", "cl", "cm", "cn", "co", "cr", "cu", "cv", "cx", "cy", "cz",
            "dd", "de", "dj", "dk", "dm", "do", "dz",
            "ec", "ee", "eg", "eh", "er", "es", "et", "es", "et", "eu",
            "fi", "fj", "fk", "fm", "fo", "fr",
            "ga", "gb", "gd", "ge", "gf", "gh", "gi", "gl", "gm", "gn", "gp", "gq", "gr", "gs", "gt", "gu", "gw", "gy",
            "hk", "hm", "hn", "hr", "ht", "hu",
            "id", "ie", "il", "im", "in", "io", "iq", "ir", "is", "it",
            "je", "jm", "jo", "jp",
            "ke", "kg", "kh", "ki", "km", "kn", "kp", "kr", "kw", "ky", "kz",
            "la", "lb", "lc", "li", "lk", "lr", "ls", "lt", "lu", "lv", "ly",
            "ma", "mc", "md", "me", "mg", "mh", "mk", "ml", "mm", "mn", "mo", "mp", "mq", "mr", "ms", "mt", "mu", "mv", "mw", "mx", "my", "mz",
            "na", "nc", "ne", "nf", "ng", "ni", "nl", "no", "np", "nr", "nu", "nz",
            "om",
            "pa", "pe", "pf", "pg", "ph", "pk", "pl", "pm", "pn", "pr", "ps", "pt", "pw", "py",
            "qa",
            "re", "ro", "rs", "ru", "rw",
            "sa", "sb", "sc", "sd", "se", "sg", "sh", "si", "sj", "sk", "sl", "sm", "sn", "so", "sr", "ss", "st", "su", "sv", "sy", "sz",
            "tc", "td", "tf", "tg", "th", "tj", "tk", "tl", "tm", "tn", "to", "tp", "tr", "tt", "tv", "tw", "tz",
            "ua", "ug", "uk", "us", "uy", "uz",
            "va", "vc", "ve", "vg", "vi", "vn", "vu",
            "wf", "ws",
            "ye", "yt", "yu",
            "za", "zm", "zw");
}
