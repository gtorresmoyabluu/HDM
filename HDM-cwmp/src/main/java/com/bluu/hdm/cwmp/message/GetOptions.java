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
public class GetOptions extends Message {

    public GetOptions() {
        name = "GetOptions";
    }

    public GetOptions(String option) {
        name = "GetOptions";
        OptionName = option;
    }

    protected void createBody(SOAPBodyElement body, SOAPFactory spf) throws SOAPException {
        body.addChildElement("OptionName").setValue(OptionName);
    }

    protected void parseBody(SOAPBodyElement body, SOAPFactory f) throws SOAPException {
    }
    public String OptionName = "";
}
