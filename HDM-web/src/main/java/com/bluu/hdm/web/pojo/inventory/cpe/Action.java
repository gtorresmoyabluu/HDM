/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.pojo.inventory.cpe;

import com.bluu.hdm.web.enums.ActionTypeEnm;
import com.bluu.hdm.web.enums.ActivitySourceEnm;
import com.bluu.hdm.web.enums.PlatformEnm;
import com.bluu.hdm.web.enums.TextTypeEnm;
import com.bluu.hdm.web.enums.VisualizeEnm;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.simpleframework.xml.core.Commit;
import org.simpleframework.xml.core.PersistenceException;
import org.simpleframework.xml.core.Validate;

public class Action extends Evaluable implements Serializable {

    private static final SimpleDateFormat TTSDF = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    @Attribute(name = "serviceid", required = false)
    private String srvId;
    @Attribute(name = "troubleshootid", required = false)
    private String trbId;
    @Attribute(name = "role", required = false)
    private String role = "none";
    @Attribute(name = "device", required = false)
    private String device = "none";
    @Attribute(name = "type", required = false)
    private ActionTypeEnm type = ActionTypeEnm.info;
    @Attribute(name = "timestamp", required = false)
    private String timestampSt; // Solo para deserializacion
    @Attribute(name = "source", required = false)
    private ActivitySourceEnm source;
    @Attribute(name = "platform", required = false)
    private PlatformEnm platform = PlatformEnm.all;
    @Attribute(name = "visualize", required = false)
    private VisualizeEnm visualize = VisualizeEnm.iffalse;
    @Attribute(name = "hidden", required = false)
    private boolean isHidden = false;
    @Element(name = "texts", required = false)
    private TextMap txtMap;
    @Element(name = "wait", required = false)
    private Integer wait;
    @ElementList(name = "inputs", required = false)
    private ArrayList<Input> iptLst = new ArrayList<>();
    @ElementList(name = "sequence", required = false)
    private ArrayList<Operation> seqLst = new ArrayList<>();
    @ElementList(name = "arguments", required = false)
    private ArrayList<Argument> argLst = new ArrayList<>();
    private static final Pattern IPTARGPATT = Pattern.compile("#I#\\w+#"), TPTCFGPATT = Pattern.compile("#T#\\w+#");
    private HashMap<String, String> iptArgMap = new HashMap<>(), tptCfgMap = new HashMap<>();
    private boolean isExecuted = false;
    private Date timestamp;
    private String execErr;

    public Action() {
    }

    public Action(Action act) {
        super(act);
        this.role = act.role;
        this.device = act.device;
        this.type = act.type;
        this.source = act.source;
        this.srvId = act.srvId;
        this.trbId = act.trbId;
        this.platform = act.platform;
        this.visualize = act.visualize;
        this.isHidden = act.isHidden;
        this.wait = act.wait;
        this.tptCfgMap = act.tptCfgMap;
        if (act.txtMap != null) {
            this.txtMap = new TextMap(act.txtMap);
        }
        for (Input ipt : act.iptLst) {
            ipt = new Input(ipt);
            ipt.setMaps(tptCfgMap, iptArgMap);
            iptLst.add(ipt);
            if (ipt.getValue() != null) {
                iptArgMap.put(ipt.getId(), ipt.getValue());
            }
        }
        for (Argument arg : act.argLst) {
            argLst.add(arg);
            iptArgMap.put(arg.getId(), arg.getValue());
        }
        for (Operation opr : act.seqLst) {
            seqLst.add(new Operation(opr));
        }
    }

    @Commit
    public void build() {
        if (timestampSt != null) {
            try {
                this.timestamp = TTSDF.parse(timestampSt);
            } catch (ParseException ignore) {
            }
        }
        for (Input ipt : iptLst) {
            iptArgMap.put(ipt.getId(), ipt.getValue() == null ? ipt.getDefaultValue() : ipt.getValue());
        }
        for (Argument arg : argLst) {
            iptArgMap.put(arg.getId(), arg.getValue());
        }
    }

    public ArrayList<Argument> getArgumentList() {
        return argLst;
    }

    public String getDevice() {
        return device;
    }

    public String getExecutionError() {
        return execErr;
    }

    public HashMap<String, String> getInputAndArgumentMap() {
        return iptArgMap;
    }

    public Input getInput(String id) {
        for (Input input : getInputList()) {
            if (input.getId().equalsIgnoreCase(id)) {
                return input;
            }
        }
        return null;
    }

    public ArrayList<Input> getInputList() {
        return iptLst;
    }

    public PlatformEnm getPlatform() {
        return platform;
    }

    public String getRole() {
        return role;
    }

    public ArrayList<Operation> getSequenceList() {
        for (Operation opr : seqLst) {
            opr.setValue(parseValue(opr.getValue()));
        }
        return seqLst;
    }

    public String getServiceId() {
        return srvId;
    }

    public ActivitySourceEnm getSource() {
        return source;
    }

    public String getTextDetail() {
        return txtMap.getText(TextTypeEnm.detail) == null ? null : parseValue(txtMap.getText(TextTypeEnm.detail));
    }

    public String getTextError() {
        return txtMap.getText(TextTypeEnm.error) == null ? null : parseValue(txtMap.getText(TextTypeEnm.error));
    }

