package org.dbbeans.util.json;

import java.util.ArrayList;
import java.util.List;

public class JsonArrayOfBooleansElement extends JsonArrayOfStringsElement {

	public JsonArrayOfBooleansElement(final String name, final List<Boolean> values) {
		super(name, getStringList(values));
	}
	
	private static List<String> getStringList(final List<Boolean> values) {
		final List<String> strings = new ArrayList<String>(values.size());
		
		for (boolean val: values)
			strings.add(JsonBooleanElement.calcVal(val));
		
		return strings;
	}
}

