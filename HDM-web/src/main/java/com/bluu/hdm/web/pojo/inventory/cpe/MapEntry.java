package com.bluu.hdm.web.pojo.inventory.cpe;

import java.io.Serializable;

public class MapEntry implements Serializable {

    private final String key, value;

    public MapEntry(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
