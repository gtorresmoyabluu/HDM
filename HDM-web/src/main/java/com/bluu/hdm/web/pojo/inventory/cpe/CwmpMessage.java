package com.bluu.hdm.web.pojo.inventory.cpe;

import com.bluu.hdm.web.exception.CwmpException;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;



public class CwmpMessage implements Serializable {

    private static final String[] RPC_METHOD_ARR = {"GetRPCMethods", "Inform", "TransferComplete", "AutonomousTransferComplete"};
    private final ArrayList<String> paramLst = new ArrayList<>();
    private final LinkedHashMap<String, String> paramMap = new LinkedHashMap<>();
    // private final Logger logger = Logger.getLogger(CwmpMessage.class);
    private final String userName, ip;
    private boolean isGpnWritable = false;
    private int retryCount = 0, eventCode = 0;
    private String id = "1", cwmp = "unknown", type, manufacturer, oui, productClass, serialNumber, faultCode, 
            faultString, faultParam, gpnParamPath = "", objectName, parameterKey, instanceNumber;

    public CwmpMessage(CwmpMessage soapRequest) {
        this.userName = soapRequest.userName;
        this.id = soapRequest.id;
        this.ip = soapRequest.ip;
    }

    public CwmpMessage(String userName, String ip) {
        this.userName = userName;
        this.ip = ip;
    }

    public void addParameter(String value) {
        paramLst.add(value);
    }

    public void addParameter(String key, String value) {
        paramMap.put(key, value);
    }

