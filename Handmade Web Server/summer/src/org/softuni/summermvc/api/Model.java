package org.softuni.summermvc.api;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Model {
    private Map<String, Object> attributes;

    public Model() {
        this.attributes = new LinkedHashMap<>();
    }

    public Map<String, Object> getAttributes() {
        return Collections.unmodifiableMap(this.attributes);
    }

    public void addAttribute(String key, Object value){
        this.attributes.put(key,value);
    }

    public void removeAttribute(String name) {
         this.attributes.remove(name);
    }
}
