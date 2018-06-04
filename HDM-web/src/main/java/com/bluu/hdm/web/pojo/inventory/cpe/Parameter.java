/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.pojo.inventory.cpe;

import com.bluu.hdm.web.enums.ParameterTypeEnm;
import java.io.Serializable;
import java.util.ArrayList;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.core.Commit;

public class Parameter implements Serializable {

    @Attribute(name = "id")
    protected String id;
    @Attribute(name = "name", required = false)
    private String name;
    @Attribute(name = "type", required = false)
    private ParameterTypeEnm type = ParameterTypeEnm.value;
    @Attribute(name = "unit", required = false)
    private String unit = "";
    @Attribute(name = "hidden", required = false)
    private boolean isHidden = false;
    @ElementList(name = "contents")
    private ArrayList<Operation> cntLst;
    @ElementList(name = "sequence", required = false)
    private ArrayList<Operation> seqLst = new ArrayList<>();
    private ArrayList<ArrayList<String>> valTbl = new ArrayList<>();
    private ArrayList<String> refLst = new ArrayList<>();

    public Parameter() {
    }

    public Parameter(String id, String value) {
        this.id = id;
        ArrayList<String> valLst = new ArrayList<>();
        valLst.add(value);
        valTbl.add(valLst);
    }

    public Parameter(Parameter par) {
        this.id = par.getId();
        this.name = par.name;
        this.type = par.type;
        this.unit = par.unit;
        this.isHidden = par.isHidden;
        this.cntLst = new ArrayList<>();
        for (Operation opr : par.cntLst) {
            cntLst.add(new Operation(opr));
        }
        if (par.seqLst != null) {
            for (Operation opr : par.seqLst) {
                seqLst.add(new Operation(opr));
            }
        }
    }

    @Commit
    public void build() {
        if (name == null) {
            this.name = id;
        }
    }

    public ArrayList<Operation> getContentList() {
        return cntLst;
    }

    public String getId() {
        return id;
    }

    public int getListSize() {
        return valTbl.isEmpty() ? 0 : valTbl.get(0).size();
    }

    public String getName() {
        return name;
    }

    public String getReference() {
        if ((refLst == null) || refLst.isEmpty()) {
            return "unknown";
        } else if (refLst.size() == 1) {
            return refLst.get(0);
        } else {
            String ref = refLst.toString();
            return ref.substring(1, ref.length() - 1);
        }
    }

    public ArrayList<String> getReferenceList() {
        return refLst;
    }

    public ArrayList<Operation> getSequenceList() {
        return seqLst;
    }

    public ParameterTypeEnm getType() {
        return type;
    }

    public String getUnit() {
        return unit;
    }

    public String getValue() {
        if (type == ParameterTypeEnm.group) {
            return "group";
        } else if (valTbl.isEmpty() || valTbl.get(0).isEmpty()) {
            return "unknown";
        } else if (type == ParameterTypeEnm.list) {
            String val = valTbl.get(0).toString();
            return val.substring(1, val.length() - 1);
        } else if (type == ParameterTypeEnm.table) {
            String val = valTbl.toString();
            return val.substring(1, val.length() - 1);
        } else { // value
            ArrayList<String> valueLst = valTbl.get(0);
            return valueLst.get(0);
        }
    }

    public ArrayList<String> getValueList() {
        return valTbl.isEmpty() ? new ArrayList<String>() : valTbl.get(0);
    }

    public ArrayList<ArrayList<String>> getValueTable() {
        return valTbl;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setValues(ArrayList<ArrayList<String>> valTbl, ArrayList<String> refLst) {
        this.valTbl = valTbl;
        this.refLst = refLst;
    }

    @Override
    public String toString() {
        String result = "Par Id: " + id + ", Name: " + name + ", Type: " + type
                + ", Unit: " + unit + ", Hidden: " + isHidden + "\nPar Contents:";
        for (Operation opr : cntLst) {
            result += "\n- " + opr;
        }
        if (!seqLst.isEmpty()) {
            result += "\nPar Sequence:";
            for (Operation opr : seqLst) {
                result += "\n- " + opr;
            }
        }
        return result;
    }
}
