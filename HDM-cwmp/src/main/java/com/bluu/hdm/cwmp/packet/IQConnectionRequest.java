/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.packet;

import org.jivesoftware.smack.packet.IQ;
import static org.jivesoftware.smack.packet.IQ.createResultIQ;

/**
 *
 * @author Gonzalo Torres
 */
public class IQConnectionRequest extends IQ {

    public static final String ELEMENT = "connectionRequest";
    public static final String NAMESPACE = "urn:broadband-forum-org:cwmp:xmppConnReq-1-0";

    protected String username;
    protected String password;

    public IQConnectionRequest() {
        super(ELEMENT, NAMESPACE);
    }

    /**
     * CPE Username
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    @Override
//    public String getChildElementXML() {
//        String query
//                = "<connectionRequest xmlns=\"urn:broadband-forum-org:cwmp:xmppConnReq-1-0\">"
//                + "<username>%s</username>"
//                + "<password>%s</password>"
//                + "</connectionRequest>";
//        return String.format(query, username, password);
//    }
    /**
     * Create new IQ Connection Request to CPE
     *
     * @param username CPE Username
     * @param password CPE Password
     */
    public IQConnectionRequest(String username, String password) {
        this();
        this.username = username;
        this.password = password;
        this.setType(IQ.Type.get);
    }

    @Override
    protected IQ.IQChildElementXmlStringBuilder getIQChildElementBuilder(IQ.IQChildElementXmlStringBuilder xml) {
        xml.rightAngleBracket();
        xml.append("<username>").append(username).append("</username>");
        xml.append("<password>").append(password).append("</password>");
        return xml;
    }

    public IQ getReply() {
        return createResultIQ(this);
    }

    public static IQConnectionRequest createResponse(IQ request) {
        IQConnectionRequest res = new IQConnectionRequest();
        res.setType(IQ.Type.result);
        res.setTo(request.getFrom());
        res.setUsername("ok");
        res.setPassword("ok");
        return res;
    }
}
