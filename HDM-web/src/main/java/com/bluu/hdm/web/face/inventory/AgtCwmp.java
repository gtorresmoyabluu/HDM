package com.bluu.hdm.web.face.inventory;


import com.bluu.sch.trb.agent.Agent;
import com.bluu.sch.trb.enums.*;
import com.bluu.sch.trb.exception.AgtException;
import com.bluu.sch.trb.listener.LookupFileListener;
import com.bluu.sch.trb.logger.TrbLogger;
import com.bluu.sch.trb.pojo.*;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class AgtCwmp extends Agent implements Serializable {

    private final HashMap<String, ArrayList<ArrayList<String>>> key2TableMap = new HashMap<>();
    private final String user, url;
    private Cwmp cwmpBean;
    private HashMap<String, String> key2ValueMap = new HashMap<>();

    public AgtCwmp(String user, String url, String role, Template iniTpt, ArrayList<Template> tptLst, Device dev, Topology topology,
            HashMap<String, String> schCfgMap, LookupFileListener lookupFileListener, Cwmp cwmpBean, TrbLogger logger) {
        super(role, iniTpt, tptLst, dev, topology, schCfgMap, lookupFileListener, logger);
        if (user == null) {
            super.setError(AgtErrorEnm.ERRUSR, null);
        } else if (url == null) {
            super.setError(AgtErrorEnm.ERREND, null);
        } else if (tptLst.isEmpty()) {
            super.setError(AgtErrorEnm.ERRTPT, null);
        }
        this.cwmpBean = cwmpBean;
        this.user = user;
        this.url = url;
    }

    public AgtCwmp(String user, String url, HashMap<String, String> infoMap, String role, Template iniTpt, ArrayList<Template> tptLst,
            Device dev, Topology topology, HashMap<String, String> schCfgMap, LookupFileListener lookupFileListener, Cwmp cwmpBean, TrbLogger logger) {
        this(user, url, role, iniTpt, tptLst, dev, topology, schCfgMap, lookupFileListener, cwmpBean, logger);
        this.key2ValueMap = infoMap;
    }

    @Override
    public void doDisconnect() {
    }

    @Override
    public void doEvaluateParameter(Parameter par) throws AgtException {
        try {
            Template tpt = getTemplate();
            ArrayList<String> parLst = new ArrayList<>();
            boolean isConfNeeded = false;
            if (par.getType() == ParameterTypeEnm.group) {
                for (Operation opr : par.getContentList()) {
                    if (opr.getType() == OperationTypeEnm.parameter) {
                        String tptParId = opr.getValue();
                        Parameter tptPar = tpt.getParameter(tptParId);
                        if (tptPar == null) {
                            throw new AgtException("Param \"" + tptParId + "\" tpt \"" + tpt.getFile() + "\" not found");
                        }
                        for (Operation parOpr : tptPar.getContentList()) {
                            if ((parOpr.getType() == OperationTypeEnm.device_value) || (parOpr.getType() == OperationTypeEnm.device_table)) {
                                parLst.add(parOpr.getValue());
                            }
                        }
                    } else if (opr.getType() == OperationTypeEnm.wait_confirmation) {
                        isConfNeeded = true;
                    }
                }
            } else {
                for (Operation opr : par.getContentList()) {
                    if ((opr.getType() == OperationTypeEnm.device_value) || (opr.getType() == OperationTypeEnm.device_table)) {
                        parLst.add(opr.getValue());
                    } else if (opr.getType() == OperationTypeEnm.wait_confirmation) {
                        isConfNeeded = true;
                    }
                }
            }
            if (parLst.isEmpty()) {
                throw new AgtException("Param \"" + par.getId() + "\" tpt \"" + tpt.getFile() + "\" empty list");
            }
            LinkedHashMap<String, String> valMap = cwmpBean.getTestInfo(user, url, parLst, isConfNeeded);
            for (String key : valMap.keySet()) {
                key2ValueMap.put(key, valMap.get(key));
            }
        } catch (Exception ex) {
            throw new AgtException(ex.getMessage());
        }
    }

    @Override
    public void doExecuteAction(Action act) throws AgtException {
        try {
            String actId = act.getId();
            if (actId.equalsIgnoreCase("act_reboot")) {
                cwmpBean.doReboot(user, url);
            } else {
                LinkedHashMap<String, String> exParMap = new LinkedHashMap<>();
                String value = null;
                for (Operation oper : act.getSequenceList()) {
                    OperationTypeEnm operType = oper.getType();
                    String operValue = oper.getValue();
                    if (operType == OperationTypeEnm.cwmp_set_value) {
                        value = operValue;
                    } else if ((operType == OperationTypeEnm.cwmp_set_parameter) && (value != null)) {
                        exParMap.put(operValue, value);
                        value = null;
                    } else if (operType == OperationTypeEnm.wait) {
                        if (!exParMap.isEmpty()) {
                            cwmpBean.doExecuteAction(user, url, exParMap);
                            exParMap = new LinkedHashMap<>();
                        }
                        try {
                            TimeUnit.SECONDS.sleep(Integer.valueOf(operValue));
                        } catch (InterruptedException ignore) {
                        }
                    } else {
                        throw new AgtException("Operation type " + operType + " Not Supported");
                    }
                }
                if (!exParMap.isEmpty()) {
                    cwmpBean.doExecuteAction(user, url, exParMap);
                }
            }
        } catch (Exception ex) {
            throw new AgtException(ex.getMessage());
        }
    }

    @Override
    public ArrayList<ArrayList<String>> getTable(Operation opr) throws AgtException {
        OperationTypeEnm oprType = opr.getType();
        if (oprType == OperationTypeEnm.device_table) {
            String oprValue = opr.getValue();
            if (key2TableMap.containsKey(oprValue)) {
                return key2TableMap.get(oprValue);
            } else if (!oprValue.endsWith(".")) {
                throw new AgtException("CWMP table references must end with '.'");
            } else {
                ArrayList<ArrayList<String>> tab = new ArrayList<>();
                LinkedHashMap<String, LinkedHashMap<String, String>> agtTabMap = new LinkedHashMap<>();
                LinkedHashSet<String> headerSet = new LinkedHashSet<>();
                for (String key : key2ValueMap.keySet()) {
                    if (key.startsWith(oprValue)) {
                        String[] idxArr = key.substring(oprValue.length()).split("\\.");
                        int idxArrLen = idxArr.length;
                        String rowIdx = idxArr[0];
                        String colIdx = idxArrLen >= 2 ? idxArr[1] : "";
                        if (idxArrLen > 2) {
                            for (int i = 2; i < idxArrLen; i++) {
                                colIdx += "." + idxArr[i];
                            }
                        }
                        LinkedHashMap<String, String> rowMap = agtTabMap.get(rowIdx);
                        if (rowMap == null) {
                            rowMap = new LinkedHashMap<>();
                            rowMap.put("Index", rowIdx);
                            agtTabMap.put(rowIdx, rowMap);
                        }
                        headerSet.add(colIdx);
                        rowMap.put(colIdx, key2ValueMap.get(key));
                    }
                }
                ArrayList<String> colIdxLst = new ArrayList<>(headerSet);
                Collections.sort(colIdxLst);
                colIdxLst.add(0, "Index");
                tab.add(new ArrayList<>(colIdxLst));
                ArrayList<String> rowLst;
                ArrayList<String> rowIdxLst = new ArrayList<>(agtTabMap.keySet());
                Collections.sort(rowIdxLst);
                for (String rowKey : rowIdxLst) {
                    rowLst = new ArrayList<>();
                    LinkedHashMap<String, String> rowMap = agtTabMap.get(rowKey);
                    for (String colIdx : colIdxLst) {
                        String val = rowMap.get(colIdx);
                        rowLst.add(val == null ? "" : val);
                    }
                    tab.add(rowLst);
                }
                key2TableMap.put(oprValue, tab);
                return tab;
            }
        } else {
            throw new AgtException("getTable Wrong Operation " + oprType);
        }
    }

    @Override
    public ArrayList<ArrayList<String>> getTable(ArrayList<Operation> oprLst) throws AgtException {
        throw new AgtException("getTable lst Not Supported");
    }

    @Override
    public String getValue(Operation opr) throws AgtException {
        OperationTypeEnm oprType = opr.getType();
        if (oprType == OperationTypeEnm.device_value) {
            String oprValue = opr.getValue();
            return key2ValueMap.containsKey(oprValue) ? key2ValueMap.get(oprValue) : "unknown";
        } else {
            throw new AgtException("getValue Wrong Operation " + oprType);
        }
    }

    public String getUser() {
        return user;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean isIcmpConnectivity() {
        return true;
    }

    @Override
    public boolean isProtocolConnectivity() {
        try {
            if (isStatusOk()) {
                Template tpt = getTemplate();
                if ((dev != null) && (dev.getStatus() != OnlineEnm.offline) && tpt.containsTest(TST_DEV_NOK)) {
                    dev.setOnline(!evaluateTest(null, TST_DEV_NOK, null));
                }
            }
            return isStatusOk();
        } catch (Exception ex) {
            setError(AgtErrorEnm.ERREXC, ex.getMessage());
            return false;
        }
    }

    public void setInfoMap(HashMap<String, String> infoMap) {
        if ((infoMap == null) || infoMap.isEmpty()) {
            setError(AgtErrorEnm.ERRRSP, null);
        } else {
            this.key2ValueMap = infoMap;
        }
    }
}
