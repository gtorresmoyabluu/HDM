package com.bluu.hdm.web.face.inventory;

import com.bluu.hdm.web.exception.CwmpException;
import com.bluu.hdm.web.pojo.inventory.Cpe;
import com.bluu.hdm.web.pojo.inventory.cpe.CwmpMessage;
import com.bluu.hdm.web.pojo.inventory.cpe.CwmpTask;
import com.bluu.hdm.web.pojo.inventory.cpe.InformCwmp;
import com.bluu.hdm.web.pojo.inventory.cpe.MapEntry;
import com.bluu.hdm.web.rest.FactoryRest;
import com.bluu.sch.trb.enums.ConnectivityProtocolEnm;
import com.bluu.sch.trb.enums.OperationTypeEnm;
import com.bluu.sch.trb.exception.AgtException;
import com.bluu.sch.trb.pojo.Operation;
import com.bluu.sch.trb.pojo.Parameter;
import com.bluu.sch.trb.pojo.Template;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.log4j.Logger;
import java.util.HashMap;
import org.jivesoftware.smack.AccountManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smackx.Form;
import org.jivesoftware.smackx.ReportedData;
import org.jivesoftware.smackx.search.UserSearch;
import org.jivesoftware.smackx.search.UserSearchManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
@LocalBean
public class Cwmp {

    private final Logger logger = Logger.getLogger(Cwmp.class);

    //private final SchLogger trbLogger = new SchLogger();
    private ObjectMapper mapper;
    private static final int CWMP_RESPONSE_TIMEOUT = 120; // seconds
    private LinkedHashMap<String, String> valueMap = new LinkedHashMap<>();
    private static final int SOCKET_TIMEOUT = 3000, CONNECT_TIMEOUT = 10000; // milliseconds
    private final SchLogger trbLogger = new SchLogger();
//    private CwmpSt cwmpSt = new CwmpSt();

    private CwmpSt cwmpSt = new CwmpSt();
    
    @Inject
    private TemplateSt templates;

    public Cwmp() {
        if (mapper == null) {
            mapper = new ObjectMapper();
        }
        //templates.loadTemplates();
    }

