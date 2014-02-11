package org.dbbeans.util.json;

/**
 * This class represents a json element with a boolean value.
 */
public class JsonBooleanElement extends JsonStringElement {

    /**
     * Creates the json element and gives it a name and value.
     * @param name of the element.
     * @param value of the element.
     */
	public JsonBooleanElement(final String name, final boolean value) {
		super(name, calcVal(value));
	}

    /**
     * Returns a string representation (either "true" or "false") of a boolean value.
     * @param value to be converted to a String.
     * @return string representation of the boolean value.
     */
    protected static String calcVal(final boolean value) {
        if (value)
            return "true";

        return "false";
    }
}

