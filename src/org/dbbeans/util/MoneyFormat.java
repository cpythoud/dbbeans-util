package org.dbbeans.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ...
 */
public class MoneyFormat {

    private final int decimals;

    private final List<String> decimalSeparators;

    private final List<String> decorators;

    private final long noDecimalMultiplier;

    private static final MoneyFormat DEFAULT_MONEY_FORMAT = (new Builder()).create();

    public static MoneyFormat getDefault() {
        return DEFAULT_MONEY_FORMAT;
    }

    public boolean isValOK(final String val) {
        if (hasSeparator() && !separatorOK(val))
            return false;

        return digitsOK(val);
    }

    private boolean hasSeparator() {
        return decimalSeparators.size() > 0;
    }

    private boolean separatorOK(final String val) {
        if (!separatorCountOK(val))
            return false;
        return separatorPlaceOK(val);
    }

    private boolean separatorCountOK(final String val) {
        int count = 0;
        for (final String separator: decimalSeparators)
            count += Strings.occurrenceCount(val, separator);
        return count <= 1;
    }

    private boolean separatorPlaceOK(final String val) {
        for (final String separator: decimalSeparators) {
            final int index = val.indexOf(separator);
            if (index == -1)
                continue;
            if (index < val.length() - decimals - 1 || index == val.length() - 1)
                return false;
        }

        return true;
    }

    private boolean digitsOK(final String val) {
        for (final String digit: Strings.toLetterList(val)) {
            if (digit.matches("[0-9]"))
                continue;
            if (decimalSeparators.contains(digit) || decorators.contains(digit))
                continue;
            return false;
        }

        return true;
    }

    public long getVal(final String s) {
        if (!isValOK(s))
            throw new IllegalArgumentException("Argument contains invalid characters: " + s);

        return calcValue(removeDecorators(s));
    }

    private String removeDecorators(final String s) {
        if (decorators.isEmpty())
            return s;

        final List<String> digits = Strings.toLetterList(s);
        final StringBuilder buf = new StringBuilder();
        for (final String digit: digits)
            if (!decorators.contains(digit))
                buf.append(digit);

        return buf.toString();
    }

    private long calcValue(final String s) {
        if (decimals == 0)
            return getUnitValue(s);

        final long unitValue = getUnitValue(s);
        final long decimalValue = getDecimalValue(s);

        return unitValue * noDecimalMultiplier + decimalValue;
    }

    private long getUnitValue(final String s) {
        if (decimals == 0)
            return Long.valueOf(s);

        return Long.valueOf(s.substring(0, getDecimalSymbolIndex(s)));
    }

    private int getDecimalSymbolIndex(final String s) {
        int index = s.length();

        for (final String separator: decimalSeparators) {
            final int idx = s.indexOf(separator);
            if (idx != -1) {
                index = idx;
                break;
            }
        }

        return index;
    }

    private long getDecimalValue(final String s) {
        final int index = getDecimalSymbolIndex(s);
        if (index == s.length())
            return 0;

        final String digits = s.substring(index + 1, s.length());
        if (digits.length() == decimals)
            return Long.valueOf(digits);

        long val = Long.valueOf(digits);
        for (int i = digits.length(); i < decimals; i++)
            val *= 10;
        return val;
    }

    public String print(final long val) {
        if (decimals == 0)
            return getDigitsWithSeparator(val);

        final long unitVal = val / noDecimalMultiplier;
        final long decimalVal = val - unitVal * noDecimalMultiplier;

        return getDigitsWithSeparator(unitVal) + decimalSeparators.get(0) + formatDecimals(decimalVal);
    }

    private String getDigitsWithSeparator(final long val) {
        final String digits = Long.toString(val);

        if (decorators.isEmpty())
            return digits;

        final StringBuilder buf = new StringBuilder();
        int count = 0;
        for (int i = digits.length(); i > 0; i--) {
            buf.append(digits.substring(i - 1, i));
            count++;
            if (count == 3) {
                buf.append(decorators.get(0));
                count = 0;
            }
        }
        if (count == 0)
            buf.delete(buf.length() - 1, buf.length());

        return buf.reverse().toString();
    }

    private String formatDecimals(final long decimalDigits) {
        final String ddStr = Long.toString(decimalDigits);

        if (ddStr.length() == decimals)
            return ddStr;

        final StringBuilder buf = new StringBuilder();
        for (int i = ddStr.length(); i < decimals; i++)
            buf.append("0");
        buf.append(ddStr);

        return buf.toString();
    }


    private MoneyFormat(final int decimals, final List<String> decimalSeparators, final List<String> decorators) {
        this.decimals = decimals;
        this.decimalSeparators = deepCopy(decimalSeparators);
        this.decorators = deepCopy(decorators);

        noDecimalMultiplier = calcNoDecimalMultiplier();
    }

    private List<String> deepCopy(List<String> list) {
        List<String> copy = new ArrayList<String>();

        for (final String s: list)
            copy.add(s);

        return copy;
    }

    private long calcNoDecimalMultiplier() {
        long mul = 1;
        for (int i =0; i < decimals; i++)
            mul *= 10;
        return mul;
    }

    public static class Builder {
        private int decimals;

        private List<String> decimalSeparators;

        private List<String> decorators;

        public Builder() {
            decimals = 2;

            decimalSeparators = new ArrayList<String>();
            decimalSeparators.add(".");
            decimalSeparators.add(",");

            decorators = new ArrayList<String>();
            decorators.add(" ");
            decorators.add("'");
        }

        public Builder setDecimals(final int decimals) {
            if (decimals < 0 || decimals > 5)
                throw new IllegalArgumentException("Number of decimal positions must be between 0 and 5.");

            this.decimals = decimals;

            return this;
        }

        public Builder clearDecimalSeparators() {
            decimalSeparators.clear();
            return this;
        }

        public Builder addDecimalSeparator(final String separator) {
            if (!elementOK(separator))
                throw new IllegalArgumentException("Illegal decimal separator: " + separator);

            decimalSeparators.add(separator);

            return this;
        }

        private boolean elementOK(final String element) {
            if (element.length() != 1)
                return false;

            if (element.matches("[0-9]"))
                return false;

            return true;
        }

        public Builder clearDecorators() {
            decorators.clear();
            return this;
        }

        public Builder addDecorator(final String decorator) {
            if (!elementOK(decorator))
                throw new IllegalArgumentException("Illegal decorator: " + decorator);

            decorators.add(decorator);

            return this;
        }

        public MoneyFormat create() {
            for (final String sep: decimalSeparators)
                if (decorators.contains(sep))
                    throw new IllegalArgumentException("Decimal Separator " + sep + " is also present in decorators.");

            if (decimalSeparators.isEmpty() && decimals > 0)
                throw new IllegalArgumentException("No decimal separator specified and format takes " + decimals + " decimals.");

            if (decimals == 0 && !decimalSeparators.isEmpty())
                throw new IllegalArgumentException("Cannot specify a decimal separator when there is no decimal.");

            return new MoneyFormat(decimals, decimalSeparators, decorators);
        }
    }
}
