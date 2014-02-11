package org.dbbeans.util.json;

import java.util.ArrayList;
import java.util.List;

public class JsonObject {
	
	public JsonObject() {
		elements = new ArrayList<JsonElement>();
	}
	
	public JsonObject(final JsonObject jo) {
		elements = new ArrayList<JsonElement>(jo.elements);
	}
	
	public void addElement(final JsonElement element) {
		elements.add(element);
	}
	
	public void reset() {
		elements.clear();
	}
	
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
	
	@Override
	public String toString() {
		return print(0, true);
	}
	
	private List<JsonElement> elements;
}
