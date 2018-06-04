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
public class InformResponse extends Message {

    /** Creates a new instance of InformResponse */
    public InformResponse() {
        name = "InformResponse";
    }

    public InformResponse(String _id, int me) {
        name = "InformResponse";
        id = _id;
        MaxEnvelopes = me;
    }

    @Override
    protected void createBody(SOAPBodyElement body, SOAPFactory spf) throws SOAPException {
        body.addChildElement(spf.createName("MaxEnvelopes")).setValue(String.valueOf(MaxEnvelopes));
    }

    @Override
    protected void parseBody(SOAPBodyElement body, SOAPFactory spf) throws SOAPException {
    }
    public int MaxEnvelopes;
}
