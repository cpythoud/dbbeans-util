package org.dbbeans.util.json;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a json element with an array of milliseconds value.
 */
public class JsonArrayOfAtMillisecondsElement extends JsonArrayOfStringsElement {

    /**
     * Creates the json element and gives it a name and value.
     * @param name of the element.
     * @param values the array value of the element.
     */
	public JsonArrayOfAtMillisecondsElement(final String name, final List<Long> values) {
		super(name, getStringList(values));
	}

    /**
     * Creates the json element from a list of Dates.
     * @param name of the element.
     * @param dates of the element.
     */
	public static JsonArrayOfAtMillisecondsElement createElementFromDates(final String name, final List<Date> dates) {
		final List<Long> millis = new ArrayList<Long>(dates.size());
		for (Date date: dates)
			millis.add(date.getTime());
		return new JsonArrayOfAtMillisecondsElement(name, millis);
	}

    /**
     * Creates the json element from a list of Times.
     * @param name of the element.
     * @param times of the element.
     */
	public static JsonArrayOfAtMillisecondsElement createElementFromTimes(final String name, final List<Time> times) {
		final List<Long> millis = new ArrayList<Long>(times.size());
		for (Time time: times)
			millis.add(time.getTime());
		return new JsonArrayOfAtMillisecondsElement(name, millis);
	}

    /**
     * Creates the json element from a list of Timestamps.
     * @param name of the element.
     * @param timestamps of the element.
     */
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

