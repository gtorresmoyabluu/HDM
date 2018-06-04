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

public class UploadResponse extends Message {

    /** Creates a new instance of GetParameterValues */
    public UploadResponse() {
        name = "UploadResponse";
    }

    @Override
    protected void createBody(SOAPBodyElement body, SOAPFactory spf) throws SOAPException {
    }

    @Override
    protected void parseBody(SOAPBodyElement body, SOAPFactory spf) throws SOAPException {
        StartTime = getRequestElement(spf, body, "StartTime");
        CompleteTime = getRequestElement(spf, body, "CompleteTime");
        Status = Integer.parseInt(getRequestElement(spf, body, "Status"));
    }

    @Override
    public String toString() {
        String s = name + ": StartTime=" + StartTime + " CompleteTIme=" + CompleteTime + " Status=" + Status;
        return s;

    }
    public String StartTime;
    public String CompleteTime;
    public int Status;
}