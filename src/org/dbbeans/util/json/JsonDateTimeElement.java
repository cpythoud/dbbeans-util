package org.dbbeans.util.json;

import java.sql.Timestamp;

/**
 * This class represents a json element with a date and time value.
 */
public class JsonDateTimeElement extends JsonStringElement {

    /**
     * Creates the json element and gives it a name and value.
     * @param name of the element.
     * @param value of the element.
     */
	public JsonDateTimeElement(final String name, final Timestamp value) {
        super(name, value.toString());
	}
}

