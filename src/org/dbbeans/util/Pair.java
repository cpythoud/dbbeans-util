package org.dbbeans.util;

/**
 * Simple class to represent a pair of objects of arbitrary type. Useful when a function should return two values,
 * like a collection and some information about the collection that can only be easily gathered during the
 * collection creation. This class is immutable.
 */
public class Pair<E1, E2> {

    /**
     * The first pair element.
     */
    public final E1 e1;
    /**
     * The second pair element.
     */
    public final E2 e2;

    /**
     * Constructor to initialize immutable pair.
     * @param e1 first pair element
     * @param e2 second pair element
     */
    public Pair(final E1 e1, final E2 e2) {
        this.e1 = e1;
        this.e2 = e2;
    }
}
