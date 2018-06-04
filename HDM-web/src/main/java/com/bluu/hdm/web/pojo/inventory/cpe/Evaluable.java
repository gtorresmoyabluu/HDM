/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.pojo.inventory.cpe;

import java.io.Serializable;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

public class Evaluable implements Serializable {

    @Attribute(name = "id")
    protected String id;
    @Element(name = "boolean", required = false)
    protected Evaluator boolEvl = new Evaluator();
    @Element(name = "requirement", required = false)
    protected Evaluator reqsEvl = new Evaluator();

    public Evaluable() {
    }

    public Evaluable(Evaluable evl) {
        this.id = evl.id;
        this.boolEvl = new Evaluator(evl.boolEvl);
        this.reqsEvl = new Evaluator(evl.reqsEvl);
    }

    public Evaluator getBooleanEvaluator() {
        return boolEvl;
    }

    public String getId() {
        return id;
    }

    public Evaluator getRequirementEvaluator() {
        return reqsEvl;
    }
}
