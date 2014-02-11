package org.dbbeans.util.json;

public class JsonBooleanElement extends JsonStringElement {

	public JsonBooleanElement(final String name, final boolean value) {
		super(name, calcVal(value));
	}

    protected static String calcVal(final boolean value) {
        if (value)
            return "true";

        return "false";
    }
}

