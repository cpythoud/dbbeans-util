package org.dbbeans.util.json;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;

public class JsonArrayOfDateTimesElement extends JsonArrayOfStringsElement {
	
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

