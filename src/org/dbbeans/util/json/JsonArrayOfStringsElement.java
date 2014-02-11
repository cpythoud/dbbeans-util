package org.dbbeans.util.json;

import java.util.ArrayList;
import java.util.List;

public class JsonArrayOfStringsElement extends JsonElement {
	
	public JsonArrayOfStringsElement(final String name, final List<String> values) {
		super(name);
		this.values = new ArrayList<String>(values);
	}
	
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

