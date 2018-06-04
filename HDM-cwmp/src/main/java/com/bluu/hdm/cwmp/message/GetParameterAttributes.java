/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.message;

import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;

/**
 *
 * @author Gonzalo Torres
 */
public class GetParameterAttributes extends Message {

    /** Creates a new instance of GetParameterAttributes */
    public GetParameterAttributes() {
        name = "GetParameterAttributes";
    }

    @Override
    protected void createBody(SOAPBodyElement body, SOAPFactory spf) throws SOAPException {
        SOAPElement elm = body.addChildElement(spf.createName("ParameterNames"));
        elm.setAttribute(SOAP_ARRAY_TYPE, "xsd:string[" + String.valueOf(parameterNames.length) + "]");
        for (int i = 0; i < parameterNames.length; i++) {
            elm.addChildElement("string").setValue(parameterNames[i]);
        }
    }

    @Override
    protected void parseBody(SOAPBodyElement body, SOAPFactory f) throws SOAPException {
    }
    public String parameterNames[];
}
