package org.dbbeans.util.json;

import java.sql.Date;

public class JsonDateElement extends JsonStringElement {
	
	public JsonDateElement(final String name, final Date value) {
		super(name, value.toString());
	}
}

