/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.pojo.inventory.cpe;


import com.bluu.hdm.web.pojo.inventory.cpe.Reference;
import com.bluu.hdm.web.enums.MessageSeverityEnm;
import com.bluu.hdm.web.enums.PlatformEnm;
import com.bluu.hdm.web.enums.VisualizeEnm;
import java.io.Serializable;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;


public class Message1 extends Evaluable implements Serializable {

    @Attribute(name = "severity", required = false)
    private MessageSeverityEnm severity = MessageSeverityEnm.info;
    @Attribute(name = "platform", required = false)
    private PlatformEnm platform = PlatformEnm.all;
    @Attribute(name = "visualize", required = false)
    private VisualizeEnm visualize = VisualizeEnm.iffalse;
    @Element(name = "text")
    private String text;

    public Message1() {
    }

    public Message1(Message1 msg) {
        super(msg);
        this.severity = msg.severity;
        this.platform = msg.platform;
        this.visualize = msg.visualize;
        this.text = msg.text;
    }    

    public Message1(String id, MessageSeverityEnm severity, String text) {
        super();
        this.id = id;
        this.severity = severity;
        this.text = text;
    }

    public void doAppendDeviceId2Id(String devId) {
        this.id += "_" + devId;
    }
    
    public PlatformEnm getPlatform() {
        return platform;
    }

    public MessageSeverityEnm getSeverity() {
        return severity;
    }

    public String getText() {
        return text;
    }

    public String getTextHtml() {
        String html = text;
        if (html.contains("[") && html.contains("]")) {
            int bracket1st = html.indexOf("[");
            int bracketlst = html.lastIndexOf("]");
            if (bracketlst > bracket1st) {
                String msgHead = html.startsWith("[") ? "" : (html.substring(0, bracket1st) + "<br/>");
                String msgTable = html.substring(bracket1st, bracketlst + 1);
                String msgTail = html.endsWith("]") ? "" : html.substring(bracketlst + 1, html.length());
                msgTable = msgTable.replaceAll("\\], \\[", "</td></tr><tr><td>");
                msgTable = msgTable.replaceAll("\\[", "<table><tr><td>");
                msgTable = msgTable.replaceAll("\\]", "</td></tr></table>");
                msgTable = msgTable.replaceAll(", ", "</td><td>");
                html = msgHead + msgTable + msgTail;
            }
        }
        return html;
    }

    public MsgTable getTextTable() {
        return new MsgTable(text);
    }

    public boolean isTableInMessage() {
        if (text.contains("[") && text.contains("]")) {
            int bracket1st = text.indexOf("[");
            int bracketlst = text.lastIndexOf("]");
            return bracketlst > bracket1st;
        }
        return false;
    }

    public boolean isSeverityError() {
        return severity == MessageSeverityEnm.error;
    }

    public boolean isSeverityInfo() {
        return severity == MessageSeverityEnm.info;
    }

    public boolean isSeverityWarning() {
        return severity == MessageSeverityEnm.warning;
    }

    public boolean isVisualized(boolean boolRes) {
        return (visualize == VisualizeEnm.ifrequired) || ((visualize == VisualizeEnm.iftrue) && boolRes)
                || ((visualize == VisualizeEnm.iffalse) && !boolRes);
    }

    public void setText(String val) {
        this.text = val;
    }

    @Override
    public String toString() {
        String result = "Msg Id: " + id + ", Severity: " + severity + ", Platform: "
                + platform + ", Visualize: " + visualize + "\nMsg Text: " + text;
        if (reqsEvl != null) {
            result += "\nMsg Requirement AndType: " + reqsEvl.isAndType() + ", Result: " + reqsEvl.getResult();
            for (Reference ref : reqsEvl.getReferenceList()) {
                result += "\nMsg " + ref;
            }
        }
        return result;
    }
}
