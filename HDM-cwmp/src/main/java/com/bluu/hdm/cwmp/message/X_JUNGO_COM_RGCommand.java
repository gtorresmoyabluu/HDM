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
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;

public class X_JUNGO_COM_RGCommand extends Message {
    
    private String RGCommand = "";
    
    public X_JUNGO_COM_RGCommand() {
        name = "X_JUNGO_COM_RGCommand";
    }

    public X_JUNGO_COM_RGCommand(String RGCommand) {
        this();
        this.RGCommand = RGCommand;
    }

    @Override
    protected void createBody(SOAPBodyElement body, SOAPFactory spf) throws SOAPException {
        body.addChildElement(spf.createName("RGCommand")).setValue(RGCommand);
    }

    @Override
    protected void parseBody(SOAPBodyElement body, SOAPFactory f) throws SOAPException {
        throw new UnsupportedOperationException("Not implemented.");
    }
}
