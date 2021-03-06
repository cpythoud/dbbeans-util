package org.dbbeans.util.json;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a json element with an array of booleans value.
 */
public class JsonArrayOfBooleansElement extends JsonElement {

	private final List<Boolean> values;

    /**
     * Creates the json element and gives it a name and value.
     * @param name of the element.
     * @param values the array value of the element.
     */
	public JsonArrayOfBooleansElement(final String name, final List<Boolean> values) {
		super(name);
		this.values = new ArrayList<Boolean>(values);
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
		buf.append("\" : [ ");
		int index = 0;
		final int max = values.size();
		for (boolean val: values) {
			index++;
			buf.append(val);
			if (index != max)
				buf.append(", ");
		}
		buf.append(" ]");
		if (!isLast)
			buf.append(",");
		buf.append("\n");

		return buf.toString();
	}
}

