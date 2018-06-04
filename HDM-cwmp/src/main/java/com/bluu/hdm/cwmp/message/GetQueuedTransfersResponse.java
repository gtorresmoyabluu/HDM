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

public class GetQueuedTransfersResponse extends Message {

    @Override
    protected void createBody(SOAPBodyElement body, SOAPFactory spf) throws SOAPException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void parseBody(SOAPBodyElement body, SOAPFactory spf) throws SOAPException {
        Iterator pi = getRequestChildElement(spf, body, "TransferList").getChildElements(spf.createName("QueuedTransferStruct"));
        Name nameCommandKey = spf.createName(COMMAND_KEY);
        Name nameState = spf.createName("State");
        TransferList = new Hashtable<>();
        while (pi.hasNext()) {
            SOAPElement param = (SOAPElement) pi.next();
            String key = getRequestElement(param, nameCommandKey);
            String state = getRequestElement(param, nameState);
            TransferList.put(key, state);
        }

    }
    public Hashtable<String, String> TransferList;
}
