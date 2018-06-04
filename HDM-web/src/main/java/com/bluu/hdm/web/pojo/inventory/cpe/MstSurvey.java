/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.pojo.inventory.cpe;



import com.bluu.hdm.web.pojo.inventory.cpe.TextMap;
import com.bluu.hdm.web.enums.TextTypeEnm;
import java.io.Serializable;
import java.util.ArrayList;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "survey")
public class MstSurvey implements Serializable {

    @Element(name = "texts", required = false)
    private TextMap txtMap;
    @ElementList(name = "actions")
    private ArrayList<Action> actLst;

    public MstSurvey() {
    }

    public ArrayList<Action> getActionList() {
        return actLst;
    }

    public TextMap getTextMap() {
        return txtMap;
    }

    public String getTextHeader() {
        return txtMap == null ? null : txtMap.getText(TextTypeEnm.header);
    }

    public String getTextFooter() {
        return txtMap == null ? null : txtMap.getText(TextTypeEnm.footer);
    }

    @Override
    public String toString() {
        String result = "";
        if (txtMap != null) {
            for (TextTypeEnm txtType : txtMap.getTypeSet()) {
                result += "\nSur Txt " + txtType + ": " + txtMap.getText(txtType);
            }
        }
        if (!actLst.isEmpty()) {
            result += "\nSur Actions:";
            for (Action act : actLst) {
                result += "\n" + act;
            }
        }
        return result;
    }
}
