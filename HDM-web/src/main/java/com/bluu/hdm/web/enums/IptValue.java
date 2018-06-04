/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.enums;

import java.io.Serializable;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Text;

public class IptValue implements Serializable {

    @Attribute(name = "label", required = false)
    private String label;
    @Text
    private String value;

    public IptValue() {
    }

    public IptValue(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label == null ? value : label;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Ipv Label: " + getLabel() + ", Value: " + value;
    }
}
