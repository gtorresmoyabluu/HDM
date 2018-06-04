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
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;

public class TransferComplete extends Message {

    /** Creates a new instance of TransferComplete */
    public TransferComplete() {
    }

    @Override
    protected void createBody(SOAPBodyElement body, SOAPFactory spf) throws SOAPException {
    }

    @Override
    protected void parseBody(SOAPBodyElement body, SOAPFactory spf) throws SOAPException {
        StartTime = getRequestElement(spf, body, "StartTime");
        CompleteTime = getRequestElement(spf, body, "CompleteTime");
        CommandKey = getRequestElement(spf, body, COMMAND_KEY);
        SOAPElement fault = getRequestChildElement(spf, body, "FaultStruct");
        if (fault != null) {
            FaultCode = Integer.parseInt(getRequestElement(spf, fault, "FaultCode"));
            FaultString = getRequestElement(spf, fault, "FaultString");
        } else {
            FaultCode = 0;
            FaultString = null;
        }
    }

    @Override
    public String toString() {
        String s = "TransferComplete: cmdkey=" + CommandKey + " faultcode=" + FaultCode + " faultstring=" + FaultString;
        return s;

    }
    public String CommandKey;
    public String StartTime;
    public String CompleteTime;
    public int FaultCode;
    public String FaultString;
}