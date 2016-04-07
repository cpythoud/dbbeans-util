package org.dbbeans.util.json;

/**
 * This class represents a json element with a null value.
 */
public class JsonNullElement extends JsonElement {

    /**
     * Creates the json element and gives it a name.
     * @param name of the element.
     */
    public JsonNullElement(final String name) {
        super(name);
    }

    /**
     * Prints this JsonElement. This function is usually called from the {@link JsonObject#print(int, boolean)}
     * function of the enclosing JsonObject.
     * @param indentLevel the indentation level (how many tabs).
     * @param isLast must be true if this element is the last one in the json object, to prevent this function
     *               to print an extra comma.
     * @return a representation of this JsonElement.
     */
    @Override
    public String print(final int indentLevel, final boolean isLast) {
        final String tabs = getTabs(indentLevel);

        final StringBuilder buf = new StringBuilder();

        buf.append(tabs);
        buf.append("\"");
        buf.append(getName());
        buf.append("\" : null");
        if (!isLast)
            buf.append(",");
        buf.append("\n");

        return buf.toString();
    }
}
