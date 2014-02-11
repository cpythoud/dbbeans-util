package org.dbbeans.util.json;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * This class represents a json element with a milliseconds value. When printed, the value is preceded by an @
 * character according to the json standard.
 */
public class JsonAtMillisecondsElement extends JsonStringElement {

    /**
     * Creates the json element from a Date object and gives it a name and value.
     * @param name of the element.
     * @param value of the element.
     */
	public JsonAtMillisecondsElement(final String name, final Date value) {
		super(name, calcVal(value.getTime()));
	}

    /**
     * Creates the json element from a Time object and gives it a name and value.
     * @param name of the element.
     * @param value of the element.
     */
	public JsonAtMillisecondsElement(final String name, final Time value) {
		super(name, calcVal(value.getTime()));
	}

    /**
     * Creates the json element from a Timestamp object and gives it a name and value.
     * @param name of the element.
     * @param value of the element.
     */
    public JsonAtMillisecondsElement(final String name, final Timestamp value) {
        super(name, calcVal(value.getTime()));
    }

    /**
     * Returns a string representation of a milliseconds value. The digits are preceded by an @ character.
     * @param millis milliseconds value to be converted to a String.
     * @return string representation of the milliseconds value.
     */
	protected static String calcVal(final long millis) {
        return INDICATOR + millis + INDICATOR;
	}
	
	private final static String INDICATOR = "@";
}

