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
public class NullHandler extends DefaultHandler {

    private String tag;

    public NullHandler(String tag) {
        this.tag = tag;
    }

    @Override
    protected String getRootTag() {
        return tag;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    }
}
