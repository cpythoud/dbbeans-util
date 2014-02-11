package org.dbbeans.util.json;

import java.util.ArrayList;
import java.util.List;

public class JsonArrayOfIntegersElement extends JsonElement {
	
	public JsonArrayOfIntegersElement(final String name, final List<Long> values) {
		super(name);
		this.values = new ArrayList<Long>(values);
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
		for (long val: values) {
			index++;
			buf.append(val);
			if (index != max)
				buf.append(", ");
		}
		buf.append(" }");
		if (!isLast)
			buf.append(",");
		buf.append("\n");
		
		return buf.toString();
	}
	
	private final List<Long> values;
}

