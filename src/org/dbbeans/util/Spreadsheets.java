package org.dbbeans.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Spreadsheets {

    private static final List<String> COLUMN_LETTERS = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");

    private static final Map<String, Integer> COLUMN_INDEXES;

    static {
        COLUMN_INDEXES = new HashMap<String, Integer>();
        for (int i = 0; i < 26; ++i)
            COLUMN_INDEXES.put(COLUMN_LETTERS.get(i), i);
    }

    /**
     * Return the name (letters) of a column in a spreadsheet from the column number. Column numbers start at 1.
     * @param columnNumber the column number
     * @return name (letters) for the column number
     */
    public static String getColumnLetters(final int columnNumber) {
        if (columnNumber <= 0)
            throw new IllegalArgumentException("Column number must be greater than zero");

        StringBuilder buf = new StringBuilder();
        int div = columnNumber;
        int mod;

        while (div > 0) {
            mod = (div - 1) % 26;
            buf.append(COLUMN_LETTERS.get(mod));
            div = (div - mod) / 26;
        }

        return buf.reverse().toString();
    }

    /**
     * Return the numerical index of a spreadsheet column. The index starts at 1.
     * @param columnLetters the letters used to reference the column
     * @return the column index starting at 1
     */
    public static int getColumnIndex(final String columnLetters) {
        final String letters = columnLetters.toUpperCase();
        if (!letters.matches("[A-Z]{1,5}"))
            throw new IllegalArgumentException("Illegal column name: " + columnLetters);

        int sum = 0;
        for (int i = 0; i < letters.length(); ++i) {
            sum *= 26;
            sum += COLUMN_INDEXES.get(letters.substring(i, i + 1)) + 1;
        }

        return sum;
    }
}
