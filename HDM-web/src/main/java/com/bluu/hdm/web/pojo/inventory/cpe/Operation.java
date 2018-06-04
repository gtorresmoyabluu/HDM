/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.pojo.inventory.cpe;

import com.bluu.hdm.web.enums.OperationTypeEnm;
import java.io.Serializable;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Text;

public class Operation implements Serializable {

    @Attribute(name = "type")
    private OperationTypeEnm type;
    @Text(required = false)
    private String value;

    public Operation() {
    }

    public Operation(Operation opr) {
        this.type = opr.type;
        this.value = opr.value;
    }

    public Operation(OperationTypeEnm oprType, String oprValue) {
        this.type = oprType;
        this.value = oprValue;
    }

    public OperationTypeEnm getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
    
    public void setValue(String val) {
        this.value = val;
    }

    @Override
    public String toString() {
        return "Opr Type: " + type + ", Value: " + value;
    }
}

