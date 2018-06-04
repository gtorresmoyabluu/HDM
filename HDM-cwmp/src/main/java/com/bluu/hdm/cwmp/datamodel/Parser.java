/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.datamodel;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Gonzalo Torres
 */
public class Parser {

    public static void setDir(String dir) {
        DefaultHandler.setDirectory(dir);
    }

    public static void Parse(String name) throws SAXException, ParserConfigurationException, IOException {
        System.out.println("PARSE: " + name);
        DefaultHandler.Parse(name);
    }

    public static void setStreamProvider(StreamProvider streamProvider) {
        DefaultHandler.setStreamProvider(streamProvider);

    }
}
