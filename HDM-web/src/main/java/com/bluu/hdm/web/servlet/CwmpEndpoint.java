package com.bluu.hdm.web.servlet;

import com.bluu.hdm.web.exception.CwmpException;
import com.bluu.hdm.web.face.inventory.Cwmp;
import com.bluu.hdm.web.pojo.inventory.cpe.CwmpMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.StringTokenizer;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.w3c.dom.DOMException;

public class CwmpEndpoint extends HttpServlet {
    private Cwmp cwmpBean = new Cwmp();
    private static final boolean IS_DIGEST_AUTHENTICATION = true;
    private static String password;
    private final Logger logger = Logger.getLogger(CwmpEndpoint.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcessRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcessRequest(request, response);
    }

    private String doAuthenticateBasic(HttpServletRequest request, String ipAddress) {
        String autUsername = null;
        try {
            if (password == null) {
                password = "admin";//configSt.getConfiguration("acsPassword");
            }
            String authorization = request.getHeader("Authorization");
            if (authorization != null) {
                StringTokenizer st = new StringTokenizer(authorization);
                if (st.hasMoreTokens()) {
                    String basic = st.nextToken();
                    if (basic.equalsIgnoreCase("Basic")) {
                        String credentials = new String(Base64.decodeBase64(st.nextToken()), "UTF-8");
                        int p = credentials.indexOf(":");
                        if (p != -1) {
                            autUsername = credentials.substring(0, p).trim();
                            String autPassword = credentials.substring(p + 1).trim();
                            if (autPassword.equals(password)) {
                                return autUsername;
                            } else {
                                logger.error("cwmp(Bas): " + ipAddress + " " + autUsername + " authentication error");
                            }
                        } else {
                            logger.error("cwmp(Bas): " + ipAddress + " invalid authentication token");
                        }
                    }
                }
            }
        } catch (UnsupportedEncodingException ex) {
            logger.error("cwmp(Bas): " + ipAddress + " " + autUsername + " authentication exception: " + ex.getMessage());
        }
        return null;
    }

    private String doAuthenticateDigest(HttpServletRequest request, String ipAddress){
        String autUsername = null;
        try {
            if (password == null) {
                password = "admin";//<<configSt.getConfiguration("acsPassword");
            }
            String authorization = request.getHeader("Authorization");
            if (authorization != null){
                authorization = authorization.substring(authorization.toLowerCase().indexOf("igest") + 5).trim();
                String autRealm = null, autQop = null, autUri = null, autNonce = null,
                        autCnonce = null, autNc = null, autResponse = null;
                String[] authArr = authorization.split(",");
                for (String authTok : authArr) {
                    String[] authTokArr = authTok.split("=", 2);
                    if (authTokArr[0].trim().equalsIgnoreCase("username")) {
                        autUsername = authTokArr[1].trim().replaceAll("\"", "");
                    } else if (authTokArr[0].trim().equalsIgnoreCase("realm")) {
                        autRealm = authTokArr[1].trim().replaceAll("\"", "");
                    } else if (authTokArr[0].trim().equalsIgnoreCase("qop")) {
                        autQop = authTokArr[1].trim().replaceAll("\"", "");
                    } else if (authTokArr[0].trim().equalsIgnoreCase("uri")) {
                        autUri = authTokArr[1].trim().replaceAll("\"", "");
                    } else if (authTokArr[0].trim().equalsIgnoreCase("nonce")) {
                        autNonce = authTokArr[1].trim().replaceAll("\"", "");
                    } else if (authTokArr[0].trim().equalsIgnoreCase("cnonce")) {
                        autCnonce = authTokArr[1].trim().replaceAll("\"", "");
                    } else if (authTokArr[0].trim().equalsIgnoreCase("nc")) {
                        autNc = authTokArr[1].trim().replaceAll("\"", "");
                    } else if (authTokArr[0].trim().equalsIgnoreCase("response")) {
                        autResponse = authTokArr[1].trim().replaceAll("\"", "");
                    }
                }
                if ((autUsername != null) && (autRealm != null) && (autQop != null) && (autUri != null)
                        && (autNonce != null) && (autCnonce != null) && (autNc != null) && (autResponse != null)) {
                    String A1 = autUsername + ":" + autRealm + ":" + password;
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    md.reset();
                    byte[] x = md.digest(A1.getBytes());
                    BigInteger hA1aux = new BigInteger(1, x);
                    String hA1 = hA1aux.toString(16);
                    String A2 = request.getMethod() + ":" + autUri;
                    md.reset();
                    x = md.digest(A2.getBytes());
                    BigInteger hA2aux = new BigInteger(1, x);
                    String hA2 = hA2aux.toString(16);
                    String resCalculate = hA1 + ":" + autNonce + ":" + autNc + ":" + autCnonce + ":" + autQop + ":" + hA2;
                    md.reset();
                    x = md.digest(resCalculate.getBytes());
                    BigInteger hresCalculateAux = new BigInteger(1, x);
                    String hresCalculate = hresCalculateAux.toString(16);
                    int aux = hresCalculate.length();
                    if (hresCalculate.length() != 32) {
                        for (int i = 0; i < 32 - aux; i++) {
                            hresCalculate = "0".concat(hresCalculate);
                        }
                    }
                    if (autResponse.equals(hresCalculate)) {
                        return autUsername; // Authentication OK
                    }
                }
            }
            // No authentication
            if (authorization == null) {
                logger.info("cwmp(Dig): " + ipAddress + " no authentication");
            } else {
                logger.error("cwmp(Dig): " + ipAddress + " " + autUsername + " authentication error");
            }
        } catch (NoSuchAlgorithmException ex) {
            logger.error("cwmp(Dig): " + ipAddress + " " + autUsername + " authentication exception: " + ex.getMessage());
        }
        return null;
    }

