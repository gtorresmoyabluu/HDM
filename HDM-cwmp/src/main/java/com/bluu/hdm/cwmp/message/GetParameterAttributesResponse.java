/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.message;

import java.io.Serializable;
import java.util.Iterator;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;

/**
 *
 * @author Gonzalo Torres
 */
public class GetParameterAttributesResponse  extends Message {

    /** Creates a new instance of GetParameterAttributes */
    public GetParameterAttributesResponse() {
        name = "GetParameterAttributesResponse";
    }

    @Override
    protected void createBody(SOAPBodyElement body, SOAPFactory spf) throws SOAPException {
    }

    @Override
    protected void parseBody(SOAPBodyElement body, SOAPFactory spf) throws SOAPException {
        SOAPElement ml = getRequestChildElement(spf, body, "ParameterList");
        int i = getArrayCount(spf, ml);
        Iterator mlist = ml.getChildElements(spf.createName("ParameterAttributeStruct"));
        attributes = new ParameterAttributeStruct[i];
        Name nameKey = spf.createName("Name");
        Name nameNotification = spf.createName("Notification");
        Name nameAccessList = spf.createName("AccessList");
        Name nameString = spf.createName("string");
        //System.out.println ("start "+i);
        i = 0;
        while (mlist.hasNext()) {
            SOAPElement param = (SOAPElement) mlist.next();
            attributes[i] = new ParameterAttributeStruct();
            attributes[i].Name = getRequestElement(param, nameKey);
            attributes[i].Notification = Integer.parseInt(getRequestElement(param, nameNotification));
            //System.out.println ("Attrbiute: name="+attributes[i].Name+", notification="+attributes[i].Notification);
            // get acl array        
            SOAPElement elementAccessList = getRequestChildElement(spf, param, "AccessList");
            int ii = getArrayCount(spf, elementAccessList);
            attributes[i].AccessList = new String[ii];

            System.out.println("Access list length: " + ii);
            ii = 0;
            Iterator iteratorAccessList = elementAccessList.getChildElements(nameString);
            while (iteratorAccessList.hasNext()) {
                attributes[i].AccessList[ii++] = ((SOAPElement) iteratorAccessList.next()).getValue();
                //System.out.println ("acl= "+attributes[i].AccessList[ii-1]);
            }
            i++;
        }

    }
    public ParameterAttributeStruct attributes[];

    public class ParameterAttributeStruct implements Serializable {

        ParameterAttributeStruct() {
        }
        public String Name;
        public int Notification;
        public String AccessList[];
    }
}
