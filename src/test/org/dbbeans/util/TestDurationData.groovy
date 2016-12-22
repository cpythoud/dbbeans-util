package test.org.dbbeans.util

import org.dbbeans.util.DurationData
import org.dbbeans.util.SimpleInputDurationFormat

class TestDurationData extends GroovyTestCase {

    void testAllMinutesConstructor() {
        final DurationData durationData = new DurationData(3513)

        assertEquals(2, durationData.days)
        assertEquals(10, durationData.hours)
        assertEquals(33, durationData.minutes)
        assertEquals(3513, durationData.totalMinutes)
    }

    void testFormat() {
        final DurationData minutes150 = new DurationData(150)
        final DurationData twoDaysPlus = new DurationData(3513)

        assertEquals("2h30", minutes150.format("d", "h", "m", true))
        assertEquals("2h 30m", minutes150.format("d", "h", "m", false))

        assertEquals("2d 10h33", twoDaysPlus.format("d", "h", "m", true))
        assertEquals("2d 10h 33m", twoDaysPlus.format("d", "h", "m", false))

        final SimpleInputDurationFormat simpleInputDurationFormat = new SimpleInputDurationFormat("d", "h", "m")

        assertEquals("2h30", minutes150.format(simpleInputDurationFormat, true))
        assertEquals("2h 30m", minutes150.format(simpleInputDurationFormat, false))
        assertEquals("2d 10h33", twoDaysPlus.format(simpleInputDurationFormat, true))
        assertEquals("2d 10h 33m", twoDaysPlus.format(simpleInputDurationFormat, false))
    }
}
