/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.message;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;

/**
 *
 * @author Gonzalo Torres
 */
public class GetParameterNamesResponse extends Message {

    /** Creates a new instance of GetParameterNamesResponse */
    public GetParameterNamesResponse() {
        name = "GetParameterNamesResponse";
    }

    @Override
    protected void createBody(SOAPBodyElement body, SOAPFactory spf) throws SOAPException {
    }

    @Override
    protected void parseBody(SOAPBodyElement body, SOAPFactory spf) throws SOAPException {
        names = parseParamList(body, spf, "ParameterInfoStruct", "Writable");
    }

    public int[] getMultiInstanceNames(String prefix) {
        int[] r = new int[names.size()];
        int ix = 0;
        int pfxlength = prefix.length();
        for (Entry<String, String> e : names.entrySet()) {
            String k = e.getKey();
            String n;
            if (k.endsWith(".")) {
                n = k.substring(pfxlength, k.length() - 1);
            } else {
                n = k.substring(pfxlength);
            }
            System.out.println("Name: " + n);
            r[ix++] = Integer.parseInt(n);
        }
        Arrays.sort(r);
        return r;
    }
    
    public Map<String, String> getDataValue(){
    	return names;
    }
    public Map<String, String> names;
}