/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.model;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

/**
 *
 * @author Gonzalo Torres
 */
public class SOAPMessages {
    private static SOAPEnvelope soap;
    private static SOAPMessages instance;
    
    public static SOAPMessages getInstance(){
        if(instance == null){
            instance = new SOAPMessages();
        }
        return instance;
    }

    public static SOAPEnvelope getSoap() {
        return soap;
    }

    public static void setSoap(SOAPEnvelope soap) {
        SOAPMessages.soap = soap;
    }
    
    
    private static SOAPEnvelope createSoapEnvelope(SOAPMessage soapMessage){
        try {
            SOAPPart soapPart = soapMessage.getSOAPPart();
            soap = soapPart.getEnvelope();
            
        } catch (SOAPException ex) {
            Logger.getLogger(SOAPMessages.class.getName()).log(Level.SEVERE, null, ex);
        }
        return soap;
    }
}
