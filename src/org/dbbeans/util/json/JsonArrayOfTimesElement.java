package org.dbbeans.util.json;

import java.sql.Time;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a json element with an array of strings value.
 */
public class JsonArrayOfTimesElement extends JsonArrayOfStringsElement {

    /**
     * Creates the json element and gives it a name and value.
     * @param name of the element.
     * @param values the array value of the element.
     */
	public JsonArrayOfTimesElement(final String name, final List<Time> values) {
		super(name, getStringList(values));
	}
	
	private static List<String> getStringList(final List<Time> times) {
		final List<String> strings = new ArrayList<String>(times.size());
		
		for (Time time: times)
			strings.add(time.toString());
		
		return strings;
	}
}

