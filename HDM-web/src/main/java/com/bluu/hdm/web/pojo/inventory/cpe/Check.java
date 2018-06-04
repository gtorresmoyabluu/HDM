/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.pojo.inventory.cpe;

import com.bluu.hdm.web.pojo.inventory.cpe.Evaluable;
import com.bluu.hdm.web.pojo.inventory.cpe.Message1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

public class Check extends Evaluable implements Serializable {

    @Element(name = "parameter", required = false)
    private String paramId;
    @ElementList(name = "messages", required = false)
    private ArrayList<Message1> msgLst = new ArrayList<>();
    private final LinkedHashMap<String, Message1> msg2showMap = new LinkedHashMap<>();

    public Check() {
        super();
    }

    public Check(Check chk) {
        super(chk);
        this.paramId = chk.paramId;
        for (Message1 msg : chk.msgLst) {
            msgLst.add(new Message1(msg));
        }
    }

    public void addMessage2Show(Message1 msg) {
        msg2showMap.put(msg.getId(), msg);
    }

    public ArrayList<Message1> getMessageList() {
        return msgLst;
    }

    public ArrayList<Message1> getMessage2ShowList() {
        return new ArrayList<>(msg2showMap.values());
    }

    public String getParameterId() {
        return paramId;
    }

    @Override
    public String toString() {
        String result = "Chk Id: " + id + ", ParamId: " + paramId;
        if (boolEvl != null) {
            result += "\nChk Boolean AndType: " + boolEvl.isAndType() + ", Result: " + boolEvl.getResult();
            for (Operation opr : boolEvl.getOperationList()) {
                result += "\nChk " + opr;
            }
        }
        if (!msgLst.isEmpty()) {
            result += "\nChk Messages:";
            for (Message1 msg : msgLst) {
                result += "\n" + msg;
            }
        }
        return result;
    }
}
