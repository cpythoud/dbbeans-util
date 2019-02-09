package test.org.dbbeans.util

import org.dbbeans.util.Spreadsheets

class TestSpreadsheetColumns extends GroovyTestCase {

    void testPrintout() {
        for (int i = 1; i < 20000; ++i)
            println(i + " = " + Spreadsheets.getColumnLetters(i))
    }

    void testIndexes() {
        assertEquals(1, Spreadsheets.getColumnIndex("A"))
        assertEquals(26, Spreadsheets.getColumnIndex("Z"))
        assertEquals(27, Spreadsheets.getColumnIndex("AA"))
        assertEquals(58, Spreadsheets.getColumnIndex("BF"))
        assertEquals(1744, Spreadsheets.getColumnIndex("BOB"))
    }
}
