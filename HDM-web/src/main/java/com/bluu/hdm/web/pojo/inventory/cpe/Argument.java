/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.pojo.inventory.cpe;

import java.io.Serializable;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Text;

public class Argument implements Serializable {

    @Attribute(name = "id")
    private String id;
    @Text
    private String value;

    public Argument() {
    }

    public Argument(String id, String val) {
        this.id = id;
        this.value = val;
    }

    public String getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String val) {
        this.value = val;
    }

    @Override
    public String toString() {
        return "Arg Id: " + id + ", Value: " + value;
    }
}
