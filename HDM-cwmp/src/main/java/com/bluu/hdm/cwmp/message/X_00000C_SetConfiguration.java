/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.message;

import java.util.ArrayList;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;

/**
 *
 * @author Gonzalo Torres
 */
public class X_00000C_SetConfiguration extends Message {

    public X_00000C_SetConfiguration() {
        name = "X_00000C_SetConfiguration";
    }

    @Override
    protected void createBody(SOAPBodyElement body, SOAPFactory spf) throws SOAPException {
        body.addChildElement(spf.createName("ErrorOption")).setValue(ErrorOption);
        body.addChildElement(spf.createName("Target")).setValue(Target);
        SOAPElement elm = body.addChildElement(spf.createName("ConfigCommandList"));
        elm.setAttribute(SOAP_ARRAY_TYPE, "xsd:string[" + String.valueOf(ConfigCommandList.size()) + "]");
        for (int i = 0; i < ConfigCommandList.size(); i++) {
            SOAPElement s = elm.addChildElement("string");
            s.setValue(ConfigCommandList.get(i));
        }
        body.addChildElement(spf.createName("ParameterKey")).setValue(ParameterKey);
    }

    @Override
    protected void parseBody(SOAPBodyElement body, SOAPFactory f) throws SOAPException {
        throw new UnsupportedOperationException("Not implemented.");
    }

    public void addCommand(String cmd) {
        ConfigCommandList.add(cmd);
    }
    protected String ErrorOption = "rollback";

    public void setErrorOption(String ErrorOption) {
        this.ErrorOption = ErrorOption;
    }
    protected String Target = "running-config";

    public void setTarget(String Target) {
        this.Target = Target;
    }
    protected String ParameterKey = "unsetparamkey";

    public void setParameterKey(String ParameterKey) {
        this.ParameterKey = ParameterKey;
    }
    private ArrayList<String> ConfigCommandList = new ArrayList<>();
}
