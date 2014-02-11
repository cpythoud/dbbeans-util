package org.dbbeans.util.json;

import java.sql.Time;

import java.util.ArrayList;
import java.util.List;

public class JsonArrayOfTimesElement extends JsonArrayOfStringsElement {
	
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

