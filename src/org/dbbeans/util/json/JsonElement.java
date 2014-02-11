package org.dbbeans.util.json;

public abstract class JsonElement {
	
	public JsonElement(final String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public abstract String print(final int indentLevel, final boolean isLast);
	
	@Override
	public String toString() {
		return print(0, true);
	}
	
	public static String getTabs(final int indentLevel) {
		if (indentLevel < 0)
			throw new IllegalArgumentException("indentLevel < 0 / indentLevel = " + indentLevel);
		
		if (indentLevel == 0)
			return "";
		
		final StringBuilder buf = new StringBuilder();
		
		for (int i = 0; i < indentLevel; i++)
			buf.append("\t");
		
		return buf.toString();
	}
	
	final private String name;
}

