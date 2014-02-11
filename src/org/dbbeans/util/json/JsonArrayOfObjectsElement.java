package org.dbbeans.util.json;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a json element with an embedded array of json objects value.
 */
public class JsonArrayOfObjectsElement extends JsonElement {

    /**
     * Creates the json element and gives it a name and value.
     * @param name of the element.
     * @param values of the element.
     */
	public JsonArrayOfObjectsElement(final String name, final List<JsonObject> values) {
		super(name);
		this.values = new ArrayList<JsonObject>(values.size());
		for (JsonObject jo: values)
			this.values.add(new JsonObject(jo));
	}

    /**
     * Prints this JsonElement. This function is usually called from the {@link JsonObject#print(int, boolean)}
     * function of the enclosing JsonObject.
     * @param indentLevel the indentation level (how many tabs).
     * @param isLast must be true if this element is the last one in the json object, to prevent this function
     *               to print an extra comma.
     * @return a multiline representation of this JsonElement.
     */
	@Override
	public String print(final int indentLevel, final boolean isLast) {
		final String tabs = getTabs(indentLevel);
		
		final StringBuilder buf = new StringBuilder();
		
		buf.append(tabs);
		buf.append("\"");
		buf.append(getName());
		buf.append("\" = {\n");
		int index = 0;
		final int max = values.size();
		for (JsonObject jo: values) {
			index++;
			buf.append(jo.print(indentLevel + 1, true));
			if (index != max)
				buf.append(",");
			buf.append("\n");
		}
		buf.append(tabs);
		buf.append("}");
		if (!isLast)
			buf.append(",");
		buf.append("\n");
		
		return buf.toString();
	}
	
	private final List<JsonObject> values;
}

