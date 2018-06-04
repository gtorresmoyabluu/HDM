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

public class X_JUNGO_COM_RGCommandResponse extends Message {

    public X_JUNGO_COM_RGCommandResponse() {
        name = "X_JUNGO_COM_RGCommandResponse";
    }

    @Override
    protected void createBody(SOAPBodyElement body, SOAPFactory spf) throws SOAPException {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override
    protected void parseBody(SOAPBodyElement body, SOAPFactory spf) throws SOAPException {
        Status = getRequestElement(spf, body, "Status");
        Result = getRequestElement(spf, body, "Result");
    }

    public String getStatus() {
        return this.Status;
    }

    public String getResult() {
        return Result;
    }
    private String Result = "";
    private String Status = "";
}
