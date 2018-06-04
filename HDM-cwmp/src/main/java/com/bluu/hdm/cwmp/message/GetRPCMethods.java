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
import javax.xml.soap.*;

public class GetRPCMethods extends Message {

    /** Creates a new instance of GetRPCMethods */
    public GetRPCMethods() {
        name = "GetRPCMethods";
    }

    public GetRPCMethods(String _id) {
        name = "GetRPCMethods";
        id = _id;
    }

    @Override
    protected void createBody(SOAPBodyElement body, SOAPFactory spf) throws SOAPException {
    }

    @Override
    protected void parseBody(SOAPBodyElement body, SOAPFactory f) throws SOAPException {
    }
}