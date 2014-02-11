package org.dbbeans.util.json;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a json object that contains elements, defined by the other classes in this package.
 */
public class JsonObject {

    /**
     * Creates a new, empty JsonObject.
     */
	public JsonObject() {
		elements = new ArrayList<JsonElement>();
	}

    /**
     * Creates a new JsonObject by copying the elements in an other JsonObject.
     * @param jsonObject that contains the elements to be copied in this JsonObject.
     */
	public JsonObject(final JsonObject jsonObject) {
		elements = new ArrayList<JsonElement>(jsonObject.elements);
	}

    /**
     * Add an element to this JsonObject.
     * @param element to be added.
     */
	public void addElement(final JsonElement element) {
		elements.add(element);
	}

    /**
     * Removes all elements from this JsonObject.
     */
	public void reset() {
		elements.clear();
	}

    /**
     * Prints this JsonObject.
     * @param indentLevel the indentation level to be applied to evey printed lines (i.e., how many tabs).
     * @param initialTabs to indicate if the indentation level should be applied to the first line. It should
     *                    usually not be the case if you plan to display text before the opening bracket.
     * @return a multiline string representation of this JsonObject.
     */
	public String print(final int indentLevel, final boolean initialTabs) {
		final String tabs = JsonElement.getTabs(indentLevel);
		
		final StringBuilder buf = new StringBuilder();
		
		if (initialTabs)
			buf.append(tabs);
		buf.append("{\n");
		int index = 0;
		final int max = elements.size();
		for (JsonElement element: elements) {
			index++;
			buf.append(element.print(indentLevel + 1, index == max));
		}
		buf.append(tabs);
		buf.append("}");
		
		return buf.toString();
	}

    /**
     * Prints a multiline string representation of this JsonObject.
     * @return a multiline string representation of this JsonObject.
     */
	@Override
	public String toString() {
		return print(0, true);
	}
	
	private List<JsonElement> elements;
}