    private void doProcessRequest(HttpServletRequest request, HttpServletResponse response) {
        String reqIp = request.getRemoteAddr();
        String userName = null;
        try {
            // Ip Address from header
            String prxIp = request.getHeader("x-forwarded-for");
            if (prxIp != null) {
                reqIp = prxIp;
            }

            // Authentication Management /////////////////////////////////////////////////////////////////////////////////////////
            userName = IS_DIGEST_AUTHENTICATION ? doAuthenticateDigest(request, reqIp) : doAuthenticateBasic(request, reqIp);
            if (userName == null){ //&& configSt.getConfiguration("acsProvActive").equalsIgnoreCase("false")) {
                // User not authenticated and Preprovision Disabled //////////////////////////////////////////////////////////////
                if (IS_DIGEST_AUTHENTICATION) { // Return digest authentication params
                    byte[] nonce = new byte[16];
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    Random rnd = new Random();
                    rnd.nextBytes(nonce);
                    md.update(nonce);
                    BigInteger number = new BigInteger(1, nonce);
                    response.setHeader("WWW-Authenticate", "Digest realm=\"Schaman\",qop=\"auth\",nonce=\"" + number.toString(16) + "\"");
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
                }
            } else { // Authenticated or Preprovision Enabled ////////////////////////////////////////////////////////////////////
                // Resquest Management ///////////////////////////////////////////////////////////////////////////////////////////
                CwmpMessage soapRequest = new CwmpMessage(userName, reqIp);
//                soapRequest.doLoadRequest(request);
                // TODO: Comment ///////////////////////////////////////////////////////////////
                String reqMsg = soapRequest.doLoadRequest(request);
                if (!reqMsg.isEmpty()) {
                    logger.info("cwmp(Req): " + reqMsg);
                }

                //Add user in Server XMPP
               /* if (!soapRequest.getDeviceId().isEmpty()) {
                    logger.info("cwmp(Req): CheckUserXMPP " + soapRequest.getDeviceId());
                    if (cwmpBean.CheckUserXMPP(soapRequest.getDeviceId(), soapRequest.getDeviceId())) {
                        cwmpBean.connectUserXMPP(soapRequest.getDeviceId(), soapRequest.getDeviceId());
                    }
                }*/
                ////////////////////////////////////////////////////////////////////////////////
                CwmpMessage soapResponse = cwmpBean.getSoapResponse(soapRequest);
                // Response Management ///////////////////////////////////////////////////////////////////////////////////////////
                if (soapResponse != null) { // NULL => Provisioned, but not Authenticated
                    String responseType = soapResponse.getType() == null ? "Empty" : soapResponse.getType();
                    if (responseType.equals("Fault")) {
                        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400 Bad Request
                    } else {
                        SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
                        SOAPPart soapPart = soapMessage.getSOAPPart();
                        SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
                        soapEnvelope.addNamespaceDeclaration("cwmp", "urn:dslforum-org:cwmp-1-0");
                        soapEnvelope.addNamespaceDeclaration("xsi", "http://www.w3.org/2001/XMLSchema-instance");
                        soapEnvelope.addNamespaceDeclaration("xsd", "http://www.w3.org/2001/XMLSchema");
                        soapEnvelope.addNamespaceDeclaration("SOAP-ENC", "http://schemas.xmlsoap.org/soap/encoding/");
                        SOAPHeader soapHeader = soapEnvelope.getHeader();
                        SOAPFactory spf = SOAPFactory.newInstance();
                        SOAPHeaderElement elmntId = soapHeader.addHeaderElement(spf.createName("ID", "cwmp", "urn:dslforum-org:cwmp-1-0"));
                        elmntId.setAttribute("SOAP-ENV:mustUnderstand", "1");
                        elmntId.setValue(soapResponse.getId());
                        SOAPBody soapBody = soapEnvelope.getBody();
                        if (responseType.equals("Empty")) {
                            response.setStatus(HttpServletResponse.SC_NO_CONTENT); // 204 No Content
                        } else {
                            SOAPElement typeCont = soapBody.addChildElement(responseType, "cwmp");
                            switch (responseType) {
                                case "AddObject":
                                    typeCont.addChildElement(spf.createName("ObjectName")).setValue(soapResponse.getObjectName());
                                    typeCont.addChildElement(spf.createName("ParameterKey")).setValue(soapResponse.getParameterKey());
                                    break;
                                case "DeleteObject":
                                    typeCont.addChildElement(spf.createName("ObjectName")).setValue(soapResponse.getObjectName());
                                    typeCont.addChildElement(spf.createName("ParameterKey")).setValue(soapResponse.getParameterKey());
                                    break;
                                case "GetParameterNames":
                                    typeCont.addChildElement(spf.createName("ParameterPath"))
                                            .setValue(soapResponse.getGetParameterNamesParameterPath());
                                    typeCont.addChildElement(spf.createName("NextLevel"))
                                            .setValue(String.valueOf(soapResponse.isGetParameterNamesNextLevel()));
                                    break;
                                case "GetParameterValues":
                                    SOAPElement parNams = typeCont.addChildElement(spf.createName("ParameterNames"));
                                    ArrayList<String> paramLst = soapResponse.getParameterList();
                                    parNams.setAttribute("SOAP-ENC:arrayType", "xsd:string[" + String.valueOf(paramLst.size()) + "]");
                                    for (String param : paramLst) {
                                        SOAPElement soapElt = parNams.addChildElement("string");
                                        soapElt.setValue(param);
                                    }
                                    break;
                                case "GetRPCMethodsResponse":
                                    SOAPElement metLst = typeCont.addChildElement(spf.createName("MethodList"));
                                    String[] rpcMethArr = soapResponse.getRpcMethodArr();
                                    metLst.setAttribute("SOAP-ENC:arrayType", "xsd:string[" + String.valueOf(rpcMethArr.length) + "]");
                                    for (String element : rpcMethArr) {
                                        SOAPElement soapElt = metLst.addChildElement("string");
                                        soapElt.setValue(element);
                                    }
                                    break;
                                case "InformResponse":
                                    typeCont.addChildElement(spf.createName("MaxEnvelopes")).setValue("1");
                                    break;
                                case "Reboot":
                                    typeCont.addChildElement(spf.createName("CommandKey")).setValue("");
                                    break;
                                case "SetParameterValues":
                                    SOAPElement parList = typeCont.addChildElement(spf.createName("ParameterList"));
                                    LinkedHashMap<String, String> parMap = soapResponse.getParameterMap();
                                    parList.setAttribute("SOAP-ENC:arrayType", "cwmp:ParameterValueStruct[" + String.valueOf(parMap.size()) + "]");
                                    for (String key : parMap.keySet()) {
                                        SOAPElement parVal = parList.addChildElement(spf.createName("ParameterValueStruct"));
                                        parVal.addChildElement("Name").setValue(key);
                                        SOAPElement eltVal = parVal.addChildElement("Value");
                                        eltVal.setAttribute("xsi:type", "xsd:string");
                                        eltVal.setValue(parMap.get(key));
                                    }
                                    typeCont.addChildElement(spf.createName("ParameterKey")).setValue("");
                                    break;
                            }
                        }
                        soapMessage.saveChanges();
                        ByteArrayOutputStream out = new ByteArrayOutputStream();
                        soapMessage.writeTo(out);
                        response.setContentType("text/xml;charset=UTF-8");
                        response.setContentLength(out.size());
                        if (responseType.equals("Empty")) {
                            response.setHeader("Connection", "close");
                        } else {
                            response.setHeader("SOAPAction", "");
                            response.setHeader("Connection", "keep-alive");
                        }
                        String resMsg = out.toString();
                        response.getOutputStream().print(resMsg);
                        // TODO: Comment ///////////////////////////////////////////////////////////////
                        if (!responseType.equals("Empty")) {
                            logger.info("cwmp(Res): " + resMsg);
                        }
                        ////////////////////////////////////////////////////////////////////////////////
                    }
                    logger.info("cwmp(Res): " + reqIp + " " + soapResponse.getUserName() + " " + responseType);
                } else if (IS_DIGEST_AUTHENTICATION) { // Return digest authentication params
                    byte[] nonce = new byte[16];
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    Random rnd = new Random();
                    rnd.nextBytes(nonce);
                    md.update(nonce);
                    BigInteger number = new BigInteger(1, nonce);
                    response.setHeader("WWW-Authenticate", "Digest realm=\"Schaman\",qop=\"auth\",nonce=\"" + number.toString(16) + "\"");
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
                }
            }
        } catch (CwmpException | IOException | NoSuchAlgorithmException | SOAPException | DOMException ex) {
            logger.error("cwmp(Err): " + reqIp + " " + (userName == null ? "unknown" : userName) + " " + ex.getMessage());
            // response = empty
            response.setStatus(HttpServletResponse.SC_NO_CONTENT); // 204 No Content
            response.setContentType("text/xml;charset=UTF-8");
            response.setContentLength(0);
            response.setHeader("Connection", "close");
        }
    }
}
