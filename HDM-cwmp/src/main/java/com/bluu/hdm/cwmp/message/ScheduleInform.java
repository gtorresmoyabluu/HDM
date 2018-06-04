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

public class ScheduleInform extends Message {

    public ScheduleInform() {
        name = "ScheduleInform";
    }

    public ScheduleInform(int DelaySeconds, String CommandKey) {
        this();
        this.CommandKey = CommandKey;
        this.DelaySeconds = DelaySeconds;
    }

    @Override
    protected void createBody(SOAPBodyElement body, SOAPFactory spf) throws SOAPException {
        body.addChildElement(COMMAND_KEY).setValue(CommandKey);
        body.addChildElement("DelaySeconds").setValue(((Integer) DelaySeconds).toString());
    }

    @Override
    protected void parseBody(SOAPBodyElement body, SOAPFactory f) throws SOAPException {
    }
    public String CommandKey;
    public int DelaySeconds;
}