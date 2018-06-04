/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.model;

/**
 *
 * @author Gonzalo Torres
 */
public class NamedKeyValue {

    private String key;
    private String value;

    public NamedKeyValue() {
    }

    public NamedKeyValue(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setValue(Object value) {
        this.value = String.valueOf(value);
    }
}
