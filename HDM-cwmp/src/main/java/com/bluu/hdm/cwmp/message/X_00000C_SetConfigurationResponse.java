/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.message;

/**
 *
 * @author Gonzalo Torres
 */
import java.util.Hashtable;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;

public class X_00000C_SetConfigurationResponse extends Message {

    public Hashtable<String, String> response = new Hashtable<>();
    protected Integer Status;
    
    public X_00000C_SetConfigurationResponse() {
        name = "X_00000C_SetConfigurationResponse";
    }

    @Override
    protected void createBody(SOAPBodyElement body, SOAPFactory spf) throws SOAPException {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override
    protected void parseBody(SOAPBodyElement body, SOAPFactory spf) throws SOAPException {
        Status = Integer.parseInt(getRequestElement(spf, body, "Status"));
    }

    public Integer getStatus() {
        return Status;
    }
}
