package org.dbbeans.util.json;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a json element with an array of dates and times value.
 */
public class JsonArrayOfDateTimesElement extends JsonArrayOfStringsElement {

    /**
     * Creates the json element and gives it a name and value.
     * @param name of the element.
     * @param values the array value of the element.
     */
	public JsonArrayOfDateTimesElement(final String name, final List<Timestamp> values) {
		super(name, getStringList(values));
	}

    private static List<String> getStringList(final List<Timestamp> timestamps) {
        final List<String> strings = new ArrayList<String>(timestamps.size());

        for (Timestamp timestamp: timestamps)
            strings.add(timestamp.toString());

        return strings;
    }
}

