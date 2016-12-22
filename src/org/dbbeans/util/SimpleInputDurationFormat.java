package org.dbbeans.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class SimpleInputDurationFormat {

    private static final Pattern DIGITS =  Pattern.compile("\\d+");

    private final String daySymbol;
    private final String hourSymbol;
    private final String minuteSymbol;

    public SimpleInputDurationFormat(final String daySymbol, final String hourSymbol, final String minuteSymbol) {
        this.daySymbol = daySymbol;
        this.hourSymbol = hourSymbol;
        this.minuteSymbol = minuteSymbol;
    }

    public String getDaySymbol() {
        return daySymbol;
    }

    public String getHourSymbol() {
        return hourSymbol;
    }

    public String getMinuteSymbol() {
        return minuteSymbol;
    }

    public boolean validate(final String duration) {
        return validateAndParse(duration).e1;
    }

    private Pair<Boolean, List<String>> validateAndParse(final String durationParameter) {
        final String duration = Strings.removeWhiteSpace(durationParameter);
        final List<String> results = new ArrayList<String>();

        int offset = 0;

        final Pair<String, Integer> dayDigitsAndOffset = getDigitsAndOffset(duration, offset, daySymbol, false);
        if (dayDigitsAndOffset.e2 == null)
            return returnFalse();
        results.add(dayDigitsAndOffset.e1);
        offset = dayDigitsAndOffset.e2;

        final Pair<String, Integer> hourDigitsAndOffset = getDigitsAndOffset(duration, offset, hourSymbol, false);
        if (hourDigitsAndOffset.e2 == null)
            return returnFalse();
        results.add(hourDigitsAndOffset.e1);
        offset = hourDigitsAndOffset.e2;

        final Pair<String, Integer> minuteDigitsAndOffset = getDigitsAndOffset(duration, offset, minuteSymbol, true);
        if (minuteDigitsAndOffset.e2 == null)
            return returnFalse();
        results.add(minuteDigitsAndOffset.e1);
        offset = minuteDigitsAndOffset.e2;

        /*System.out.println(dayDigitsAndOffset.e1);
        System.out.println(dayDigitsAndOffset.e2);
        System.out.println(hourDigitsAndOffset.e1);
        System.out.println(hourDigitsAndOffset.e2);
        System.out.println(minuteDigitsAndOffset.e1);
        System.out.println(minuteDigitsAndOffset.e2);*/

        if (offset != duration.length())
            throw new AssertionError();//return returnFalse();

        for (String result: results)
            if (!Strings.isEmpty(result))
                return new Pair<Boolean, List<String>>(true, results);

        return returnFalse();
    }

    private Pair<String, Integer> getDigitsAndOffset(final String duration, final int offset, final String symbol, final boolean symbolOptional) {
        final int pos = duration.indexOf(symbol);
        if (pos != -1) {
            if (pos == offset)
                return new Pair<String, Integer>(null, offset);
            final String digits = duration.substring(offset, pos);
            if (checkDigits(digits))
                return new Pair<String, Integer>(digits, pos + symbol.length());

            return new Pair<String, Integer>(null, offset);
        }

        if (symbolOptional) {
            final String digits = duration.substring(offset, duration.length());
            if (checkDigits(digits))
                return new Pair<String, Integer>(digits, duration.length());
        }

        return new Pair<String, Integer>("", offset);
    }

    private Pair<Boolean, List<String>> returnFalse() {
        return new Pair<Boolean, List<String>>(false, new ArrayList<String>());
    }

    private boolean checkDigits(final String digits) {
        return DIGITS.matcher(digits).matches();
    }

    public DurationData parse(final String duration) {
        final Pair<Boolean, List<String>> parsedData = validateAndParse(duration);
        if (!parsedData.e1)
            throw new IllegalArgumentException("Illegal duration format: " + duration);

        final String dayStr = parsedData.e2.get(0);
        final String hourStr = parsedData.e2.get(1);
        final String minuteStr = parsedData.e2.get(2);

        final int days;
        if (Strings.isEmpty(dayStr))
            days = 0;
        else
            days = Integer.valueOf(dayStr);

        final int hours;
        if (Strings.isEmpty(hourStr))
            hours = 0;
        else
            hours = Integer.valueOf(hourStr);

        final int minutes;
        if (Strings.isEmpty(minuteStr))
            minutes = 0;
        else
            minutes = Integer.valueOf(minuteStr);

        return new DurationData(days, hours, minutes);
    }

}
