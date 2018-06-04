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
import java.util.Map;
import java.util.Map.Entry;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;

public class GetParameterValuesResponse extends Message {

    /**
     * Creates a new instance of GetParameterValuesResponse
     */
    public GetParameterValuesResponse() {
        name = "GetParameterValuesResponse";
    }

    @Override
    protected void createBody(SOAPBodyElement body, SOAPFactory spf) throws SOAPException {
    }

    @Override
    protected void parseBody(SOAPBodyElement body, SOAPFactory spf) throws SOAPException {
        values = parseParamList(body, spf);
    }
    public Map<String, String> values;

    public Map<String, String> getDataValues() {
        return values;
    }

    public Integer getParamInt(String name) {
        String v = values.get(name);
        if (v != null) {
            try {
                return Integer.parseInt(v);
            } catch (NumberFormatException e) {
            }
        }
        return null;
    }

    public Integer getParamInt(String name, int defaultValue) {
        Integer value = getParamInt(name);
        return (value != null) ? value : defaultValue;
    }

    public String getParam(String name) {
        return values.get(name);
    }

    public String getParam(String name, String defaultValue) {
        String value = getParam(name);
        return (value != null) ? value : defaultValue;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder(1024);
        for (Entry<String, String> e : values.entrySet()) {
            b.append(e.getKey());
            b.append("=");
            b.append(e.getValue());
            b.append("\n");
        }
        return b.toString();

    }
}
