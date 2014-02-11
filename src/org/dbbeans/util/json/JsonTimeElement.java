package org.dbbeans.util.json;

import java.sql.Time;

public class JsonTimeElement extends JsonStringElement {
	
	public JsonTimeElement(final String name, final Time value) {
		super(name, value.toString());
	}
}

