/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.pojo.inventory.cpe;

import com.bluu.hdm.web.pojo.inventory.cpe.Reference;
import com.bluu.hdm.web.enums.EvaluatorTypeEnm;
import java.io.Serializable;
import java.util.ArrayList;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;


public class Evaluator implements Serializable {

    @Attribute(name = "type", required = false)
    private EvaluatorTypeEnm type = EvaluatorTypeEnm.and;
    @Attribute(name = "result", required = false)
    private Boolean result;
    @ElementList(entry = "reference", inline = true, required = false)
    private ArrayList<Reference> refLst = new ArrayList<>();
    @ElementList(entry = "operation", inline = true, required = false)
    private ArrayList<Operation> oprLst = new ArrayList<>();
    private boolean isEvaluated;

    public Evaluator() {
    }

    public Evaluator(Evaluator evl) {
        this.type = evl.type;
        this.result = evl.result;
        for (Reference ref : evl.refLst) {
            refLst.add(ref);
        }
        for (Operation opr : evl.oprLst) {
            oprLst.add(new Operation(opr));
        }
    }

    public ArrayList<Reference> getReferenceList() {
        return refLst;
    }

    public ArrayList<Operation> getOperationList() {
        return oprLst;
    }

    public Boolean getResult() {
        return result;
    }

    public boolean getResultNotNull() {
        return result == null ? true : result;
    }

    public boolean isAndType() {
        return type == EvaluatorTypeEnm.and;
    }

    public boolean isEvaluated() {
        return isEvaluated;
    }

    public void setResult(boolean val) {
        this.isEvaluated = true;
        this.result = val;
    }
}
