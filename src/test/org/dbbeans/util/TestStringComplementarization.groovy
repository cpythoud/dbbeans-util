package test.org.dbbeans.util

import org.dbbeans.util.Strings

class TestStringComplementarization extends GroovyTestCase {

    void testBasicComplementarization() {
        assertEquals("000000010%", Strings.complementString("10%", "0", 10, true))
        assertEquals("xyxyxyxy10%", Strings.complementString("10%", "xy", 10, true))
        //System.out.println(Strings.complementString("10%", "xy", 10, true))
    }
}
