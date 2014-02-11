package org.dbbeans.util.json;

import java.sql.Date;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a json element with an array of dates value.
 */
public class JsonArrayOfDatesElement extends JsonArrayOfStringsElement {

    /**
     * Creates the json element and gives it a name and value.
     * @param name of the element.
     * @param values the array value of the element.
     */
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

