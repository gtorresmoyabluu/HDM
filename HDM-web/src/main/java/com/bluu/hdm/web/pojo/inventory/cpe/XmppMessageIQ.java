package com.bluu.hdm.web.pojo.inventory.cpe;

import org.jivesoftware.smack.packet.IQ;

public class XmppMessageIQ extends IQ {

    private final String userIQ, pwdIQ;

    public XmppMessageIQ(String userIQ, String pwdIQ) {
        this.userIQ = userIQ;
        this.pwdIQ = pwdIQ;
    }

    @Override
    public String getChildElementXML() {
        
        StringBuilder sb = new StringBuilder();
        sb.append("<connectionRequest xmlns=\"urn:broadband-forum-org:cwmp:xmppConnReq-1-0\">");
        sb.append(String.format("<username>%s</username>", userIQ));
        sb.append(String.format("<password>%s</password>", pwdIQ));
        sb.append("</connectionRequest>");
        return sb.toString();
    }
}
