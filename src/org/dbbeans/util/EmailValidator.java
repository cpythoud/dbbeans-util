package org.dbbeans.util;

import java.util.regex.Pattern;

/**
 * ...
 */
public class EmailValidator {

    public static boolean validate(final String email, final boolean allowIpForDomain, final boolean extensiveTldValidation, final boolean acceptQuotedStrings) {
        final String[] parts = email.split("@");

        if (parts.length != 2)
            return false;

        final String local = parts[0];
        final String domain = parts[1];

        if (!dotsOK(local) || !dotsOK(domain))
            return false;

        final String[] localParts = local.split("\\.");
        for (String part: localParts)
            if (!ALLOWED_CHARS_IN_LOCAL_PART.matcher(part).matches())
                return false;

        if (allowIpForDomain && IPv4Address.isValid(domain))
            return true;

        final String[] domainParts = domain.split("\\.");
        if (domainParts.length < 2)
            return false;
        for (String part: domainParts)
            if (!ALLOWED_CHARS_IN_DOMAIN_PART.matcher(part).matches())
                return false;

        final String tld = domainParts[domainParts.length - 1];
        if (tld.length() == 2) {
            if (extensiveTldValidation)
                return TLDs.isCountryTLD(tld);
            else
                return true;
        }

        return TLDs.isGenericTLD(tld) || TLDs.isUSATLD(tld);
    }

    public static boolean dotsOK(final String part) {
        return !part.startsWith(".") && !part.endsWith(".") && part.indexOf("..") == -1;
    }

    public static final Pattern ALLOWED_CHARS_IN_LOCAL_PART = Pattern.compile("[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+");
    public static final Pattern ALLOWED_CHARS_IN_DOMAIN_PART = Pattern.compile("[A-Za-z0-9]+");
}
