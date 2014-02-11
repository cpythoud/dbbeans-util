package org.dbbeans.util.json;

import java.sql.Timestamp;

public class JsonDateTimeElement extends JsonStringElement {
	
	public JsonDateTimeElement(final String name, final Timestamp value) {
        super(name, value.toString());
	}
}

