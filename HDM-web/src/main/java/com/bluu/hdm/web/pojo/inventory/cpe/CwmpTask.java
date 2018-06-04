package com.bluu.hdm.web.pojo.inventory.cpe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

public class CwmpTask implements Serializable {

    private static final long TIMEOUT = 120000; // 2 min
    private final Date timestamp;
    private ArrayList<String> valueLst = new ArrayList<>();
    private boolean isRequested = false, isCompleted = false,
            isNextLevel = false, // GetParameterNames: nextLevel
            isConfNeeded = false, // GetTestInfo: inform with type 8 needed
            isConfirmed = false; // GetTestInfo: inform with type 8 reached
    private LinkedHashMap<String, String> valueMap = new LinkedHashMap<>();
    private String param, // GetParameterNames parameterPath, Provisioning userName
            prefix, // Provisioning prefix
            paramId; // Parameter id

    public CwmpTask() { // GetRPCMethods
        this.timestamp = new Date();
    }

    public CwmpTask(String param) {
        this.param = param;
        valueLst.add(param);
        this.timestamp = new Date();
    }

    public CwmpTask(ArrayList<String> valLst) {
        this.valueLst = valLst;
        this.timestamp = new Date();
    }

    public CwmpTask(ArrayList<String> valLst, boolean isConfNeeded) {
        this.valueLst = valLst;
        this.isConfNeeded = isConfNeeded;
        this.timestamp = new Date();
    }

    public CwmpTask(String param, String prefix) {
        this.param = param;
        this.prefix = prefix;
        this.timestamp = new Date();
    }

    public CwmpTask(String param, String prefix, String paramId) {
        this.param = param;
        this.prefix = prefix;
        this.paramId = paramId;
        this.timestamp = new Date();
    }

    public CwmpTask(LinkedHashMap<String, String> valueMap) {
        this.valueMap = valueMap;
        this.timestamp = new Date();
    }

    public CwmpTask(String paramPath, boolean isNextLevel) { // GetParameterNames
        this.param = paramPath;
        this.isNextLevel = isNextLevel;
        this.timestamp = new Date();
    }

    public String getParameter() {
        return param;
    }

    public String getParameterId() {
        return paramId;
    }

    public String getPrefix() {
        return prefix;
    }

    public ArrayList<String> getValueList() {
        return valueLst;
    }

    public ArrayList<MapEntry> getValueMapList() {
        ArrayList<MapEntry> mapLst = new ArrayList<>();
        for (String key : valueMap.keySet()) {
            mapLst.add(new MapEntry(key, valueMap.get(key)));
        }
        return mapLst;
    }

    public LinkedHashMap<String, String> getValueMap() {
        return valueMap;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public boolean isNextLevel() {
        return isNextLevel;
    }

    public boolean isConfirmationNeeded() {
        return isConfNeeded;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public boolean isRequested() {
        return isRequested;
    }

    public boolean isTimedout() {
        return (new Date()).getTime() - timestamp.getTime() > TIMEOUT;
    }

    public void setCompleted() {
        this.isCompleted = true;
    }

    public void setConfirmed() {
        this.isConfirmed = true;
    }

    public void setRequested() {
        this.isRequested = true;
    }

    public void setValueList(ArrayList<String> valueLst) {
        this.valueLst = valueLst;
    }

    public void setValueMap(LinkedHashMap<String, String> valueMap) {
        this.valueMap = valueMap;
    }
}
