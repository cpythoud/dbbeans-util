package org.dbbeans.util.json;

import java.sql.Time;

/**
 * This class represents a json element with a time value.
 */
public class JsonTimeElement extends JsonStringElement {

    /**
     * Creates the json element and gives it a name and value.
     * @param name of the element.
     * @param value of the element.
     */
	public JsonTimeElement(final String name, final Time value) {
		super(name, value.toString());
	}
}

