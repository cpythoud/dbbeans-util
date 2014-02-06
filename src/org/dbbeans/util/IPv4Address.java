package org.dbbeans.util;

import java.util.regex.Pattern;

/**
 * This class is used to represent and validate IPv4 Addresses.
 * This class is used by {@link EmailValidator} to determine if the IP part of an e-mail address is correct.
 */
public class IPv4Address {

    /**
     * The constructor expects an array of four ints representing an IPv4 address.
     * @param bytes representing an IPv4 address.
     * @throws java.lang.IllegalArgumentException if the array length is not 4 or if any of the ints is not an
     * acceptable value (i.e., comprised between 0 and 255).
     */
    public IPv4Address(final int... bytes) {
        if (bytes.length != 4)
            throw new IllegalArgumentException("Illegal number of bytes in IP v4 Address: " + bytes.length + " (must be 4).");

        for (int i = 0; i < 4; i++)
            if (!isInRange(bytes[i]))
                throw new IllegalArgumentException("Illegal value for byte " + (i + 1) + ": " + bytes[i] + " (must be between 0 and 255).");

        setBytes(bytes);
    }

    /**
     * Copy constructor.
     * @param address to be copied.
     */
    public IPv4Address(final IPv4Address address) {
        setBytes(address.bytes);
    }

    /**
     * Creates an IPv4Address from a string representation of an IPv4 address.
     * @param address string representation of an IPv4 address.
     * @see IPv4Address#valueOf(String)
     */
    public IPv4Address(final String address) {
        this(valueOf(address));
    }

    private void setBytes(final int[] bytes) {
        System.arraycopy(bytes, 0, this.bytes, 0, 4);
    }

    /**
     * Checks if a String is a valid representation of an IPv4 address.
     * @param address to be checked.
     * @return true if <code>address</code> is a valid representation of an IPv4 address, false otherwise.
     */
    public static boolean isValid(final String address) {
        final String[] parts = address.split(".");
        if (parts.length != 4)
            return false;

        for (String part: parts)
            if (!NUMBERS_ONLY.matcher(part).matches() || !isInRange(Integer.valueOf(part)))
                return false;

        return true;
    }

    /**
     * Creates an IPv4Address from a string representation of an IPv4 address.
     * @param address string representation of an IPv4 address.
     * @return IPv4Address created.
     * @see IPv4Address#IPv4Address(String)
     */
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
