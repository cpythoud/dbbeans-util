package test.org.dbbeans.util

import org.dbbeans.util.English

class TestPluralization extends GroovyTestCase {

    void testInvariable() {
        assertEquals("equipment", English.pluralize("equipment"))
    }

    void testAdHocPlural() {
        assertEquals("children", English.pluralize("child"))
    }

    void testPatterns() {
        assertEquals("wolves", English.pluralize("wolf"))
        assertEquals("wives", English.pluralize("wife"))
        assertEquals("men", English.pluralize("man"))
        assertEquals("yeomen", English.pluralize("yeoman"))
        assertEquals("waterways", English.pluralize("waterway"))
        assertEquals("worries", English.pluralize("worry"))
        assertEquals("quizzes", English.pluralize("quiz"))
        assertEquals("mice", English.pluralize("mouse"))
        assertEquals("matrices", English.pluralize("matrix"))
        assertEquals("viruses", English.pluralize("virus"))
        assertEquals("witches", English.pluralize("witch"))
        assertEquals("wishes", English.pluralize("wish"))
        assertEquals("jukeboxes", English.pluralize("jukebox"))
        assertEquals("houses", English.pluralize("house"))
        assertEquals("juices", English.pluralize("juice"))

        assertEquals("sausages", English.pluralize("sausage"))
        assertEquals("statuses", English.pluralize("status"))
        assertEquals("axes", English.pluralize("ax"))
        assertEquals("axes", English.pluralize("axe"))
        assertEquals("crushes", English.pluralize("crush"))
        assertEquals("crutches", English.pluralize("crutch"))
        assertEquals("matrices", English.pluralize("matrix"))
        assertEquals("indices", English.pluralize("index"))
        assertEquals("days", English.pluralize("day"))
        assertEquals("skies", English.pluralize("sky"))
    }
}
