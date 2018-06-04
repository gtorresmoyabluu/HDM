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
public class DownloadResponse extends Message {

    /** Creates a new instance of DownloadResponse */
    public DownloadResponse() {
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
        String s = "DownloadResponse: Status = " + Status + " StartTime " + StartTime + " CompleteTime " + CompleteTime;
        return s;
    }
    public String StartTime;
    public String CompleteTime;
    public int Status;
}