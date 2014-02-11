package org.dbbeans.util.json;

/**
 * This class represents a json element with an embedded json object value.
 */
public class JsonObjectElement extends JsonElement {

    /**
     * Creates the json element and gives it a name and value.
     * @param name of the element.
     * @param value of the element.
     */
	public JsonObjectElement(final String name, final JsonObject value) {
		super(name);
		this.value = value;
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
		buf.append("\" = ");
		buf.append(value.print(indentLevel, false));
		if (!isLast)
			buf.append(",");
		buf.append("\n");
		
		return buf.toString();
	}
	
	private final JsonObject value;
}

