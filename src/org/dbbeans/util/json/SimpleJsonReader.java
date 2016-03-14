package org.dbbeans.util.json;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chris on 2015-10-22.
 */
public class SimpleJsonReader {

    private final String jsonText;
    private final JsonObject jsonObject = new JsonObject();
    private final Map<String, JsonObject> values = new HashMap<String, JsonObject>();
    private final boolean valid;

    public SimpleJsonReader(final String jsonText) {
        this.jsonText = jsonText;

        valid = parseJson();
    }

    private boolean parseJson() {
        //final String data = removeCurlyBraces();



        return true;
    }
}
