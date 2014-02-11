package org.dbbeans.util.json;

public class JsonObjectElement extends JsonElement {
	
	public JsonObjectElement(final String name, final JsonObject value) {
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
		buf.append("\" = ");
		buf.append(value.print(indentLevel, false));
		if (!isLast)
			buf.append(",");
		buf.append("\n");
		
		return buf.toString();
	}
	
	private final JsonObject value;
}

