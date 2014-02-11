package org.dbbeans.util.json;

import java.sql.Date;

/**
 * This class represents a json element with a date value.
 */
public class JsonDateElement extends JsonStringElement {

    /**
     * Creates the json element and gives it a name and value.
     * @param name of the element.
     * @param value of the element.
     */
	public JsonDateElement(final String name, final Date value) {
		super(name, value.toString());
	}
}

