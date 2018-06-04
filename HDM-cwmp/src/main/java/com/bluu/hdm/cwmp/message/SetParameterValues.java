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
import java.util.List;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;

public class SetParameterValues extends Message {

    /**
     * Creates a new instance of GetParameterValues
     */
    public SetParameterValues() {
        name = "SetParameterValues";
        names = new ArrayList<String>();
        values = new ArrayList<String>();
        types = new ArrayList<String>();
    }

    public void Merge(SetParameterValues other) {
        for (int i = 0; i < other.names.size(); i++) {
            names.add(other.names.get(i));
            values.add(other.values.get(i));
            types.add(other.types.get(i));
        }
    }

    @Override
    protected void createBody(SOAPBodyElement body, SOAPFactory spf) throws SOAPException {
        SOAPElement elm = body.addChildElement(spf.createName("ParameterList"));
        elm.setAttribute(SOAP_ARRAY_TYPE, "cwmp:ParameterValueStruct[" + String.valueOf(names.size()) + "]");
        for (int i = 0; i < names.size(); i++) {
            SOAPElement param = elm.addChildElement("ParameterValueStruct");
            param.addChildElement("Name").setValue(String.valueOf(names.get(i)));
            SOAPElement v = param.addChildElement("Value");
            v.setValue(values.get(i) == null ? "" : String.valueOf(values.get(i)));
//            v.setAttribute (XSI_TYPE,XSD_STRING);
            v.setAttribute(XSI_TYPE, types.get(i));
        }
        body.addChildElement(PARAMETER_KEY).setValue(key);
    }

    @Override
    protected void parseBody(SOAPBodyElement body, SOAPFactory f) throws SOAPException {
    }

    public void AddValue(String name, String value) {
        AddValue(name, value, XSD_STRING);
    }

    public void AddValue(String name, Integer value) {
        AddValue(name, value.toString(), XSD_UNSIGNEDINT);
    }

    public void AddValue(String name, boolean value) {
        AddValue(name, String.valueOf(value), XSD_BOOLEAN);
    }

    public void AddValue(String name, String value, String type) {
        names.add(name);
        values.add(value);
        types.add(type);
    }

    public boolean isEmpty() {
        return names.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("SetParameterValues request:\n");
        for (int i = 0; i < names.size(); i++) {
            s.append('\t');
            s.append(names.get(i));
            s.append(" (");
            s.append(types.get(i));
            s.append(") '");
            s.append(values.get(i));
            s.append("'\n");
        }
        return s.toString();
    }
    private List<String> names;
    private List<String> values;
    private List<String> types;
    public String key = "unsetCommandKey";
}
