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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;

public class SetParameterAttributes extends Message {

    private class SetParameterAttributesStruct implements Serializable {

        public String Name;
        public boolean NotificationChange;
        public int Notification;
        public boolean AccessListChange;
        public String[] AccessList;

        SetParameterAttributesStruct(String Name, boolean NotificationChange, int Notification, boolean AccessListChange, String[] AccessList) {
            this.Name = Name;
            this.NotificationChange = NotificationChange;
            this.Notification = Notification;
            this.AccessList = AccessList;
            this.AccessListChange = AccessListChange;
        }
    }

    public SetParameterAttributes() {
        name = "SetParameterAttributes";
        attrs = new ArrayList<SetParameterAttributesStruct>();
    }

    public void AddAttribute(String Name, boolean NotificationChange, int Notification, boolean AccessListChange, String[] AccessList) {
        attrs.add(new SetParameterAttributesStruct(Name, NotificationChange, Notification, AccessListChange, AccessList));
    }

    @Override
    protected void createBody(SOAPBodyElement body, SOAPFactory spf) throws SOAPException {
        SOAPElement elm = body.addChildElement(spf.createName("ParameterList"));
        elm.setAttribute(SOAP_ARRAY_TYPE, "cwmp:SetParameterAttributesStruct[" + String.valueOf(attrs.size()) + "]");
        int c = attrs.size();
        for (int i = 0; i < c; i++) {
            SOAPElement param = elm.addChildElement("SetParameterAttributesStruct");
            param.addChildElement("Name").setValue(attrs.get(i).Name);
            param.addChildElement("NotificationChange").setValue(b2s(attrs.get(i).NotificationChange));
            param.addChildElement("Notification").setValue(String.valueOf(attrs.get(i).Notification));

            param.addChildElement("AccessListChange").setValue(b2s(attrs.get(i).AccessListChange));

            SOAPElement al = param.addChildElement(spf.createName("AccessList"));
            String acl[] = attrs.get(i).AccessList;
            int ca = acl.length;
            al.setAttribute(SOAP_ARRAY_TYPE, "xsd:string[" + String.valueOf(ca) + "]");
            for (int i2 = 0; i2 < ca; i2++) {
                SOAPElement acle = al.addChildElement("string");
                acle.setValue(acl[i2]);
                acle.setAttribute(XSI_TYPE, XSD_STRING);
            }

        }
    }

    @Override
    protected void parseBody(SOAPBodyElement body, SOAPFactory f) throws SOAPException {
    }
    private List<SetParameterAttributesStruct> attrs;
}