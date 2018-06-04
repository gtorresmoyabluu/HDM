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
public class Download extends Message {

    public static final String FT_FIRMWARE = "1 Firmware Upgrade Image";
    public static final String FT_WEBCONTENT = "2 Web Content";
    public static final String FT_CONFIG = "3 Vendor Configuration File";

    public String CommandKey = "";
    public String FileType = "";
    public String Version = "";
    public String url = "";
    public String UserName = "";
    public String Password = "";
    public String TargetFileName = "";
    public String SuccessUrl = "";
    public String FailureUrl = "";
    public long FileSize = 0;
    public int DelaySeconds = 0;

    /**
     * Creates a new instance of Download
     */
    public Download() {
        name = "Download";
//        TargetFileName = UserName = Password = SuccessUrl = FailureUrl = "";
    }

    public Download(String CommandKey, String url, String FileType) {
        name = "Download";
//        TargetFileName = UserName = Password = SuccessUrl = FailureUrl = "";
        this.CommandKey = CommandKey;
        this.url = url;
        this.FileType = FileType;
    }

    @Override
    protected void createBody(SOAPBodyElement body, SOAPFactory spf) throws SOAPException {
        body.addChildElement(COMMAND_KEY).setValue(CommandKey);
        body.addChildElement("FileType").setValue(FileType);
        body.addChildElement("URL").setValue(url);
        body.addChildElement("Version").setValue(Version);
        body.addChildElement("Username").setValue(UserName);
        body.addChildElement("Password").setValue(Password);
        body.addChildElement("FileSize").setValue(String.valueOf(FileSize));
        body.addChildElement("TargetFileName").setValue(TargetFileName);
        body.addChildElement("DelaySeconds").setValue(String.valueOf(DelaySeconds));
        body.addChildElement("SuccessURL").setValue(SuccessUrl);
        body.addChildElement("FailureURL").setValue(FailureUrl);
    }

    @Override
    protected void parseBody(SOAPBodyElement body, SOAPFactory f) throws SOAPException {
    }

}