    public void doExecuteAction(String cwmpUser, String cwmpUrl, LinkedHashMap<String, String> paramMap) throws Exception {
        try {
            cwmpSt.setActionTask(cwmpUser, new CwmpTask(paramMap));
            requestConnection(cwmpUser, cwmpUrl);
        } catch (Exception ex) {
            logger.error("cwmp(doExecuteAction): " + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    public void doReboot(String cwmpId) throws Exception {
        try {
            Cpe cpe = mapper.convertValue(FactoryRest.getInstance().getRestAPI(String.format("deviceviewer/find/%s", cwmpId)), Cpe.class);
            if (cpe == null) {
                throw new Exception("CPE not found in database");
            }
            //Connection con = cpe.getConnection();
            doReboot("74dada-DIR-878-74dadaebfb83", cpe.getUrlCpe());
        } catch (Exception ex) {
            logger.error("cwmp(doReboot): " + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    public void doReboot(String cwmpUser, String cwmpUrl) throws Exception {
        try {
            cwmpSt.setRebootTask(cwmpUser);
            requestConnection(cwmpUser, cwmpUrl);
        } catch (Exception ex) {
            logger.error("cwmp(doReboot): " + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    public void doAddObject(String cwmpId, String objectName, String parameterKey) throws Exception {
        try {
            Cpe cpe = mapper.convertValue(FactoryRest.getInstance().getRestAPI(String.format("deviceviewer/find/%s", cwmpId)), Cpe.class);
            if (cpe == null) {
                throw new Exception("CPE not found in database");
            }
            //Connection con = cpe.getConnection();
            String cwmpUser = "74dada-DIR-878-74dadaebfb83";//con.getUsercomr();
            cwmpSt.setAddObjectTask(cwmpUser, new CwmpTask(objectName, parameterKey));
            requestConnection(cwmpUser, cpe.getUrlCpe());
        } catch (Exception ex) {
            logger.error("cwmp(doAddObject): " + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    public void doDeleteObject(String cwmpId, String objectName, String parameterKey) throws Exception {
        try {
            Cpe cpe = mapper.convertValue(FactoryRest.getInstance().getRestAPI(String.format("deviceviewer/find/%s", cwmpId)), Cpe.class);
            if (cpe == null) {
                throw new Exception("CPE not found in database");
            }
            //Connection con = cpe.getConnection();
            String cwmpUser = "74dada-DIR-878-74dadaebfb83";//con.getUsercomr();
            cwmpSt.setDeleteObjectTask(cwmpUser, new CwmpTask(objectName, parameterKey));
            requestConnection(cwmpUser, cpe.getUrlCpe());
        } catch (Exception ex) {
            logger.error("cwmp(doDeleteObject): " + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    public void doSetParameterValues(String cwmpId, String key, String value) throws Exception {
        try {
            Cpe cpe = mapper.convertValue(FactoryRest.getInstance().getRestAPI(String.format("deviceviewer/find/%s", cwmpId)), Cpe.class);
            if (cpe == null) {
                throw new Exception("CPE not found in database");
            }
            //Connection con = cpe.getConnection();
            String cwmpUser = "74dada-DIR-878-74dadaebfb83";//con.getUsercomr();
            LinkedHashMap<String, String> valueMap = new LinkedHashMap<>();
            valueMap.put(key, value);
            CwmpTask task = new CwmpTask(valueMap);
            cwmpSt.setSetParamValuesTask(cwmpUser, task);
            requestConnection(cwmpUser, cpe.getUrlCpe());
        } catch (Exception ex) {
            logger.error("cwmp(doSetParameterValues): " + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    @SuppressWarnings("SleepWhileInLoop")
    public ArrayList<MapEntry> getParameterNames(String cwmpId, String paramPath, boolean isNextLevel) throws Exception {
        try {
            Cpe cpe = mapper.convertValue(FactoryRest.getInstance().getRestAPI(String.format("deviceviewer/find/%s", cwmpId)), Cpe.class);
            if (cpe == null) {
                throw new Exception("CPE not found in database");
            }
            //Connection con = cpe.getConnection();
            String cwmpUser = "74dada-DIR-878-74dadaebfb83";//con.getUsercomr();
            cwmpSt.setGetParamNamesTask(cwmpUser, new CwmpTask(paramPath, isNextLevel));
            requestConnection(cwmpUser, cpe.getUrlCpe());
            CwmpTask task;
            for (int i = CWMP_RESPONSE_TIMEOUT; i > 0; i--) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    logger.error("cwmp(getParameterNames): " + ex.getMessage());
                }
                if (((task = cwmpSt.getGetParamNamesTask(cwmpUser)) != null) && task.isCompleted()) {
                    cwmpSt.dropGetParamNamesTask(cwmpUser);
                    return task.getValueMapList();
                }
            }
            throw new Exception("Timeout waiting for getParameterNames response");
        } catch (Exception ex) {
            logger.error("cwmp(getParameterNames): " + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    @SuppressWarnings("SleepWhileInLoop")
    public ArrayList<MapEntry> getParameterValues(String cwmpId, String paramKey) throws Exception {
        try {
            Cpe cpe = mapper.convertValue(FactoryRest.getInstance().getRestAPI(String.format("deviceviewer/find/%s", cwmpId)), Cpe.class);
            if (cpe == null) {
                throw new Exception("CPE not found in database");
            }
            String cwmpUser = "74dada-DIR-878-74dadaebfb83";//admin";//con.getUsercomr();
            cwmpSt.setGetParamValuesTask(cwmpUser, new CwmpTask(paramKey));
            requestConnection(cwmpUser, cpe.getUrlCpe());
            CwmpTask task;
            for (int i = CWMP_RESPONSE_TIMEOUT; i > 0; i--) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    logger.error("cwmp(getParameterValues): " + ex.getMessage());
                }
                if (((task = cwmpSt.getGetParamValuesTask(cwmpUser)) != null) && task.isCompleted()) {
                    cwmpSt.dropGetParamValuesTask(cwmpUser);
                    return task.getValueMapList();
                }
            }
            throw new Exception("Timeout waiting for getParameterValues response");
        } catch (Exception ex) {
            logger.error("cwmp(getParameterValues): " + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    @SuppressWarnings("SleepWhileInLoop")
    public ArrayList<String> getRpcMethods(String cwmpId) throws Exception {
        try {
            Cpe cpe = mapper.convertValue(FactoryRest.getInstance().getRestAPI(String.format("deviceviewer/find/%s", cwmpId)), Cpe.class);
            if (cpe == null) {
                throw new Exception("CPE not found in database");
            }
            //Connection con = cpe.getConnection();
            String cwmpUser = "74dada-DIR-878-74dadaebfb83";//con.getUsercomr();
            cwmpSt.setGetRpcMethodsTask(cwmpUser);
            requestConnection(cwmpUser, cpe.getUrlCpe());
            CwmpTask task;
            for (int i = CWMP_RESPONSE_TIMEOUT; i > 0; i--) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    logger.error("cwmp(getRpcMethods): " + ex.getMessage());
                }
                if (((task = cwmpSt.getGetRpcMethodsTask(cwmpUser)) != null) && task.isCompleted()) {
                    cwmpSt.dropGetRpcMethodsTask(cwmpUser);
                    return task.getValueList();
                }
            }
            throw new Exception("Timeout waiting for getRpcMethods response");
        } catch (Exception ex) {
            logger.error("cwmp(E): " + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    public CwmpMessage getSoapResponse(CwmpMessage request) {
        CwmpMessage response = new CwmpMessage(request);
        String userName = request.getUserName();
        System.out.println("EL USUARIO POR REQUEST ES: " + userName);

        String reqType = request.getType();
        String reqIp = request.getIp();
        int reqRetryCnt = request.getRetryCount();

        /* //SOLO PARA PROBAR
        
          mapper.convertValue(FactoryRest.getInstance().getRestAPI(String.format("deviceviewer/cwmpFault/%s/%s/%s", userName, faultCode, faultString ), params),Cpe.class);*/
        try {
            String logMsg = "cwmp(Req): " + reqIp + " " + userName + " " + reqType;
            CwmpTask task;
            InformCwmp informAgt = new InformCwmp();
            switch (reqType) {
                case "AutonomousTransferComplete":
                    response.setType("AutonomousTransferCompleteResponse");
                    break;
                case "Fault":
                    response.setType("Fault");
                    String faultCode = request.getFaultCode();
                    String faultString = request.getFaultString();
                    String faultParam = request.getFaultParameter();
                    logMsg += " " + faultCode + " " + faultString + (faultParam == null ? "" : (" (" + faultParam + ")"));
                    mapper.convertValue(FactoryRest.getInstance().getRestAPI(String.format("deviceviewer/cwmpFault/%s/%s/%s", userName, faultCode, faultString)), Cpe.class);
                    //troubleshootDbBean.cwmpFault(userName, faultCode, faultString);
                    break;
                case "AddObjectResponse":
                    if (((task = cwmpSt.getAddObjectTask(userName)) != null) && task.isRequested()) {
                        task.setCompleted();
                    } else if (((task = cwmpSt.getXmppObjectCreationTask(reqIp)) != null) && task.isRequested()) {
                        String paramId = request.getInstanceNumber();
                        if (paramId != null) {
                            String cwmpUser = task.getParameter();
                            System.out.println("EL USUARIO POR TASK ES: " + userName);
                            String cwmpPrefix = task.getPrefix();
                            String xmppServerIp = "192.168.30.18";//configStBean.getConfiguration("xmppServerIp");
                            String xmppServerPort = "5222";//configStBean.getConfiguration("xmppServerPort");
                            String xmppServerDomain = "xmpp.cl.bluu.es";//configStBean.getConfiguration("xmppServerDomain");
                            response.addParameter(cwmpPrefix + ".XMPP.Connection." + paramId + ".Username", cwmpUser);
                            response.addParameter(cwmpPrefix + ".XMPP.Connection." + paramId + ".Password", cwmpUser);
                            response.addParameter(cwmpPrefix + ".XMPP.Connection." + paramId + ".Domain", xmppServerDomain);
                            response.addParameter(cwmpPrefix + ".XMPP.Connection." + paramId + ".Server.1.ServerAddress", xmppServerIp);
                            response.addParameter(cwmpPrefix + ".XMPP.Connection." + paramId + ".Server.1.Port", xmppServerPort);
                            response.addParameter(cwmpPrefix + ".XMPP.Connection." + paramId + ".Resource", cwmpUser);
                            response.addParameter(cwmpPrefix + ".XMPP.Connection." + paramId + ".Enable", "true");
                            response.addParameter(cwmpPrefix + ".ManagementServer.ConnReqAllowedJabberIDs", "acs" + "@" + xmppServerDomain + "/" + "acs");
                            response.addParameter(cwmpPrefix + ".ManagementServer.ConnReqXMPPConnection", cwmpPrefix + ".XMPP.Connection." + paramId + ".");

                            response.setType("SetParameterValues");
                        }
                        cwmpSt.dropXmppObjectCreationTask(reqIp);
                    }
                    logMsg += " " + request.getFaultCode(); // Status
                    break;

                case "DeleteObjectResponse":
                    if (((task = cwmpSt.getDeleteObjectTask(userName)) != null) && task.isRequested()) {
                        task.setCompleted();
                    }
                    logMsg += " " + request.getFaultCode(); // Status
                    break;
                case "GetParameterNamesResponse":
                    if (((task = cwmpSt.getGetParamNamesTask(userName)) != null) && task.isRequested()) {
                        task.setValueMap(request.getParameterMap());
                        task.setCompleted();
                    }
                    break;
                case "GetParameterValuesResponse":
                    if ((((task = cwmpSt.getTroubleshootTask(userName)) != null)
                            || ((task = cwmpSt.getTestInfoTask(userName)) != null)
                            || ((task = cwmpSt.getGetWifiConfigTask(userName)) != null)
                            || ((task = cwmpSt.getGetParamValuesTask(userName)) != null)) && task != null && task.isRequested()) {
                        task.setValueMap(request.getParameterMap());
                        task.setCompleted();
                    } else if (((task = cwmpSt.getXmppCheckTask(reqIp)) != null) && task.isRequested()) {
                        String cwmpUser = task.getParameter();
                        System.out.println("EL USUARIO POR TASK ES: " + userName);

                        String cwmpPrefix = task.getPrefix();
                        String paramId = null;
                        String objPref = cwmpPrefix + ".XMPP.Connection.";
                        LinkedHashMap<String, String> reqParMap = request.getParameterMap();
                        for (String key : reqParMap.keySet()) {
                            if (key.startsWith(objPref)) {
                                paramId = key.substring(objPref.length());
                                paramId = paramId.substring(0, paramId.indexOf('.'));
                            }
                        }
                        if (paramId == null) {
                            cwmpSt.setXmppObjectCreationTask(reqIp, new CwmpTask(cwmpUser, cwmpPrefix));
                            response.setAddDeleteObject(cwmpPrefix + ".XMPP.Connection.", "schaman");
                            response.setType("AddObject");
                            task.setRequested();
                        } else {
                            String xmppServerIp = "192.168.30.18";//configStBean.getConfiguration("xmppServerIp");
                            String xmppServerPort = "5222";//configStBean.getConfiguration("xmppServerPort");
                            String xmppServerDomain = "xmpp.cl.bluu.es";//configStBean.getConfiguration("xmppServerDomain");

                            response.addParameter(cwmpPrefix + ".XMPP.Connection." + paramId + ".Username", cwmpUser);
                            response.addParameter(cwmpPrefix + ".XMPP.Connection." + paramId + ".Password", cwmpUser);
                            response.addParameter(cwmpPrefix + ".XMPP.Connection." + paramId + ".Domain", xmppServerDomain);
                            response.addParameter(cwmpPrefix + ".XMPP.Connection." + paramId + ".Server.1.ServerAddress", xmppServerIp);
                            response.addParameter(cwmpPrefix + ".XMPP.Connection." + paramId + ".Server.1.Port", xmppServerPort);
                            response.addParameter(cwmpPrefix + ".XMPP.Connection." + paramId + ".Resource", cwmpUser);
                            response.addParameter(cwmpPrefix + ".XMPP.Connection." + paramId + ".Enable", "true");
                            response.addParameter(cwmpPrefix + ".ManagementServer.ConnReqAllowedJabberIDs", "acs" + "@" + xmppServerDomain + "/" + "acs");
                            response.addParameter(cwmpPrefix + ".ManagementServer.ConnReqXMPPConnection", cwmpPrefix + ".XMPP.Connection." + paramId + ".");
                            response.setType("SetParameterValues");
                        }
                        cwmpSt.dropXmppCheckTask(reqIp);
                    }
                    break;
                case "GetRPCMethods":
                    response.setType("GetRPCMethodsResponse");
                    break;
                case "GetRPCMethodsResponse":
                    if (((task = cwmpSt.getGetRpcMethodsTask(userName)) != null) && task.isRequested()) {
                        task.setValueList(request.getParameterList());
                        task.setCompleted();
                    }
                    break;
                case "Inform":
                    response.setType("InformResponse");
                    String id = request.getDeviceId();
                    String manufacturer = request.getManufacturer();
                    String model = request.getProductClass();
                    String serial = request.getSerialNumber();
                    int eventCode = request.getEventCode();
                    String url = "unknown";
                    String firmware = "unknown";
                    String prvCode = null;
                    String tptFile = null;
                    String cwmpPrefix = "";
                    String name = request.getObjectName();

                    LinkedHashMap<String, String> reqParMap = request.getParameterMap();
                    for (String key : reqParMap.keySet()) {
                        if (key.endsWith("ConnectionRequestURL")) {
                            url = reqParMap.get(key);
                            cwmpPrefix = key.substring(0, key.indexOf("."));
                        } else if (key.endsWith("SoftwareVersion")) {
                            firmware = reqParMap.get(key);
                        } else if (key.endsWith("ProvisioningCode")) {
                            prvCode = reqParMap.get(key);
                        }
                    }
                    boolean isPreprovActive = false;//configStBean.getConfiguration("acsProvActive").equalsIgnoreCase("true");
                    String cwmpProvCode = "74dada-DIR-878-74dadaebfb83";//configStBean.getConfiguration("acsProvCode");
                    templates.loadTemplates();
                    ArrayList<Template> tptLst = templates.getTemplateList(ConnectivityProtocolEnm.cwmp);
                    if (tptLst.isEmpty()) {
                        throw new CwmpException("Plantilla CWMP Inexistente");
                    }
                    reqParMap.put(cwmpPrefix + ".DeviceInfo.Manufacturer", manufacturer);
                    reqParMap.put(cwmpPrefix + ".DeviceInfo.ProductClass", model);
                    String role = null;
                    AgtCwmp agt = new AgtCwmp(userName, url, reqParMap, role, null, tptLst, null, null, null, null, this, trbLogger);
                    Template tpt = null;
                    try {
                        tpt = agt.getTemplate();
                        tptFile = tpt.getFile();
                        role = tpt.getRole();
                    } catch (AgtException notFound) {
                        logger.error("cwmp(Inform): " + notFound.getMessage());
                    }
                    String connType = "cwmp";
                    if (tpt != null) {
                        Parameter par = agt.getParameter("par_xmpp_supported");
                        if ((par != null) && Boolean.valueOf(par.getValue())) {
                            connType = "xmpp";
                            if (!id.isEmpty()) {
//                                if (CheckUserXMPP(id, id)) {
                                setUserXmppAcsCpe(id, id);
//                                }
                            }
                        }
                    }
                    informAgt.setIdCpe(id);
                    informAgt.setReqIpCpe(reqIp);
                    informAgt.setUserNameCpe(name);
                    informAgt.setSerialCpe(serial);
                    informAgt.setModelsCpe(model);
                    informAgt.setUrlCpe(url);
                    informAgt.setManufacturerCpe(manufacturer);
                    informAgt.setFirmwareNameCpe(firmware);
                    //informAgt.setRole(role);
                    informAgt.setConnTypeCpe(connType);
                    //troubleshootDbBean.cwmpInform(id, reqIp, userName, url, manufacturer, model, firmware, serial, role, connType, tptFile);
                    Cpe cpe = mapper.convertValue(FactoryRest.getInstance().getRestAPI(String.format("deviceviewer/find/%s", id)), Cpe.class);
                    try {
                        if (cpe == null) {
                            FactoryRest.getInstance().postRestAPI("manufacturers/add", informAgt);
                        }
                    } catch (Exception ex) {
                        logger.error("ERROR EN API");
                        ex.getCause();
                    }
                    if (request.isAuthenticated()) { // authenticated
                        if (!isPreprovActive || ((prvCode != null) && prvCode.equals(cwmpProvCode) && userName.equals(id))) {
                            if ((eventCode == 8) && ((task = cwmpSt.getTestInfoTask(userName)) != null)
                                    && task.isConfirmationNeeded() && !task.isConfirmed()) {
                                task.setConfirmed();
                            }
                            if (((task = cwmpSt.getTroubleshootTask(userName)) != null) && !task.isRequested()) { // Trb List
                                if (tpt == null) {
                                    throw new CwmpException("Device template not identified");
                                }
                                Parameter par = tpt.getParameter("par_group_troubleshoot");
                                if (par == null) {
                                    throw new CwmpException("Param \"par_group_troubleshoot\" not found tpt " + tpt.getFile());
                                }
                                ArrayList<String> parLst = new ArrayList<>();
                                for (Operation opr : par.getContentList()) {
                                    if (opr.getType() == OperationTypeEnm.parameter) {
                                        String tptParId = opr.getValue();
                                        Parameter tptPar = tpt.getParameter(tptParId);
                                        if (tptPar == null) {
                                            throw new CwmpException(
                                                    "Param \"" + tptParId + "\" tpt \"" + tpt.getFile() + "\" not found");
                                        }
                                        ArrayList<Operation> contLst = tptPar.getContentList();
                                        if (!contLst.isEmpty()) {
                                            parLst.add(contLst.get(0).getValue());
                                        }
                                    }
                                }
                                task.setValueList(parLst);
                            }
                            if (((task = cwmpSt.getGetWifiConfigTask(userName)) != null) && !task.isRequested()) { // WifiCfg
                                // List
                                if (tpt == null) {
                                    throw new CwmpException("Device template not identified");
                                }
                                Parameter par = tpt.getParameter("par_group_wificonfig");
                                if (par == null) {
                                    throw new CwmpException("Param \"par_group_wificonfig\" not found tpt " + tpt.getFile());
                                }
                                ArrayList<String> parLst = new ArrayList<>();
                                for (Operation opr : par.getContentList()) {
                                    if (opr.getType() == OperationTypeEnm.parameter) {
                                        String tptParId = opr.getValue();
                                        Parameter tptPar = tpt.getParameter(tptParId);
                                        if (tptPar == null) {
                                            throw new CwmpException(
                                                    "Param \"" + tptParId + "\" tpt \"" + tpt.getFile() + "\" not found");
                                        }
                                        ArrayList<Operation> contLst = tptPar.getContentList();
                                        if (!contLst.isEmpty()) {
                                            parLst.add(contLst.get(0).getValue());
                                        }
                                    }
                                }
                                task.setValueList(parLst);
                            }
                        } else { // not complete provisioning
                            response.setType("InformResponse");
                            cwmpSt.setProvisioningTask(reqIp, new CwmpTask(id, cwmpPrefix));
                            if (tpt != null) {
                                Parameter par = agt.getParameter("par_xmpp_supported");
                                if ((par != null) && Boolean.valueOf(par.getValue())) {
                                    cwmpSt.setXmppCheckTask(reqIp, new CwmpTask(reqIp, cwmpPrefix));
                                    if (!id.isEmpty()) {
//                                        if (CheckUserXMPP(id, id)) {
                                        setUserXmppAcsCpe(id, id);
//                                        }
                                    }
                                }
                            }
                        }
                    } else if (!isPreprovActive
                            // TODO: ZYXEL AMG1202-T10B does fail with autoprovision
                            || id.contains("AMG1202")
                            // cwmp(Req): 152.231.105.18 unknown Inform 2 1 28285D-AMG1202-T10B-S142E39022198 unknown unknown
                            // cwmp(Req): 152.231.105.18 unknown Fault 9006 Invalid parameter type
                            // (InternetGatewayDevice.ManagementServer.PeriodicInformEnable)
                            // cwmp(Req): 152.231.108.216 unknown Fault 9006 Invalid parameter type
                            // (InternetGatewayDevice.ManagementServer.PeriodicInformInterval)
                            // Analizar en ACS de maqueta como se autoprovisionaria
                            || ((prvCode != null) && prvCode.equals(cwmpProvCode))) {
                        // not authenticated and no autoprovision or provisioned
                        response = null;
                    } else { // not authenticated and autoprovision and not provisioned
                        response.setType("InformResponse");
                        cwmpSt.setProvisioningTask(reqIp, new CwmpTask(id, cwmpPrefix));
                        if (tpt != null) {
                            Parameter par = agt.getParameter("par_xmpp_supported");
                            if ((par != null) && Boolean.valueOf(par.getValue())) {
                                cwmpSt.setXmppCheckTask(reqIp, new CwmpTask(id, cwmpPrefix));
                                if (!id.isEmpty()) {
//                                    if (CheckUserXMPP(id, id)) {
                                    setUserXmppAcsCpe(id, id);
//                                    }
                                }
                            }
                        }
                    }
                    logMsg += " " + eventCode + " " + reqRetryCnt + " " + id + " "
                            + (((prvCode == null) || prvCode.isEmpty()) ? "unknown" : prvCode) + " "
                            + (tptFile == null ? "unknown" : tptFile);
                    break;
                case "SetParameterValuesResponse":
                    faultCode = request.getFaultCode();
                    logMsg += faultCode.equals("0") ? " Ok" : (" Fault " + faultCode + ": " + request.getFaultString());
                    if ((task = cwmpSt.getXmppCheckTask(reqIp)) != null) {
                        cwmpPrefix = task.getPrefix();
                        response.addParameter(cwmpPrefix + ".XMPP.");
                        response.setType("GetParameterValues");
                        task.setRequested();
                    } else if (!request.isAuthenticated()) {
                        response.setType("Empty");
                    }
                    break;
                case "TransferComplete":
                    response.setType("TransferCompleteResponse");
                    faultCode = request.getFaultCode();
                    logMsg += faultCode.equals("0") ? " Ok" : " Ok but some changes not applied";
                    break;
            }
            logger.info(logMsg);
            if ((response != null) && response.getType() == null) { // not evaluated
                if ((task = cwmpSt.getProvisioningTask(reqIp)) != null) {
                    String cwmpUser = task.getParameter();
                    System.out.println("EL USUARIO POR TASK ES: " + userName);
                    String cwmpPrefix = task.getPrefix();
                    String cwmpPassword = "admin";//configStBean.getConfiguration("acsPassword");
                    response.addParameter(cwmpPrefix + ".ManagementServer.Username", cwmpUser);
                    response.addParameter(cwmpPrefix + ".ManagementServer.Password", cwmpPassword);
                    // TODO: Volver a activar cuando la URL sea unica
                    // response.addParameter(cwmpPrefix + ".ManagementServer.URL", configStBean.getConfiguration("acsURL"));
                    response.addParameter(cwmpPrefix + ".ManagementServer.PeriodicInformEnable", "1");
                    response.addParameter(cwmpPrefix + ".ManagementServer.PeriodicInformInterval", "28800");//configStBean.getConfiguration("acsInformInterval"));
                    response.addParameter(cwmpPrefix + ".ManagementServer.ConnectionRequestUsername", cwmpUser);
                    response.addParameter(cwmpPrefix + ".ManagementServer.ConnectionRequestPassword", cwmpPassword);
                    response.addParameter(cwmpPrefix + ".DeviceInfo.ProvisioningCode", "admin");// configStBean.getConfiguration("acsProvCode"));
                    response.setType("SetParameterValues");
                    //troubleshootDbBean.cwmpResetUser(cwmpUser);
                    mapper.convertValue(FactoryRest.getInstance().getRestAPI(String.format("deviceviewer/cwmpResetUser/%s", cwmpUser)), Cpe.class);
                    cwmpSt.dropProvisioningTask(reqIp);
                } else if (request.isAuthenticated()) {
                    if ((((task = cwmpSt.getTroubleshootTask(userName)) != null)
                            || ((task = cwmpSt.getGetWifiConfigTask(userName)) != null)
                            || ((task = cwmpSt.getGetParamValuesTask(userName)) != null)
                            || (((task = cwmpSt.getTestInfoTask(userName)) != null)
                            && (!task.isConfirmationNeeded() || task.isConfirmed())))
                            && task != null && !task.isRequested() && !task.getValueList().isEmpty()) {
                        for (String par : task.getValueList()) {
                            response.addParameter(par);
                        }
                        response.setType("GetParameterValues");
                        task.setRequested();
                    } else if ((((task = cwmpSt.getActionTask(userName)) != null)
                            || ((task = cwmpSt.getSetParamValuesTask(userName)) != null)) && task != null
                            && !task.getValueMap().isEmpty()) {
                        LinkedHashMap<String, String> actParMap = task.getValueMap();
                        for (String key : actParMap.keySet()) {
                            response.addParameter(key, actParMap.get(key));
                        }
                        response.setType("SetParameterValues");
                        if (cwmpSt.getActionTask(userName) != null) {
                            cwmpSt.dropActionTask(userName);
                        } else if (cwmpSt.getSetParamValuesTask(userName) != null) {
                            cwmpSt.dropSetParamValuesTask(userName);
                        }
                    } else if (((task = cwmpSt.getGetParamNamesTask(userName)) != null) && !task.isRequested()) {
                        response.setGetParameterNames(task.getParameter(), task.isNextLevel());
                        response.setType("GetParameterNames");
                        task.setRequested();
                    } else if (((task = cwmpSt.getGetRpcMethodsTask(userName)) != null) && !task.isRequested()) {
                        response.setType("GetRPCMethods");
                        task.setRequested();
                    } else if (((task = cwmpSt.getAddObjectTask(userName)) != null) && !task.isRequested()) {
                        response.setAddDeleteObject(task.getParameter(), task.getPrefix());
                        response.setType("AddObject");
                        task.setRequested();
                    } else if (((task = cwmpSt.getDeleteObjectTask(userName)) != null) && !task.isRequested()) {
                        response.setAddDeleteObject(task.getParameter(), task.getPrefix());
                        response.setType("DeleteObject");
                        task.setRequested();
                    } else if (cwmpSt.getRebootTask(userName) != null) {
                        response.setType("Reboot");
                        cwmpSt.dropRebootTask(userName);
                    }
                }
            }
        } catch (Exception ex) {
            logger.error("cwmp(Err): " + reqIp + " " + userName + " " + reqType + " " + reqRetryCnt + ". Error : " + ex.getMessage());
        }
        return response;
    }

    @SuppressWarnings("SleepWhileInLoop")
    public LinkedHashMap<String, String> getTestInfo(String cwmpUser, String cwmpUrl, ArrayList<String> valLst,
            boolean isConfNeeded) throws Exception {
        long initt = (new Date()).getTime();
        try {
            cwmpSt.setTestInfoTask(cwmpUser, new CwmpTask(valLst, isConfNeeded));
            if (!isConfNeeded) {
                requestConnection(cwmpUser, cwmpUrl);
            }
            CwmpTask task;
            for (int i = CWMP_RESPONSE_TIMEOUT; i > 0; i--) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    logger.error("cwmp(getTestInfo): " + ex.getMessage());
                }
                if (((task = cwmpSt.getTestInfoTask(cwmpUser)) != null) && task.isCompleted()) {
                    cwmpSt.dropTestInfoTask(cwmpUser);
                    logger.info("cwmp(getTestInfo): " + ((new Date()).getTime() - initt) + " ms, " + cwmpUser);
                    return task.getValueMap();
                }
            }
            throw new Exception("Timeout waiting for getTestInfo response");
        } catch (Exception ex) {
            logger.error("cwmp(getTestInfo): " + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    @SuppressWarnings("SleepWhileInLoop")
    public LinkedHashMap<String, String> getTrobleshootInfo(String cwmpUser, String cwmpUrl) throws Exception {
        long initt = (new Date()).getTime();
        try {
            cwmpSt.setTroubleshootTask(cwmpUser);
            requestConnection(cwmpUser, cwmpUrl);
            CwmpTask task;
            for (int i = CWMP_RESPONSE_TIMEOUT; i > 0; i--) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                }
                if (((task = cwmpSt.getTroubleshootTask(cwmpUser)) != null) && task.isCompleted()) {
                    cwmpSt.dropTroubleshootTask(cwmpUser);
                    logger.info("cwmp(C) trb: " + ((new Date()).getTime() - initt) + " ms, " + cwmpUser);
                    return task.getValueMap();
                }
            }
            throw new Exception("Timeout waiting for getTrobleshootInfo response");
        } catch (Exception ex) {
            logger.error("cwmp(E): " + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    @SuppressWarnings("SleepWhileInLoop")
    public LinkedHashMap<String, String> getWifiConfig(String cwmpUser, String cwmpUrl) throws Exception {
        try {
            cwmpSt.setGetWifiConfigTask(cwmpUser);
            requestConnection(cwmpUser, cwmpUrl);
            CwmpTask task;
            for (int i = CWMP_RESPONSE_TIMEOUT; i > 0; i--) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                }
                if (((task = cwmpSt.getGetWifiConfigTask(cwmpUser)) != null) && task.isCompleted()) {
                    cwmpSt.dropGetWifiConfigTask(cwmpUser);
                    return task.getValueMap();
                }
            }
            throw new Exception("Timeout waiting for getWifiConfig response");
        } catch (Exception ex) {
            logger.error("cwmp(E): " + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    @SuppressWarnings("SleepWhileInLoop")
    public LinkedHashMap<String, String> getWifiSpectrum(String cwmpUser, String cwmpUrl) throws Exception {
        try {
            cwmpSt.setGetWifiSpectrumTask(cwmpUser);
            requestConnection(cwmpUser, cwmpUrl);
            CwmpTask task;
            for (int i = CWMP_RESPONSE_TIMEOUT; i > 0; i--) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                }
                if (((task = cwmpSt.getGetWifiSpectrumTask(cwmpUser)) != null) && task.isCompleted()) {
                    cwmpSt.dropGetWifiSpectrumTask(cwmpUser);
                    return task.getValueMap();
                }
            }
            throw new Exception("Timeout waiting for getWifiSpectrum response");
        } catch (Exception ex) {
            logger.error("cwmp(E): " + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    private void requestConnection(String cwmpUser, String cwmpUrl) throws Exception {
        try {
            if ((cwmpUser == null) || cwmpUser.isEmpty()) {
                throw new CwmpException("Wrong cwmp user " + cwmpUser);
            }
            String cwmpPassword = "admin";//configStBean.getConfiguration("acsPassword");
            if ((cwmpPassword == null) || cwmpPassword.isEmpty()) {
                throw new CwmpException("Wrong cwmp password " + cwmpPassword);
            }
            if ((cwmpUrl == null) || cwmpUrl.isEmpty()) {
                throw new CwmpException("Wrong cwmp url " + cwmpUrl);
            }
            HttpParams params = new BasicHttpParams();
            params.setParameter(CoreConnectionPNames.SO_TIMEOUT, SOCKET_TIMEOUT);
            params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, CONNECT_TIMEOUT);
            DefaultHttpClient httpclient = new DefaultHttpClient(params);
            httpclient.getCredentialsProvider().setCredentials(AuthScope.ANY,
                    new UsernamePasswordCredentials(cwmpUser, cwmpPassword));
            HttpResponse response = httpclient.execute(new HttpGet(cwmpUrl));
            int respCode = response.getStatusLine().getStatusCode();
            httpclient.getConnectionManager().shutdown();
            if (respCode != 200) {
                throw new CwmpException("Response Code " + respCode);
            }
            logger.info("cwmp(Con): " + cwmpUrl + " " + cwmpUser + " " + respCode);
        } catch (Exception ex) {
            throw new Exception(cwmpUrl + " " + cwmpUser + " RequestConnection - " + ex.getMessage());
        }
    }

    /**
     * Set and Send Stanza IQ from ACS to CPE with XMPP
     *
     * @param userCpe
     * @param pwdCpe
     */
    private void setUserXmppAcsCpe(String userXMPP, String pwdXMPP) {
        String xmppServerIp = "192.168.30.18";//configStBean.getConfiguration("xmppServerIp");
        String xmppServerPort = "5222";//configStBean.getConfiguration("xmppServerPort");
        String xmppServerDomain = "xmpp.cl.bluu.es";//configStBean.getConfiguration("xmppServerDomain");

        String userSend = "acs";
        String pwdSend = "acs";
        String resSend = "acs";

        ConnectionConfiguration conf = new ConnectionConfiguration(xmppServerIp, Integer.parseInt(xmppServerPort), xmppServerDomain);
        conf.setSecurityMode(SecurityMode.enabled);
        conf.setSASLAuthenticationEnabled(true);
//        conf.setSendPresence(true);
        XMPPConnection con = new XMPPConnection(conf);
        try {
            con.connect();
            if (!con.isConnected()) {
                logger.error(String.format("Error in connect Server XMPP %s.", xmppServerIp));
            } else {
                logger.info("Connect XMPP Server");
                con.login(userSend, pwdSend, resSend);

                Presence presence = new Presence(Presence.Type.available);
                con.sendPacket(presence);

                MessageIQ iq = new MessageIQ(userXMPP);
                iq.setFrom(userSend + "@" + xmppServerDomain + "/" + resSend);
                iq.setType(IQ.Type.GET);
                iq.setTo(userXMPP.toLowerCase() + "@" + xmppServerDomain + "/" + userXMPP);
                iq.setPacketID("cr001");

//                    logger.info("IQ Stanza ---> " + iq.getChildElementXML()); //returns null
                PacketCollector collector = con.createPacketCollector(new PacketTypeFilter(IQ.class));
                con.sendPacket(iq);
                IQ result = (IQ) collector.nextResult(SmackConfiguration.getPacketReplyTimeout());
                // Stop queuing results
                collector.cancel();
                if (result == null) {
                    logger.error(String.format("No response from server %s.", xmppServerIp));
                } else if (result.getType() == IQ.Type.RESULT) {
                    logger.info("Sent:     " + iq.toXML());
                    logger.info("Received: " + result.toXML());
                    logger.info("collector pollResult = " + collector.pollResult()); //returns null
                } else {
                    if (result.getError().toString().equalsIgnoreCase("conflict(409)")) {
                        logger.error(String.format("Regist IQ.Type.ERROR: %s", result.getError().toString()));
                    } else {
                        logger.error(String.format("Regist IQ.Type.ERROR: %s", result.getError().toString()));
                    }
                }
            }
        } catch (XMPPException ex) {
            logger.error(String.format("XMPPException: %s", ex.getMessage()));
        } finally {
            if (con.isConnected()) {
                con.disconnect();
                logger.info("Disconnect XMPP Server");
            }
        }
    }

    /**
     * Method verify if user exists in Openfire Server XMPP
     *
     * @param userXMPP
     * @param pwdXMPP
     * @return boolean
     */
    public boolean CheckUserXMPP(String userXMPP, String pwdXMPP) {
        boolean isCreate = false;
        String xmppServerIp = "192.168.30.18";//configStBean.getConfiguration("xmppServerIp");
        String xmppServerPort = "5222";//configStBean.getConfiguration("xmppServerPort");
        String xmppServerDomain = "xmpp.cl.bluu.es";//configStBean.getConfiguration("xmppServerDomain");

        ConnectionConfiguration conf = new ConnectionConfiguration(xmppServerIp, Integer.parseInt(xmppServerPort), xmppServerDomain);
        conf.setSecurityMode(SecurityMode.enabled);
        conf.setSASLAuthenticationEnabled(true);
        XMPPConnection con = new XMPPConnection(conf);
        try {
            con.connect();
            if (con.isConnected()) {
                con.login("admin", "admin");
                UserSearchManager manager = new UserSearchManager(con);
                String searchFormString = "search." + con.getServiceName();
                Form searchForm = manager.getSearchForm(searchFormString);
                Form answerForm = searchForm.createAnswerForm();
                UserSearch userSearch = new UserSearch();
                answerForm.setAnswer("Username", true);
                answerForm.setAnswer("search", userXMPP.toLowerCase());
                ReportedData results = userSearch.sendSearchForm(con, answerForm, searchFormString);
                if (!results.getRows().hasNext()) {
                    AccountManager accountManager = con.getAccountManager();
                    HashMap<String, String> att = new HashMap<>();
                    att.put("name", userXMPP);
                    accountManager.createAccount(userXMPP.toLowerCase(), pwdXMPP, att);
                    logger.info(String.format("User create: %s", userXMPP));
                    isCreate = true;
                } else {
                    isCreate = false;
                }
            } else {
                logger.error(String.format("Error CheckUserXMPP: Not connected XMPP Server %s", xmppServerIp));
            }
        } catch (XMPPException ex) {
            logger.error(String.format("Error CheckUserXMPP: %s", ex.getMessage()));
            con.disconnect();
            return isCreate;
        }
        con.disconnect();
        return isCreate;
    }

    public void connectUserXMPP(String usrXMPP, String pwdXMPP) {
        String xmppServerIp = "192.168.30.18";//configStBean.getConfiguration("xmppServerIp");
        String xmppServerPort = "5222";//configStBean.getConfiguration("xmppServerPort");
        String xmppServerDomain = "xmpp.cl.bluu.es";//configStBean.getConfiguration("xmppServerDomain");

        ConnectionConfiguration conf = new ConnectionConfiguration(xmppServerIp, Integer.parseInt(xmppServerPort), xmppServerDomain);
        conf.setSecurityMode(SecurityMode.enabled);
        conf.setSASLAuthenticationEnabled(true);
        XMPPConnection con = new XMPPConnection(conf);
        try {
            con.connect();
            if (con.isConnected()) {
                con.login(usrXMPP.toLowerCase(), pwdXMPP, usrXMPP);
            }
        } catch (XMPPException ex) {
            logger.error(String.format("Error connectUserXMPP: %s", ex.getMessage()));
        }
    }
}

class MessageIQ extends IQ {

    private String userIQ;

    public MessageIQ(String userIQ) {
        this.userIQ = userIQ;
    }

    @Override
    public String getChildElementXML() {
        String usrIQ = this.userIQ.isEmpty() ? "74dada-DIR-878-74dadaebfb83" : this.userIQ;
        String pwdIQ = "admin";

        StringBuilder sb = new StringBuilder();
        sb.append("<connectionRequest xmlns=\"urn:broadband-forum-org:cwmp:xmppConnReq-1-0\">");
        sb.append(String.format("<username>%s</username>", usrIQ));
        sb.append(String.format("<password>%s</password>", pwdIQ));
        sb.append("</connectionRequest>");
        return sb.toString();
    }

    public String getUserIQ() {
        return userIQ;
    }

    public void setUserIQ(String userIQ) {
        this.userIQ = userIQ;
    }

}
