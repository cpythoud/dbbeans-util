package test.org.dbbeans.util

import org.dbbeans.util.English

class TestSingularization extends GroovyTestCase {

    void testInvariable() {
        assertEquals("equipment", English.singularize("equipment"))
    }

    void testAdHocPlural() {
        assertEquals("child", English.singularize("children"))
        assertEquals("index", English.singularize("indices"))
        assertEquals("matrix", English.singularize("matrices"))
    }

    void testPatterns() {
        assertEquals("wife", English.singularize("wives"))
        assertEquals("wolf", English.singularize("wolves"))
        assertEquals("man", English.singularize("men"))
        assertEquals("yeoman", English.singularize("yeomen"))
        assertEquals("waterway", English.singularize("waterways"))
        assertEquals("worry", English.singularize("worries"))
        assertEquals("quiz", English.singularize("quizzes"))
        assertEquals("mouse", English.singularize("mice"))
        assertEquals("spice", English.singularize("spices"))
        assertEquals("juice", English.singularize("juices"))
        assertEquals("invoice", English.singularize("invoices"))
        assertEquals("witch", English.singularize("witches"))
        assertEquals("wish", English.singularize("wishes"))
        assertEquals("jukebox", English.singularize("jukeboxes"))
        assertEquals("house", English.singularize("houses"))

        assertEquals("sausage", English.singularize("sausages"))
        assertEquals("status", English.singularize("statuses"))
        assertEquals("axe", English.singularize("axes"))
        assertEquals("climax", English.singularize("climaxes"))
        assertEquals("crush", English.singularize("crushes"))
        assertEquals("crutch", English.singularize("crutches"))
        assertEquals("day", English.singularize("days"))
        assertEquals("sky", English.singularize("skies"))
    }
}
