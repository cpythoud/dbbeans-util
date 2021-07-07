package org.dbbeans.util;

/**
 * Simple class to represent a triplet of objects of arbitrary type. Useful when a function should return three values,
 * like a collection and some information about the collection that can only be easily gathered during the
 * collection creation. This class is immutable.
 */
public class Triplet<E1, E2, E3> {

    /**
     * The first triplet element.
     */
    public final E1 e1;
    /**
     * The second triplet element.
     */
    public final E2 e2;
    /**
     * The third triplet element.
     */
    public final E3 e3;

    /**
     * Constructor to initialize immutable triplet.
     * @param e1 first triplet element
     * @param e2 second triplet element
     * @param e3 second triplet element
     */
    public Triplet(final E1 e1, final E2 e2, final E3 e3) {
        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;
    }

    /**
     * Returns an hashcode composed with the hashcodes of the three elements of the triplet.
     * @return the hashcode
     */
    @Override
    public int hashCode() {
        int result = e1.hashCode();
        result = 31 * result + e2.hashCode();
        result = 31 * result + e3.hashCode();
        return result;
    }

    /**
     * Using Object.equals() or an overloaded version, compares two triplets and return true
     * if the first element of the first triplet equals the first element of the second triplet
     * and the second element of the first triplet equals the second element of the second triplet
     * and the third element of the first triplet equals the third element of the second triplet.
     * @param obj to be compared
     * @return true if the triplets are equal per the definition above, or else false.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (!(obj instanceof Triplet))
            return false;

        Triplet triplet = (Triplet) obj;
        return e1.equals(triplet.e1) && e2.equals(triplet.e2) && e3.equals(triplet.e3);
    }
}
