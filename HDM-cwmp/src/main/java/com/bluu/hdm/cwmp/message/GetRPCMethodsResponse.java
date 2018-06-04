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
import java.util.ArrayList;
import java.util.Iterator;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;

public class GetRPCMethodsResponse extends Message {

    /** Creates a new instance of GetRPCMethodsResponse */
    public GetRPCMethodsResponse() {
    }

    public GetRPCMethodsResponse(GetRPCMethods req) {
        this.id = req.getId();
        methods = new String[]{"Inform", "TransferComplete", "GetRPCMethods"};
        name = "GetRPCMethodsResponse";
    }

    @Override
    protected void createBody(SOAPBodyElement body, SOAPFactory spf) throws SOAPException {
        SOAPElement mlst = body.addChildElement(spf.createName("MethodList"));
        mlst.setAttribute(SOAP_ARRAY_TYPE, "xsd:string[" + String.valueOf(methods.length) + "]");
        for (String m : methods) {
            mlst.addChildElement("string").setValue(m);
        }

    }

    @Override
    protected void parseBody(SOAPBodyElement body, SOAPFactory spf) throws SOAPException {
        SOAPElement ml = getRequestChildElement(spf, body, "MethodList");
        int i = getArrayCount(spf, ml);
//        Iterator mlist = ml.getChildElements(spf.createName("string"));
        Iterator mlist = ml.getChildElements();
        //methods = new String [i];
        ArrayList<String> m = new ArrayList<>();
        i = 0;
        while (mlist.hasNext()) {
            Object e = mlist.next();
            if (e instanceof SOAPElement) {
                SOAPElement el = (SOAPElement) e;
                if (el.getElementQName().getLocalPart().equals("string")) {
//                    methods[i++] = el.getValue();
                    m.add(el.getValue());
                }
            }
        }
        methods = m.toArray(new String[1]);
        /*
        mlist = ml.getChildElements(type.getType(body,spf));
        while (mlist.hasNext()) {
        methods[i++] = ((SOAPElement)mlist.next()).getValue();
        }
         */
    }
    public String[] methods;
}