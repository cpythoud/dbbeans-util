package org.dbbeans.util.json;

public class JsonStringElement extends JsonElement {
	
	public JsonStringElement(final String name, final String value) {
		super(name);
		this.value = value;
	}
	
	@Override
	public String print(final int indentLevel, final boolean isLast) {
		final String tabs = getTabs(indentLevel);
		
		final StringBuilder buf = new StringBuilder();
		
		buf.append(tabs);
		buf.append("\"");
		buf.append(getName());
		buf.append("\" = \"");
		buf.append(value);
		buf.append("\"");
		if (!isLast)
			buf.append(",");
		buf.append("\n");
		
		return buf.toString();
	}
	
	private final String value;
}

