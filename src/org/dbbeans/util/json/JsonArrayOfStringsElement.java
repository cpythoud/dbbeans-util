package org.dbbeans.util.json;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a json element with an array of strings value.
 */
public class JsonArrayOfStringsElement extends JsonElement {

    /**
     * Creates the json element and gives it a name and value.
     * @param name of the element.
     * @param values the array value of the element.
     */
	public JsonArrayOfStringsElement(final String name, final List<String> values) {
		super(name);
		this.values = new ArrayList<String>(values);
	}

    /**
     * Prints this JsonElement. This function is usually called from the {@link JsonObject#print(int, boolean)}
     * function of the enclosing JsonObject.
     * @param indentLevel the indentation level (how many tabs).
     * @param isLast must be true if this element is the last one in the json object, to prevent this function
     *               to print an extra comma.
     * @return a representation of this JsonElement.
     */
	@Override
	public String print(final int indentLevel, final boolean isLast) {
		final String tabs = getTabs(indentLevel);
		
		final StringBuilder buf = new StringBuilder();
		
		buf.append(tabs);
		buf.append("\"");
		buf.append(getName());
		buf.append("\" = { ");
		int index = 0;
		final int max = values.size();
		for (String val: values) {
			index++;
			buf.append("\"");
			buf.append(val);
			buf.append("\"");
			if (index != max)
				buf.append(", ");
		}
		buf.append(" }");
		if (!isLast)
			buf.append(",");
		buf.append("\n");
		
		return buf.toString();
	}
	
	private final List<String> values;
}

