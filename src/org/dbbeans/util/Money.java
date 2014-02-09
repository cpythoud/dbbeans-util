package org.dbbeans.util;

/**
 * ...
 */
public class Money {
    private final long val;

    private final MoneyFormat format;


    public Money(final long val, final MoneyFormat format) {
        if (val < 0)
            throw new IllegalArgumentException("Negative values not accepted (for now).");

        this.val = val;
        this.format = format;
    }

    public Money(final long val) {
        this(val, MoneyFormat.getDefault());
    }

    public Money(final double val, final MoneyFormat format) {
        this((long) (val + 0.5), format);
    }

    public Money(final double val) {
        this(val, MoneyFormat.getDefault());
    }

    public Money(final String val, final MoneyFormat format) {
        this(format.getVal(val), format);
    }

    public Money(final String val) {
        this(MoneyFormat.getDefault().getVal(val), MoneyFormat.getDefault());
    }

    public long getVal() {
        return val;
    }

    public int getIntVal() {
        if (val > Integer.MAX_VALUE)
            throw new IllegalArgumentException("Monetary value too big to fit in an int: " + val);

        return (int) val;
    }

    public MoneyFormat getFormat() {
        return format;
    }

    public String toString() {
        return format.print(val);
    }

    public Money getPercent(final int percent) {
        if (percent < 0 || percent > 100)
            throw new IllegalArgumentException("Percentage must be comprised between 0 and 100. Incorrect value: " + percent);

        final double result = val * ((float) percent / 100);
        return new Money(Math.round(result), format);
    }
}
