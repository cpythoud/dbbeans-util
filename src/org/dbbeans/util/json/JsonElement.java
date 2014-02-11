package org.dbbeans.util.json;

/**
 * This abstract class represents the common attribute of the elements that compose a {@link JsonObject}.
 */
public abstract class JsonElement {

    /**
     * Creates a new JsonElement and names it.
     * @param name of the element.
     */
	public JsonElement(final String name) {
		this.name = name;
	}

    /**
     * Get the name of this JsonElement.
     * @return name of this JsonElement.
     */
	public String getName() {
		return name;
	}

    /**
     * Prints this JsonElement. Each subclass implements the appropriate logic. This function is usually called
     * from the {@link JsonObject#print(int, boolean)} function of the enclosing JsonObject.
     * @param indentLevel the indentation level to be applied to evey printed lines (i.e., how many tabs).
     * @param isLast must be true if this element is the last one in the json object, to prevent this function
     *               to print an extra comma.
     * @return a (possibly multiline) string representation of this JsonElement.
     */
	public abstract String print(final int indentLevel, final boolean isLast);

    /**
     * Prints a (possibly multiline) string representation of this JsonElement, with no trailing comma.
     * @return a string representation of this JsonElement.
     */
	@Override
	public String toString() {
		return print(0, true);
	}

    /**
     * Given a certain indentation level, this function returns the appropriate number of tab characters.
     * @param indentLevel the indentation level.
     * @return tabs corresponding to the indentation level.
     */
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

