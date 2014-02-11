package org.dbbeans.util.json;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a json element with an array of booleans value.
 */
public class JsonArrayOfBooleansElement extends JsonArrayOfStringsElement {

    /**
     * Creates the json element and gives it a name and value.
     * @param name of the element.
     * @param values the array value of the element.
     */
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

