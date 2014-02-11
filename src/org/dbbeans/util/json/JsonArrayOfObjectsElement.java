package org.dbbeans.util.json;

import java.util.ArrayList;
import java.util.List;

public class JsonArrayOfObjectsElement extends JsonElement {
	
	public JsonArrayOfObjectsElement(final String name, final List<JsonObject> values) {
		super(name);
		this.values = new ArrayList<JsonObject>(values.size());
		for (JsonObject jo: values)
			this.values.add(new JsonObject(jo));
	}
	
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

