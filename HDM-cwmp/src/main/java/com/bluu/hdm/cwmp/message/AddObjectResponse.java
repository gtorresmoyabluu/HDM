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
public class AddObjectResponse extends Message {

    public AddObjectResponse() {
        name = "AddObjectResponse";
    }

    @Override
    protected void createBody(SOAPBodyElement body, SOAPFactory spf) throws SOAPException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void parseBody(SOAPBodyElement body, SOAPFactory spf) throws SOAPException {
        InstanceNumber = Integer.parseInt(getRequestElement(spf, body, "InstanceNumber"));
        Status = Integer.parseInt(getRequestElement(spf, body, "Status"));
    }

    @Override
    public String toString() {
        return name + ": status=" + Status + " instance=" + InstanceNumber;
    }
    public int InstanceNumber;
    public int Status;
}
