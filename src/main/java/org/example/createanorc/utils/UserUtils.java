package org.example.createanorc.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Iterator;

public class UserUtils {

    public JsonNode merge(JsonNode original, JsonNode patch) {
        Iterator<String> fieldNames = patch.fieldNames();
        while (fieldNames.hasNext()) {
            String name = fieldNames.next();
            JsonNode value = patch.get(name);

            if (value.isNull()) {                      // explicit null → delete
                ((ObjectNode) original).remove(name);
            } else if (value.isObject() &&
                    original.has(name) &&
                    original.get(name).isObject()) { // deep merge
                merge(original.get(name), value);
            } else {
                ((ObjectNode) original).replace(name, value);
            }
        }
        return original;
    }
}
