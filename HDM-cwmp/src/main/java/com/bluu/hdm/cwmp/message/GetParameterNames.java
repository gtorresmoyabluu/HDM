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
public class GetParameterNames extends Message {

    /** Creates a new instance of GetParameterNames */
    public GetParameterNames() {
        name = "GetParameterNames";
        parameterPath = ".";
        nextLevel = false;
    }

    public GetParameterNames(String parameterPath, boolean nextLevel) {
        this();
        this.parameterPath = parameterPath;
        this.nextLevel = nextLevel;
    }

    protected void createBody(SOAPBodyElement body, SOAPFactory spf) throws SOAPException {
        body.addChildElement(spf.createName("ParameterPath")).setValue(parameterPath);
        body.addChildElement(spf.createName("NextLevel")).setValue(nextLevel ? "1" : "0");
    }

    protected void parseBody(SOAPBodyElement body, SOAPFactory f) throws SOAPException {
    }
    public String parameterPath;
    public boolean nextLevel;
}