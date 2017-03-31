package org.dbbeans.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * This class contains methods related to the management of java Exception.
 */
public class Exceptions {

    /**
     * Take any exception and returns its stack trace as a thing
     * @param throwable, the run time or checked exception which stack trace we are interested in
     * @return the throwable stack trace as a String
     */
    public static String getStackTrace(final Throwable throwable) {
        final StringWriter stringWriter = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(stringWriter);
        throwable.printStackTrace(printWriter);
        return stringWriter.toString();
    }
}