    public void doLoadRequest(String soapRequest) throws CwmpException {
        XMLStreamReader parser = null;
        try {
            // Error processing SAGEMCOM Header: /////////////////////////////////////////////////////////////////////////////////
            // whitespace missing in xmlns declarations
            // <SOAP-ENV:Envelopexmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/"xmlns:SOAP-ENC="http://schemas.xmlsoap.
            // org/soap/encoding/"xmlns:xsd="http://www.w3.org/2001/XMLSchema"xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance
            // "xmlns:cwmp="urn:dslforum-org:cwmp-1-0">
            soapRequest = soapRequest.replaceAll("xmlns:", " xmlns:");
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            // logger.info("cwmp(Req): " + soapRequest);
            XMLInputFactory factory = XMLInputFactory.newInstance();
            parser = factory.createXMLStreamReader(new ByteArrayInputStream(soapRequest.getBytes()));
            String tag = null;      // etiqueta xml
            String block = null;    // DeviceId | ParameterList | MethodList
            String paramName = null, paramValue = null; // Parameters
            int event;
            while ((event = parser.next()) != XMLStreamConstants.END_DOCUMENT) {
                if (event == XMLStreamConstants.START_ELEMENT) {
                    tag = parser.getLocalName();
                    if (tag.equalsIgnoreCase("Envelope")) {
                        for (int i = 0; i < parser.getNamespaceCount(); i++) {
                            if (parser.getNamespacePrefix(i).equalsIgnoreCase("cwmp")) {
                                cwmp = parser.getNamespaceURI(i);
                            }
                        }
                    } else if (tag.equalsIgnoreCase("AddObjectResponse") || tag.equalsIgnoreCase("DeleteObjectResponse")
                            || tag.equalsIgnoreCase("DownloadResponse") || tag.equalsIgnoreCase("Fault") || tag.equalsIgnoreCase("GetParameterNamesResponse")
                            || tag.equalsIgnoreCase("GetParameterValuesResponse") || tag.equalsIgnoreCase("GetRPCMethods")
                            || tag.equalsIgnoreCase("GetRPCMethodsResponse") || tag.equalsIgnoreCase("Inform") || tag.equalsIgnoreCase("RebootResponse")
                            || tag.equalsIgnoreCase("SetParameterValuesResponse") || tag.equalsIgnoreCase("TransferComplete")) {
                        this.type = tag;
                    } else if (tag.equalsIgnoreCase("detail") || tag.equalsIgnoreCase("DeviceId") || tag.equalsIgnoreCase("FaultStruct")
                            || tag.equalsIgnoreCase("MethodList") || tag.equalsIgnoreCase("ParameterList")) {
                        block = tag;
                    }
                } else if (event == XMLStreamConstants.END_ELEMENT) {
                    tag = parser.getLocalName();
                    if (tag.equalsIgnoreCase("detail") || tag.equalsIgnoreCase("DeviceId") || tag.equalsIgnoreCase("FaultStruct")
                            || tag.equalsIgnoreCase("MethodList") || tag.equalsIgnoreCase("ParameterList")) {
                        block = null;
                    } else if (tag.equalsIgnoreCase("ParameterValueStruct") || tag.equalsIgnoreCase("ParameterInfoStruct")) {
                        if (paramName != null) {
                            paramMap.put(paramName, paramValue);
                        }
                        paramName = null;
                        paramValue = null;
                    }
                    tag = null;
                } else if (((event == XMLStreamConstants.CHARACTERS) || (event == XMLStreamConstants.CDATA)) && (tag != null)) {
                    String text = parser.getText().trim();
                    if (!text.isEmpty()) {
                        if (block == null) {
                            if (tag.equalsIgnoreCase("ID")) {
                                this.id = text;
                            } else if (tag.equalsIgnoreCase("Status")) {
                                this.faultCode = text;
                            } else if (tag.equalsIgnoreCase("InstanceNumber")) {
                                this.instanceNumber = text;
                            } else if (tag.equalsIgnoreCase("RetryCount")) {
                                this.retryCount = Integer.parseInt(text);
                            } else if (tag.equalsIgnoreCase("EventCode")) {
                                this.eventCode = Integer.parseInt(text.substring(0, text.indexOf(" ")));
                            }
                        } else if (block.equalsIgnoreCase("detail")) {
                            if (tag.equalsIgnoreCase("FaultCode")) {
                                this.faultCode = text;
                            }
                            if (tag.equalsIgnoreCase("FaultString")) {
                                this.faultString = text;
                            }
                            if (tag.equalsIgnoreCase("ParameterName")) {
                                this.faultParam = text;
                            }
                        } else if (block.equalsIgnoreCase("DeviceId")) {
                            if (tag.equalsIgnoreCase("Manufacturer")) {
                                this.manufacturer = text;
                            } else if (tag.equalsIgnoreCase("OUI")) {
                                this.oui = text;
                            } else if (tag.equalsIgnoreCase("ProductClass")) {
                                this.productClass = text;
                            } else if (tag.equalsIgnoreCase("SerialNumber")) {
                                this.serialNumber = text;
                            }
                        } else if (block.equalsIgnoreCase("FaultStruct")) {
                            if (tag.equalsIgnoreCase("FaultCode")) {
                                this.faultCode = text;
                            }
                        } else if (block.equalsIgnoreCase("ParameterList")) {
                            if (tag.equalsIgnoreCase("Name")) {
                                paramName = text;
                                paramValue = "";
                            } else if (tag.equalsIgnoreCase("Value") || tag.equalsIgnoreCase("Writable")) {
                                paramValue = text;
                            }
                        } else if (block.equalsIgnoreCase("MethodList")) {
                            if (tag.equalsIgnoreCase("String")) {
                                paramLst.add(text);
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            throw new CwmpException(ex.getMessage());
        } finally {
            try {
                if (parser != null) {
                    parser.close();
                }
            } catch (Exception ignore) {
            }
        }
    }

    public String doLoadRequest(HttpServletRequest request) throws CwmpException {
        String soapRequest = "";
        this.type = "Empty";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            soapRequest = sb.toString();
            if (!soapRequest.isEmpty()) {
                doLoadRequest(soapRequest);
            }
        } catch (Exception ex) {
            throw new CwmpException(ex.getMessage());
        }
        return soapRequest;
    }

    public String getDeviceId() {
        String deviceId = "";
        if (oui != null) {
            deviceId = oui;
        }
        if (productClass != null) {
            deviceId += (deviceId.isEmpty() ? "" : "-") + productClass;
        }
        if (serialNumber != null) {
            deviceId += (deviceId.isEmpty() ? "" : "-") + serialNumber;
        }
        return deviceId;
    }

    public int getEventCode() {
        return eventCode;
    }

    public String getGetParameterNamesParameterPath() {
        return gpnParamPath;
    }

    public String getId() {
        return id;
    }

    public String getIp() {
        return ip;
    }

    public String getFaultCode() {
        return faultCode;
    }

    public String getFaultParameter() {
        return faultParam;
    }

    public String getFaultString() {
        return faultString;
    }

    public String getInstanceNumber() {
        return instanceNumber;
    }
    
    public String getManufacturer() {
        return manufacturer;
    }

    public String getObjectName() {
        return objectName;
    }

    public String getOUI() {
        return oui;
    }

    public String getParameter(String key) {
        return paramMap.containsKey(key) ? paramMap.get(key) : "unknown";
    }

    public String getParameterKey() {
        return parameterKey;
    }

    public ArrayList<String> getParameterList() {
        return paramLst;
    }

    public LinkedHashMap<String, String> getParameterMap() {
        return paramMap;
    }

    public String[] getRpcMethodArr() {
        return RPC_METHOD_ARR;
    }

    public String getProductClass() {
        return productClass;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getUserName() {
        return userName == null ? "unknown" : userName;
    }

    public String getType() {
        return type;
    }

    public boolean isAuthenticated() {
        return userName != null;
    }

    public boolean isGetParameterNamesNextLevel() {
        return isGpnWritable;
    }

    public void setGetParameterNames(String paramPath, boolean isNxtLevel) {
        this.gpnParamPath = paramPath;
        this.isGpnWritable = isNxtLevel;
    }

    public void setAddDeleteObject(String objectName, String parameterKey) {
        this.objectName = objectName;
        this.parameterKey = parameterKey;
    }

    public void setType(String value) {
        this.type = value;
    }

    @Override
    public String toString() {
        String tostring = "Req " + id + " " + cwmp + " " + retryCount + " " + getDeviceId() + " " + type;
        if (type.equals("Fault")) {
            tostring += " " + faultCode + " " + faultString + (faultParam == null ? "" : (" " + faultParam));
        }
        return tostring;
    }
}
