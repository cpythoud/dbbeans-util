package org.dbbeans.util;

import java.util.regex.Pattern;

/**
 * ...
 */
public class IPv4Address {

    public IPv4Address(final int... bytes) {
        if (bytes.length != 4)
            throw new IllegalArgumentException("Illegal number of bytes in IP v4 Address: " + bytes.length + " (must be 4).");

        for (int i = 0; i < 4; i++)
            if (!isInRange(bytes[i]))
                throw new IllegalArgumentException("Illegal value for byte " + (i + 1) + ": " + bytes[i] + " (must be between 0 and 255).");

        setBytes(bytes);
    }

    public IPv4Address(final IPv4Address address) {
        setBytes(address.bytes);
    }

    public IPv4Address(final String address) {
        this(valueOf(address));
    }

    private void setBytes(final int[] bytes) {
        System.arraycopy(bytes, 0, this.bytes, 0, 4);
    }

    public static boolean isValid(final String address) {
        final String[] parts = address.split(".");
        if (parts.length != 4)
            return false;

        for (String part: parts)
            if (!NUMBERS_ONLY.matcher(part).matches() || !isInRange(Integer.valueOf(part)))
                return false;

        return true;
    }

    public static IPv4Address valueOf(final String address) {
        if (!isValid(address))
            throw new IllegalArgumentException("Invalid IP Address Format: " + address);

        final String[] parts = address.split(".");

        int[] bytes = new int[4];

        for (int i = 0; i < 4; i++)
            bytes[i] = Integer.valueOf(parts[i]);

        return new IPv4Address(bytes);
    }

    private static boolean isInRange(final int bytePart) {
        return bytePart >= 0 && bytePart < 256;
    }

    private final int[] bytes = new int[4];

    private final static Pattern NUMBERS_ONLY = Pattern.compile("[0-9]{1,3}");
}
