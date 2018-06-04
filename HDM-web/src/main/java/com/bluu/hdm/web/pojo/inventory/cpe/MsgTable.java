/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.pojo.inventory.cpe;

import java.io.Serializable;
import java.util.ArrayList;

public class MsgTable implements Serializable {

    private final ArrayList<ArrayList<String>> msgTab = new ArrayList<>();
    private final String header, footer;

    public MsgTable(String text) {
        int bracket1st = text.indexOf("[");
        int bracketlst = text.lastIndexOf("]");
        this.header = text.startsWith("[") ? null : text.substring(0, bracket1st);
        this.footer = text.endsWith("]") ? null : text.substring(bracketlst + 1, text.length());
        String tabSt = text.substring(bracket1st + 1, bracketlst);
        String[] msgArr = tabSt.split("\\], \\[");
        for (String rowSt : msgArr) {
            ArrayList<String> row = new ArrayList<>();
            String[] eltArr = rowSt.split(", ");
            for (String elt : eltArr) {
                row.add(((elt == null) || elt.isEmpty()) ? "-" : elt);
            }
            msgTab.add(row);
        }
    }

    public String getFooter() {
        return footer;
    }

    public String getHeader() {
        return header;
    }

    public ArrayList<ArrayList<String>> getTable() {
        return msgTab;
    }

    public boolean isFooter() {
        return footer != null;
    }

    public boolean isHeader() {
        return header != null;
    }
}
