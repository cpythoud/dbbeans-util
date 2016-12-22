package test.org.dbbeans.util

import org.dbbeans.util.DurationData
import org.dbbeans.util.Pair
import org.dbbeans.util.SimpleInputDurationFormat

class TestSimpleInputDurationFormat extends GroovyTestCase {

    private static final SimpleInputDurationFormat SIDF = new SimpleInputDurationFormat("d", "h", "m")

    private final String TEST1 = "2d10h33m"
    private final String TEST2 = "2d 10h 33m"
    private final String TEST3 = " 2d 10h  33m "

    private final String TEST1B = "2d10h33"
    private final String TEST2B = "2d 10h 33"
    private final String TEST3B = " 2d 10h33 "

    void testCheckDigits() {
        assertTrue(SIDF.checkDigits("123"))
        assertTrue(SIDF.checkDigits("0123456789"))
        assertFalse(SIDF.checkDigits('abc'))
        assertFalse(SIDF.checkDigits('-33'))
        assertFalse(SIDF.checkDigits('45.67'))
        assertFalse(SIDF.checkDigits(''))
    }

    void testGetDigitsAndOffset() {
        Pair<String, Integer> pair = SIDF.getDigitsAndOffset(TEST1, 0, 'd', false)
        assertEquals('2', pair.e1)
        assertEquals(2, pair.e2)

        pair = SIDF.getDigitsAndOffset(TEST1, 2, 'h', false)
        assertEquals('10', pair.e1)
        assertEquals(5, pair.e2)

        pair = SIDF.getDigitsAndOffset(TEST1, 5, 'm', false)
        assertEquals('33', pair.e1)
        assertEquals(8, pair.e2)
    }


    void testSimpleCases() {
        assertTrue(SIDF.validate(TEST1))
        assertTrue(SIDF.validate(TEST2))
        assertTrue(SIDF.validate(TEST3))
    }

    void testMissingMinuteUnits() {
        assertTrue(SIDF.validate(TEST1B))
        assertTrue(SIDF.validate(TEST2B))
        assertTrue(SIDF.validate(TEST3B))
    }

    void testParse() {
        DurationData durationData = SIDF.parse(TEST1)
        assertEquals(2, durationData.days)
        assertEquals(10, durationData.hours)
        assertEquals(33, durationData.minutes)
        assertEquals(3513, durationData.totalMinutes)
    }
}
