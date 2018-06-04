/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.message;

import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;

/**
 *
 * @author Gonzalo Torres
 */
public class DeleteObject extends Message {

    public DeleteObject() {
        name = "DeleteObject";
    }

    public DeleteObject(String ObjectName, String CommandKey) {
        this();
        this.ParameterKey = CommandKey;
        this.ObjectName = ObjectName;
    }
    public String ParameterKey = "";
    public String ObjectName = "";

    @Override
    protected void createBody(SOAPBodyElement body, SOAPFactory spf) throws SOAPException {
        body.addChildElement("ObjectName").setValue(ObjectName);
        body.addChildElement(PARAMETER_KEY).setValue(ParameterKey);
    }

    @Override
    protected void parseBody(SOAPBodyElement body, SOAPFactory f) throws SOAPException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