    public String getTextHelp() {
        return txtMap.getText(TextTypeEnm.help) == null ? null : parseValue(txtMap.getText(TextTypeEnm.help));
    }

    public String getTextOk() {
        return txtMap.getText(TextTypeEnm.ok) == null ? null : parseValue(txtMap.getText(TextTypeEnm.ok));
    }

    public TextMap getTextMap() {
        return txtMap;
    }

    public String getTextUrl() {
        return txtMap.getText(TextTypeEnm.url) == null ? null : parseValue(txtMap.getText(TextTypeEnm.url));
    }

    public String getTextWarning() {
        return txtMap.getText(TextTypeEnm.warning) == null ? null : parseValue(txtMap.getText(TextTypeEnm.warning));
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getTroubleshootId() {
        return trbId;
    }

    public ActionTypeEnm getType() {
        return type;
    }

    public Integer getWait() {
        return wait;
    }

    public boolean isExecuted() {
        return isExecuted;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public boolean isTypeButton() {
        return type == ActionTypeEnm.button;
    }

    public boolean isTypeConfig() {
        return type == ActionTypeEnm.config;
    }

    public boolean isTypeDeeplink() {
        return type == ActionTypeEnm.deeplink;
    }

    public boolean isTypeInfo() {
        return type == ActionTypeEnm.info;
    }

    public boolean isTypeToast() {
        return type == ActionTypeEnm.toast;
    }

    public boolean isTypeTutorial() {
        return type == ActionTypeEnm.tutorial;
    }

    public boolean isVisualized(boolean boolRes) {
        return (visualize == VisualizeEnm.ifrequired) || ((visualize == VisualizeEnm.iftrue) && boolRes)
                || ((visualize == VisualizeEnm.iffalse) && !boolRes);
    }

    public void setArgumentValue(String argument, String value) {
        boolean notFound = true;
        for (Argument arg : argLst) {
            if (arg.getId().equalsIgnoreCase(argument)) {
                arg.setValue(value);
                notFound = false;
                break;
            }
        }
        if (notFound) {
            argLst.add(new Argument(argument, value));
        }
        iptArgMap.put(argument, value);
    }

    public void setDetails(HashMap<String, String> tptCfgMap) {
        this.tptCfgMap = tptCfgMap;
        for (Input ipt : iptLst) {
            ipt.setMaps(tptCfgMap, iptArgMap);
        }
    }

    public void setDetails(String srvId, ActivitySourceEnm source) {
        this.srvId = srvId;
        this.source = source;
    }

    public void setDetails(String srvId, ActivitySourceEnm source, String trbId) {
        this.srvId = srvId;
        this.trbId = trbId;
        this.source = source;
    }

    public void setDetails(String srvId, ActivitySourceEnm source, HashMap<String, String> tptCfgMap) {
        this.srvId = srvId;
        this.source = source;
        this.tptCfgMap = tptCfgMap;
        for (Input ipt : iptLst) {
            ipt.setMaps(tptCfgMap, iptArgMap);
        }
    }

    public void setDeviceAndRole(String device, String role) {
        this.device = device;
        this.role = role;
    }

    public void setExecutedError(String error) {
        this.isExecuted = true;
        this.execErr = error;
        this.timestamp = new Date();
    }

    public void setExecutedOk() {
        this.isExecuted = true;
        this.execErr = null;
        this.timestamp = new Date();
    }

    public void setHidden(boolean isHidden) {
        this.isHidden = isHidden;
    }

    public void setPlatform(PlatformEnm platform) {
        this.platform = platform;
    }

    @Override
    public String toString() {
        String result = "Act Id: " + id + ", Role: " + role + ", Device: " + device + ", Type: " + type
                + ", Platform: " + platform + ", Visualize: " + visualize + ", Hidden: " + isHidden
                + ((srvId == null) ? "" : (", SrvId: " + srvId)) + ((trbId == null) ? "" : (", TrbId: " + trbId))
                + ((timestamp == null) ? "" : (", Tt: " + TTSDF.format(timestamp)));
        if (txtMap != null) {
            for (TextTypeEnm txtType : txtMap.getTypeSet()) {
                result += "\nAct Txt " + txtType + ": " + txtMap.getText(txtType);
            }
        }
        if (reqsEvl != null) {
            result += "\nAct Requirement AndType: " + reqsEvl.isAndType() + ", Result: " + reqsEvl.getResult();
            for (Reference ref : reqsEvl.getReferenceList()) {
                result += "\nAct " + ref;
            }
        }
        if (!iptLst.isEmpty()) {
            result += "\nAct Inputs:";
            for (Input ipt : iptLst) {
                result += "\n" + ipt;
            }
        }
        if (!argLst.isEmpty()) {
            result += "\nAct Arguments:";
            for (Argument arg : argLst) {
                result += "\n" + arg;
            }
        }
        if (!seqLst.isEmpty()) {
            result += "\nAct Sequence:";
            for (Operation seq : seqLst) {
                result += "\n" + seq;
            }
        }
        if (wait != null) {
            result += "\nAct Wait: " + wait + " secs";
        }
        return result;
    }

    @Validate
    public void validate() throws PersistenceException {
        if (timestampSt != null) {
            try {
                TTSDF.parse(timestampSt);
            } catch (Exception ex) {
                throw new PersistenceException("Wrong Action Date");
            }
        }
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
