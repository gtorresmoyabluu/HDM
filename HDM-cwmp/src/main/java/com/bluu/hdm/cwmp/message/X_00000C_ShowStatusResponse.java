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
import java.util.Iterator;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;

public class X_00000C_ShowStatusResponse extends Message {

    public Hashtable<String, String> response = new Hashtable<>();

    public X_00000C_ShowStatusResponse() {
        name = "X_00000C_ShowStatusResponse";
    }

    @Override
    protected void createBody(SOAPBodyElement body, SOAPFactory spf) throws SOAPException {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override
    protected void parseBody(SOAPBodyElement body, SOAPFactory spf) throws SOAPException {
        Iterator pi = getRequestChildElement(spf, body, "ExecResponseList").getChildElements(spf.createName("ExecResponseStruct"));
        Name nameKey = spf.createName("Command");
        Name nameValue = spf.createName("Response");
        while (pi.hasNext()) {
            SOAPElement param = (SOAPElement) pi.next();
            String key = getRequestElement(param, nameKey);
            String value = getRequestElement(param, nameValue);
            if (value == null) {
                value = "";
            }
            System.out.append(key + "->" + value);
            response.put(key, value);
        }
    }
}
