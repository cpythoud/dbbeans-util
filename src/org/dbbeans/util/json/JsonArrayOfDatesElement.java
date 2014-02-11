package org.dbbeans.util.json;

import java.sql.Date;

import java.util.ArrayList;
import java.util.List;

public class JsonArrayOfDatesElement extends JsonArrayOfStringsElement {
	
	public JsonArrayOfDatesElement(final String name, final List<Date> values) {
		super(name, getStringList(values));
	}
	
	private static List<String> getStringList(final List<Date> dates) {
		final List<String> strings = new ArrayList<String>(dates.size());
		
		for (Date date: dates)
			strings.add(date.toString());
		
		return strings;
	}
}

