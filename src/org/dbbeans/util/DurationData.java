package org.dbbeans.util;

public class DurationData {

    private final int days;
    private final int hours;
    private final int minutes;

    public DurationData(final int days, final int hours, final int minutes) {
        this.days = days;
        this.hours = hours;
        this.minutes = minutes;
    }

    public DurationData(final int minutes) {
        days = minutes / 1440;
        hours = (minutes % 1440) / 60;
        this.minutes = minutes % 60;
    }

    public int getDays() {
        return days;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getTotalMinutes() {
        return 1440 * days + 60 * hours + minutes;
    }

    public String format(
            final String daySymbol,
            final String hourSymbol,
            final String minuteSymbol,
            final boolean skipMinuteSymbol)
    {
        final StringBuilder buf = new StringBuilder();

        if (days > 0)
            buf.append(days).append(daySymbol);

        if (hours > 0) {
            if (buf.length() > 0)
                buf.append(" ");
            buf.append(hours).append(hourSymbol);
        }

        if (minutes > 0) {
            if (hours > 0) {
                if (skipMinuteSymbol)
                    buf.append(minutes);
                else
                    buf.append(" ").append(minutes).append(minuteSymbol);
            } else
                buf.append(minutes).append(minuteSymbol);
        }

        return buf.toString();
    }

    public String format(final SimpleInputDurationFormat simpleInputDurationFormat, final boolean skipMinuteSymbol) {
        return format(
                simpleInputDurationFormat.getDaySymbol(),
                simpleInputDurationFormat.getHourSymbol(),
                simpleInputDurationFormat.getMinuteSymbol(),
                skipMinuteSymbol);
    }
}
