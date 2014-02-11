package org.dbbeans.util.json;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;

public class JsonArrayOfAtMillisecondsElement extends JsonArrayOfStringsElement {

	public JsonArrayOfAtMillisecondsElement(final String name, final List<Long> values) {
		super(name, getStringList(values));
	}

	public static JsonArrayOfAtMillisecondsElement createElementFromDates(final String name, final List<Date> dates) {
		final List<Long> millis = new ArrayList<Long>(dates.size());
		for (Date date: dates)
			millis.add(date.getTime());
		return new JsonArrayOfAtMillisecondsElement(name, millis);
	}

	public static JsonArrayOfAtMillisecondsElement createElementFromTimes(final String name, final List<Time> times) {
		final List<Long> millis = new ArrayList<Long>(times.size());
		for (Time time: times)
			millis.add(time.getTime());
		return new JsonArrayOfAtMillisecondsElement(name, millis);
	}

    public static JsonArrayOfAtMillisecondsElement createElementFromTimestamps(final String name, final List<Timestamp> timestamps) {
        final List<Long> milis = new ArrayList<Long>(timestamps.size());
        for (Timestamp timestamp: timestamps)
            milis.add(timestamp.getTime());
        return new JsonArrayOfAtMillisecondsElement(name, milis);
    }
	
	private static List<String> getStringList(final List<Long> values) {
		final List<String> strings = new ArrayList<String>(values.size());
		
		for (long val: values)
			strings.add(JsonAtMillisecondsElement.calcVal(val));
		
		return strings;
	}
}

