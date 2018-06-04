/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.pojo.inventory.cpe;


import com.bluu.hdm.web.enums.TextTypeEnm;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;
import org.simpleframework.xml.ElementMap;


public class TextMap implements Serializable {

    @ElementMap(entry = "text", key = "type", attribute = true, inline = true)
    private HashMap<TextTypeEnm, String> txtMap;

    public TextMap() {
    }

    public TextMap(TextMap tmp) {
        this.txtMap = new HashMap<>();
        for (TextTypeEnm key : tmp.txtMap.keySet()) {
            txtMap.put(key, tmp.txtMap.get(key));
        }
    }

    public String getText(TextTypeEnm type) {
        return txtMap.get(type);
    }

    public Set<TextTypeEnm> getTypeSet() {
        return txtMap.keySet();
    }
    
    public void setText(TextTypeEnm type, String text) {
        txtMap.put(type, text);
    }
}
