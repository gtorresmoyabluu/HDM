/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.datamodel;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 *
 * @author Gonzalo Torres
 */
public class ComponentHandler extends DefaultHandler {

    ComponentHandler() {
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("object")) {
        } else if (qName.equalsIgnoreCase("parameter")) {
        } else {
            if (debug) {
                System.out.println("unhandled tag under component: " + qName + " parent " + getParent());
            }
        }
    }

    @Override
    protected String getRootTag() {
        return "component";
    }
}
