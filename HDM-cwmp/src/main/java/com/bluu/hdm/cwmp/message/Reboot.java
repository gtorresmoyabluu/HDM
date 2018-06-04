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

public class Reboot extends Message {

    /** Creates a new instance of Reboot */
    public Reboot() {
        name = "Reboot";
    }

    public Reboot(String CommandKey) {
        this();
        this.CommandKey = CommandKey;
    }

    @Override
    protected void createBody(SOAPBodyElement body, SOAPFactory spf) throws SOAPException {
        body.addChildElement(COMMAND_KEY).setValue(CommandKey);
    }

    @Override
    protected void parseBody(SOAPBodyElement body, SOAPFactory f) throws SOAPException {
    }
    public String CommandKey = "";
}