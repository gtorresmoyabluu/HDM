/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.pojo.inventory.cpe;

import com.bluu.hdm.web.enums.InputTypeEnm;
import com.bluu.hdm.web.enums.InputValueTypeEnm;
import com.bluu.hdm.web.enums.IptValue;
import java.io.Serializable;
import java.util.ArrayList;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Input implements Serializable {

    @Attribute(name = "id")
    private String id;
    @Attribute(name = "default", required = false)
    private String defVal;
    @Attribute(name = "value", required = false)
    private String value;
    @Attribute(name = "type", required = false)
    private InputTypeEnm type = InputTypeEnm.text;
    @Attribute(name = "valuetype", required = false)
    private InputValueTypeEnm valType = InputValueTypeEnm.text;
    @Attribute(name = "maxlength", required = false)
    private String maxLength;
    @Attribute(name = "minlength", required = false)
    private String minLength;
    @Element(name = "text")
    private String text;
    @ElementList(name = "valuelist", required = false)
    private ArrayList<IptValue> itemLst = new ArrayList<>();
    private static final Pattern IPTARGPATT = Pattern.compile("#I#\\w+#"), TPTCFGPATT = Pattern.compile("#T#\\w+#"),
            NONASCIIPAT = Pattern.compile("[^\u0020-\u007E]");
    private HashMap<String, String> tptCfgMap = new HashMap<>(), iptArgMap = new HashMap<>();
    private LinkedHashMap<String, String> itemMap = new LinkedHashMap<>();
    // TODO: Schaman hace dos cambios casi simultaneos del valor el seleccionado y de vuelta al valor 
    // original eso hace que no funcione correctamente, se mete este campo como salvaguarda
    long lastChgTimestamp = 0l;

    public Input() {
    }

    public Input(Input ipt) {
        this.id = ipt.id;
        this.type = ipt.type;
        this.defVal = ipt.defVal;
        this.valType = ipt.valType;
        this.value = ipt.value;
        this.maxLength = ipt.maxLength;
        this.minLength = ipt.minLength;
        this.text = ipt.text;
        for (IptValue ipv : ipt.itemLst) {
            itemLst.add(ipv);
            itemMap.put(ipv.getLabel(), ipv.getValue());
        }
    }

    public String getDefaultValue() {
        return defVal;
    }

    public String getId() {
        return id;
    }

    public Integer getMaximumLength() {
        try {
            return maxLength == null ? null : Integer.parseInt(parseValue(maxLength));
        } catch (Exception parseerr) {
            return null;
        }
    }

    public String getMaximumLengthSt() {
        return maxLength;
    }

    public Integer getMinimumLength() {
        try {
            return minLength == null ? null : Integer.parseInt(parseValue(minLength));
        } catch (Exception parseerr) {
            return null;
        }
    }

    public String getMinimumLengthSt() {
        return minLength;
    }

    public String getText() {
        return parseValue(text);
    }

    public InputTypeEnm getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public boolean getValueBoolean() {
        return (value != null) && value.equalsIgnoreCase("true");
    }

    public ArrayList<IptValue> getItemList() {
        return itemLst;
    }

    public LinkedHashMap<String, String> getItemMap() {
        return itemMap;
    }

    public InputValueTypeEnm getValueType() {
        return valType;
    }

    public boolean isTypeCheck() {
        return type == InputTypeEnm.check;
    }

    public boolean isTypeList() {
        return type == InputTypeEnm.list;
    }

    public boolean isTypePassword() {
        return type == InputTypeEnm.password;
    }

    public boolean isTypeText() {
        return type == InputTypeEnm.text;
    }

    public void setDefaultValue(String val) {
        this.defVal = val;
    }

    public void setMaps(HashMap<String, String> tptCfgMap, HashMap<String, String> iptArgMap) {
        this.tptCfgMap = tptCfgMap;
        this.iptArgMap = iptArgMap;
    }

    // TODO: Unificar con setValueOrGetValidationError
    // TODO: Ver que pasa para intentar quitar filtrado por tiempo
    public void setValue(String val) {
        long chgTimestamp = (new Date()).getTime();
        if ((chgTimestamp - lastChgTimestamp) > 50) {
            this.value = val;
            this.iptArgMap.put(id, val);
            this.lastChgTimestamp = chgTimestamp;
        }
    }

    // TODO: Unificar con setValue
    public String setValueOrGetValidationError(String val) {
        Integer maxlng = getMaximumLength(), minlng = getMinimumLength();
        if (val == null) {
            return getText() + " no puede estar vacío";
        }
        if (valType == InputValueTypeEnm.ascii) {
            Matcher mat = NONASCIIPAT.matcher(val);
            HashSet<String> wrongCharSet = new HashSet<>();
            while (mat.find()) {
                wrongCharSet.add(mat.group());
            }
            if (!wrongCharSet.isEmpty()) {
                ArrayList<String> wrongCharLst = new ArrayList<>(wrongCharSet);
                if (wrongCharLst.size() == 1) {
                    return getText() + " no puede contener el caracter " + wrongCharLst.get(0);
                } else {
                    String wrongCharLstSt = wrongCharLst.toString();
                    wrongCharLstSt = wrongCharLstSt.substring(1, wrongCharLstSt.length() - 1);
                    return getText() + " no puede contener los caracteres: " + wrongCharLstSt;
                }
            }
        }
        if ((maxlng != null) && (val.length() > maxlng)) {
            return (minlng == null) ? (getText() + " debe tener " + maxlng + " caracteres como máximo")
                    : (minlng.equals(maxlng) ? (getText() + " debe tener " + maxlng + " caracteres")
                    : (getText() + " debe tener entre " + minlng + " y " + maxlng + " caracteres"));
        } else if ((minlng != null) && (val.length() < minlng)) {
            return (maxlng == null) ? (getText() + " debe tener " + minlng + " caracteres como mínimo")
                    : (maxlng.equals(minlng) ? (getText() + " debe tener " + minlng + " caracteres")
                    : (getText() + " debe tener entre " + minlng + " y " + maxlng + " caracteres"));
        } else if (val.isEmpty()) {
            return getText() + " no puede estar vacío";
        }
        this.value = val;
        this.iptArgMap.put(id, val);
        return null;
    }

    public void setValueMap(LinkedHashMap<String, String> valMap) {
        this.itemLst = new ArrayList<>();
        this.itemMap = new LinkedHashMap<>();
        for (String lab : valMap.keySet()) {
            String val = valMap.get(lab);
            itemLst.add(new IptValue(lab, val));
            itemMap.put(lab, val);
        }
    }

    @Override
    public String toString() {
        String result = "Ipt Id: " + id + ", Type: " + type + ", DefValue: " + defVal + ", Value: " + value + ", ValType: "
                + valType + ", MaxLength: " + getMaximumLength() + ", MinLength: " + getMinimumLength() + ", Text: " + text;
        if (!itemLst.isEmpty()) {
            for (IptValue val : itemLst) {
                result += "\n- " + val;
            }
        }
        return result;
    }

    private String parseValue(String value) {
        Matcher matcher = IPTARGPATT.matcher(value);
        while (matcher.find()) {
            String token = matcher.group();
            String[] tokenArr = token.split("#");
            if (tokenArr.length == 3) {
                String currAct = tokenArr[1];
                String val2parse = tokenArr[2];
                if (currAct.equalsIgnoreCase("I") && (iptArgMap != null) && iptArgMap.containsKey(val2parse)
                        && (iptArgMap.get(val2parse) != null)) {
                    value = value.replace(token, iptArgMap.get(val2parse));
                }
            }
        }
        matcher = TPTCFGPATT.matcher(value);
        while (matcher.find()) {
            String token = matcher.group();
            String[] tokenArr = token.split("#");
            if (tokenArr.length == 3) {
                String currAct = tokenArr[1];
                String val2parse = tokenArr[2];
                if (currAct.equalsIgnoreCase("T") && (tptCfgMap != null) && tptCfgMap.containsKey(val2parse)
                        && (tptCfgMap.get(val2parse) != null)) {
                    value = value.replace(token, tptCfgMap.get(val2parse));
                }
            }
        }
        return value;
    }
}

