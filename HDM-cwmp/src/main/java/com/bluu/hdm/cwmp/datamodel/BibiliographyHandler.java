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
public class BibiliographyHandler extends DefaultHandler {

    private BibliographyEntry entry;
    public static final String TOP_TAG = "bibliography";

    BibiliographyHandler() {
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("reference")) {
            BibliographyEntry.add(entry);
        } else if (qName.equals("name")) {
            entry.setName(lastText);
        } else if (qName.equals("title")) {
            entry.setTitle(lastText);
        } else if (qName.equals("organization")) {
            entry.setOrganization(lastText);
        } else if (qName.equals("category")) {
            entry.setCategory(lastText);
        } else if (qName.equals("date")) {
            entry.setDate(lastText);
        } else if (qName.equals("hyperlink")) {
            entry.setHyperlink(lastText);
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("reference")) {
            entry = new BibliographyEntry();
            entry.setId(attributes.getValue("id"));
        }
    }

    @Override
    protected String getRootTag() {
        return TOP_TAG;
    }
}
