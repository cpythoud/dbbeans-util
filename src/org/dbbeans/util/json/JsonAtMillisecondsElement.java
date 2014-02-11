package org.dbbeans.util.json;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class JsonAtMillisecondsElement extends JsonStringElement {

	public JsonAtMillisecondsElement(final String name, final Date value) {
		super(name, calcVal(value.getTime()));
	}

	public JsonAtMillisecondsElement(final String name, final Time value) {
		super(name, calcVal(value.getTime()));
	}

    public JsonAtMillisecondsElement(final String name, final Timestamp value) {
        super(name, calcVal(value.getTime()));
    }
	
	protected static String calcVal(final long millis) {
		final StringBuilder buf = new StringBuilder();
		
		buf.append(INDICATOR);
		buf.append(millis);
		buf.append(INDICATOR);
		
		return buf.toString();
	}
	
	private final static String INDICATOR = "@";
}

