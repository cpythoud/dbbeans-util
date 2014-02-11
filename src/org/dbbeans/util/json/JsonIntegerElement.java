package org.dbbeans.util.json;

public class JsonIntegerElement extends JsonElement {
	
	public JsonIntegerElement(final String name, final long value) {
		super(name);
		this.value = value;
	}

    public JsonIntegerElement(final String name, final String value) {
        super(name);
        this.value = Long.valueOf(value);
    }
	
	@Override
	public String print(final int indentLevel, final boolean isLast) {
		final String tabs = getTabs(indentLevel);
		
		final StringBuilder buf = new StringBuilder();
		
		buf.append(tabs);
		buf.append("\"");
		buf.append(getName());
		buf.append("\" = ");
		buf.append(value);
		if (!isLast)
			buf.append(",");
		buf.append("\n");
		
		return buf.toString();
	}
	
	private final long value;
}

