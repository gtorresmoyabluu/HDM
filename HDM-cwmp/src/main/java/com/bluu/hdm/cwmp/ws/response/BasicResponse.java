/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.ws.response;

import com.bluu.hdm.cwmp.model.NamedKeyValue;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 *
 * @author Gonzalo Torres
 */
public class BasicResponse extends TemplResponse<NamedKeyValue> {

    @Override
    @XmlElementWrapper(name = "Results")
    @XmlElement(name = "Item")
    public ArrayList<NamedKeyValue> getRetValue() {
        return super.getRetValue();
    }

    public void addResultValue(boolean retValue) {
        NamedKeyValue result = new NamedKeyValue();
        result.setKey("Result");
        result.setValue(retValue);
        this.addRetValue(result);
    }

    public void addValue(String key, String value) {
        NamedKeyValue result = new NamedKeyValue();
        result.setKey(key);
        result.setValue(value);
        this.addRetValue(result);
    }

    public void addValue(Object key, Object value) {
        NamedKeyValue result = new NamedKeyValue();
        result.setKey(String.valueOf(key));
        result.setValue(value);
        this.addRetValue(result);
    }
}
