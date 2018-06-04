/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.pojo.inventory.cpe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import org.simpleframework.xml.ElementList;

public class Test extends Evaluable implements Serializable {

    @ElementList(name = "actions", required = false)
    private ArrayList<Action> actLst = new ArrayList<>();
    @ElementList(name = "messages", required = false)
    private ArrayList<Message1> msgLst = new ArrayList<>();
    private final LinkedHashMap<String, Action> act2showMap = new LinkedHashMap<>();
    private final LinkedHashMap<String, Message1> msg2showMap = new LinkedHashMap<>();

    public Test() {
        super();
    }

    public Test(Test tst) {
        super(tst);
        for (Action act : tst.actLst) {
            actLst.add(new Action(act));
        }
        for (Message1 msg : tst.msgLst) {
            msgLst.add(new Message1(msg));
        }
    }
    
    public Test(String id, boolean result) {
        this.id = id;
        this.boolEvl.setResult(result);
    }

    public void addAction2Show(Action act) {
        act2showMap.put(act.getId(), act);
    }

    public void addMessage2Show(Message1 msg) {
        msg2showMap.put(msg.getId(), msg);
    }

    public ArrayList<Action> getActionList() {
        return actLst;
    }

    public ArrayList<Action> getAction2ShowList() {
        return new ArrayList<>(act2showMap.values());
    }

    public ArrayList<Message1> getMessageList() {
        return msgLst;
    }

    public ArrayList<Message1> getMessage2ShowList() {
        return new ArrayList<>(msg2showMap.values());
    }

    @Override
    public String toString() {
        String result = "Tst Id: " + id;
        if (boolEvl != null) {
            result += "\nTst Boolean AndType: " + boolEvl.isAndType() + ", Result: " + boolEvl.getResult();
            for (Reference ref : boolEvl.getReferenceList()) {
                result += "\nTst " + ref;
            }
        }
        if (!actLst.isEmpty()) {
            result += "\nTst Actions:";
            for (Action act : actLst) {
                result += "\n" + act;
            }
        }
        if (!msgLst.isEmpty()) {
            result += "\nTst Messages:";
            for (Message1 msg : msgLst) {
                result += "\n" + msg;
            }
        }
        return result;
    }
}
