/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.service;

import com.bluu.hdm.cwmp.command.AddObjectCommand;
import com.bluu.hdm.cwmp.command.Command;
import com.bluu.hdm.cwmp.command.DeleteObjectCommand;
import com.bluu.hdm.cwmp.command.FilePath;
import com.bluu.hdm.cwmp.command.GetValueCommand;
import com.bluu.hdm.cwmp.command.RebootCommand;
import com.bluu.hdm.cwmp.command.SetValueCommand;
import com.bluu.hdm.cwmp.command.UpdateFirmwareCommand;
import com.bluu.hdm.cwmp.common.DeviceStatusInfo;
import com.bluu.hdm.cwmp.entity.Device;
import com.bluu.hdm.cwmp.entity.Policy;
import com.bluu.hdm.cwmp.entity.PolicyHistory;
import com.bluu.hdm.cwmp.entity.ServiceLog;
import com.bluu.hdm.cwmp.exception.AlreadyEnqueuedException;
import com.bluu.hdm.cwmp.main.XmppManager;
import com.bluu.hdm.cwmp.message.SetParameterValues;
import com.bluu.hdm.cwmp.model.DeviceInfo;
import com.bluu.hdm.cwmp.model.ModelUtils;
import com.bluu.hdm.cwmp.model.WebServiceConfig;
import com.bluu.hdm.cwmp.object.SimpleObject;
import com.bluu.hdm.cwmp.service.interfaces.DeviceManager;
import com.bluu.hdm.cwmp.service.interfaces.PolicyHistoryManager;
import com.bluu.hdm.cwmp.service.interfaces.ServiceLogManager;
import com.bluu.hdm.cwmp.tree.DataFileUtils;
import com.bluu.hdm.cwmp.utils.BeanUtils;
import com.bluu.hdm.cwmp.utils.ConfigUtils;
import com.bluu.hdm.cwmp.ws.response.BasicResponse;
import com.bluu.hdm.cwmp.ws.response.DeviceInfoResponse;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeoutException;
import javax.jws.WebService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jivesoftware.smack.SmackException.ConnectionException;

/**
 *
 * @author Gonzalo Torres
 */
@WebService(endpointInterface = "com.bluu.hdm.cwmp.service.ACSService")
public class ACSServiceImpl implements ACSService {

    private static final Logger logger = Logger.getLogger(ACSService.class);

    public static final String SUCCESS = "SUCCESS";
    public static final String FAILED = "FAIELD";
    public static final ServiceLogManager serviceLogManager = BeanUtils.getInstance().getBean("serviceLogManager", ServiceLogManager.class);
    public static final DeviceManager deviceManager = BeanUtils.getInstance().getBean("deviceManager", DeviceManager.class);

    private int getErrorCode(Exception ex) {
        if (ex instanceof AlreadyEnqueuedException) {
            return WebServiceConfig.ErrorCode.CPE_ENQUEUED_REQUEST;
        } else if (ex instanceof TimeoutException) {
            return WebServiceConfig.ErrorCode.CPE_REQUEST_TIMEOUT;
        } else if (ex instanceof ConnectionException) {
            return WebServiceConfig.ErrorCode.CPE_CONNECTION_ERROR;
        } else {
            return WebServiceConfig.ErrorCode.CPE_CONFIG_ERROR;
        }
    }

    @Override
    public BasicResponse deleteObject(String serialNumber, String path) {
        BasicResponse result = new BasicResponse();
        // save action log
        ServiceLog serviceLog = new ServiceLog();
        serviceLog.setActionName(ACSService.METHOD_DELETE_OBJECT);
        serviceLog.setActionStartTime(new Date());
        serviceLog.setSerialNumber(serialNumber);
        Device device = deviceManager.getDeviceBySerialNumber(serialNumber);
        if (device != null) {
            serviceLog.setIpDevice(device.getIpAddress());
        }
        try {
            Command cm = new DeleteObjectCommand(serialNumber, path);
            boolean ok = cm.executeCommand();

            if (ok) {
                result.addResultValue(true);
                result.setErrorCode(WebServiceConfig.ErrorCode.SUCCESS);
                result.setMessage(WebServiceConfig.Message.SUCCESS);

                serviceLog.setResult(SUCCESS);
                serviceLog.setActionEndTime(new Date());
                serviceLogManager.save(serviceLog);
            } else {
                serviceLog.setResult(FAILED);
                serviceLog.setError(cm.getException().getMessage());
                serviceLog.setActionEndTime(new Date());
                serviceLogManager.save(serviceLog);
                throw cm.getException();
            }

        } catch (Exception ex) {
            serviceLog.setResult(FAILED);
            serviceLog.setError(ex.getMessage());
            serviceLog.setActionEndTime(new Date());
            serviceLogManager.save(serviceLog);

            result.setErrorCode(getErrorCode(ex));
            result.setMessage(ex.getClass().getSimpleName() + ": " + ex.getMessage());
        }
        return result;
    }

    @Override
    public BasicResponse setValue(String serialNumber, ArrayList<SimpleObject> data) {
        BasicResponse result = new BasicResponse();
        ServiceLog serviceLog = new ServiceLog();
        serviceLog.setActionName(ACSService.METHOD_SETVALUE);
        serviceLog.setActionStartTime(new Date());
        serviceLog.setSerialNumber(serialNumber);
        Device device = deviceManager.getDeviceBySerialNumber(serialNumber);
        if (device != null) {
            serviceLog.setIpDevice(device.getIpAddress());
        }
        try {
            Command cm = new SetValueCommand(serialNumber, data);
            Date startTime = new Date();
            boolean ok = cm.executeCommand();
            if (ok) {
                result.addResultValue(true);
                result.setErrorCode(WebServiceConfig.ErrorCode.SUCCESS);
                result.setMessage(WebServiceConfig.Message.SUCCESS);

                serviceLog.setResult(SUCCESS);
                serviceLog.setActionEndTime(new Date());
                serviceLogManager.save(serviceLog);
            } else {
                serviceLog.setResult(FAILED);
                serviceLog.setError(cm.getException().getMessage());
                serviceLog.setActionEndTime(new Date());
                serviceLogManager.save(serviceLog);
                throw cm.getException();
            }
        } catch (Exception ex) {
            serviceLog.setResult(FAILED);
            serviceLog.setError(ex.getMessage());
            serviceLog.setActionEndTime(new Date());
            serviceLogManager.save(serviceLog);
            result.setErrorCode(getErrorCode(ex));
            result.setMessage(ex.getClass().getSimpleName() + ": " + ex.getMessage());
        }
        return result;
    }

    @Override
    public DeviceInfoResponse getDeviceInfo(String serialNumber, String connectionRequest) {
        DeviceInfoResponse result = new DeviceInfoResponse();
        ServiceLog serviceLog = new ServiceLog();
        serviceLog.setActionName(ACSService.METHOD_GET_DEVICE_INFO);
        serviceLog.setActionStartTime(new Date());
        serviceLog.setSerialNumber(serialNumber);
        Device device = deviceManager.getDeviceBySerialNumber(serialNumber);
        if (device != null) {
            serviceLog.setIpDevice(device.getIpAddress());
        }
        try {
            ArrayList<SimpleObject> dataModel = DataFileUtils.getListSimpleObjectFromFile(FilePath.Get_DeviceInfo);

            GetValueCommand cm = new GetValueCommand(serialNumber);
            cm.setConnectionRequestURL(connectionRequest);
            for (SimpleObject item : dataModel) {
                cm.addParam(item.getParameter());
            }
            boolean ok = cm.executeCommand();

            if (ok) {
                Map<String, String> data = cm.getReturnValue();
                DeviceInfo devInfo = ModelUtils.parseDeviceInfo(data, dataModel);

                //devInfo.setSerialNumber(serialNumber);
                result.setDeviceInfo(devInfo);
                result.setErrorCode(WebServiceConfig.ErrorCode.SUCCESS);
                result.setMessage(WebServiceConfig.Message.SUCCESS);

                serviceLog.setResult(SUCCESS);
                serviceLog.setActionEndTime(new Date());
                serviceLogManager.save(serviceLog);

                return result;
            } else {

                serviceLog.setResult(FAILED);
                serviceLog.setError(cm.getException().getMessage());
                serviceLog.setActionEndTime(new Date());
                serviceLogManager.save(serviceLog);

                throw cm.getException();
            }
        } catch (Exception ex) {
            serviceLog.setResult(FAILED);
            serviceLog.setError(ex.getMessage());
            serviceLog.setActionEndTime(new Date());
            serviceLogManager.save(serviceLog);

            result.addResultValue(false);
            result.setErrorCode(getErrorCode(ex));
            result.setMessage(ex.getClass().getSimpleName() + ": " + ex.getMessage());
        }
        return result;
    }

    @Override
    public BasicResponse upgradeFirmware(String serialNumber, String connectionRequest, String fileUrl,
            String version, String usernameFileServer, String passwordFileServer) {
        BasicResponse result = new BasicResponse();
        ServiceLog serviceLog = new ServiceLog();
        serviceLog.setActionName(ACSService.METHOD_UPGRADE_FIRMWARE);
        serviceLog.setActionStartTime(new Date());
        serviceLog.setSerialNumber(serialNumber);
        Device device = deviceManager.getDeviceBySerialNumber(serialNumber);
        if (device != null) {
            serviceLog.setIpDevice(device.getIpAddress());
        }
        try {
            logger.info(serialNumber + " Upgrad firmware starting....");
            UpdateFirmwareCommand cm = new UpdateFirmwareCommand(serialNumber);
            cm.setConnectionRequestURL(connectionRequest);
            cm.setFileVersion(version);
            cm.setUrlFileServer(fileUrl);
            cm.setUsernameFileServer(usernameFileServer);
            cm.setPasswordFileServer(passwordFileServer);
            boolean ok = cm.executeCommand();

            if (ok) {
                result.addValue("Status", String.valueOf(cm.getDownloadResponseStt()));
                result.addResultValue(true);
                result.setErrorCode(WebServiceConfig.ErrorCode.SUCCESS);
                result.setMessage(WebServiceConfig.Message.SUCCESS);

                serviceLog.setResult(SUCCESS);
                serviceLog.setActionEndTime(new Date());
                serviceLogManager.save(serviceLog);
            } else {

                serviceLog.setResult(FAILED);
                serviceLog.setError(cm.getException().getMessage());
                serviceLog.setActionEndTime(new Date());
                serviceLogManager.save(serviceLog);

                throw cm.getException();
            }
            logger.info(serialNumber + " Upgrad firmware end....");
        } catch (Exception ex) {
            serviceLog.setResult(FAILED);
            serviceLog.setError(ex.getMessage());
            serviceLog.setActionEndTime(new Date());
            serviceLogManager.save(serviceLog);

            result.setErrorCode(getErrorCode(ex));
            result.setMessage(ex.getClass().getSimpleName() + ": " + ex.getMessage());
        }
        return result;
    }

    public BasicResponse upgradeFirmwareByPolicy(String serialNumber,
            String fileUrl,
            String version,
            String usernameFileServer,
            String passwordFileServer,
            long policyId,
            DeviceInfo deviceInfo
    ) {
        BasicResponse result = new BasicResponse();
        ServiceLog serviceLog = new ServiceLog();
        serviceLog.setActionName(ACSService.METHOD_UPGRADE_FIRMWARE_POLICY);
        serviceLog.setActionStartTime(new Date());
        serviceLog.setSerialNumber(serialNumber);
        Device tmp = deviceManager.getDeviceBySerialNumber(serialNumber);
        if (tmp != null) {
            serviceLog.setIpDevice(tmp.getIpAddress());
        }
        try {
            if (StringUtils.isBlank(serialNumber)) {
                throw new Exception("Missing SerialNumber param");
            } else if (StringUtils.isBlank(fileUrl)) {
                throw new Exception("Missing fileUrl param");
            } else if (policyId < 1) {
                throw new Exception("Missing policyId param. policyId > 0");
            }

            // 1. Send command request to device
            UpdateFirmwareCommand cm = new UpdateFirmwareCommand(serialNumber);
            cm.setFileVersion(version);
            cm.setUrlFileServer(fileUrl);
            cm.setUsernameFileServer(usernameFileServer);
            cm.setPasswordFileServer(passwordFileServer);
            boolean ok = cm.executeCommand();
            // get Policy
            Policy policy = new Policy();
            Device device = new Device();

            policy.setId(policyId);
            device.setId(deviceInfo.getId());

            // 2. Update Policy is upgrading 
            PolicyHistoryManager policyHistoryManager = BeanUtils.getInstance().getBean("policyHistoryManager", PolicyHistoryManager.class);
            PolicyHistory policyHis = new PolicyHistory();
            policyHis.setStartTime(new Timestamp(new Date().getTime()));
            policyHis.setDescription("Cập nhật Firmware với chính sách: " + deviceInfo.getPolicyName());
            policyHis.setDeviceSerialNumber(serialNumber);
            policyHis.setFirmwareOldVersion(deviceInfo.getFirmwareVersion());
            policyHis.setFirmwareNewVersion(version);
            policyHis.setEndTime(null);
            policyHis.setPolicy(policy);
            policyHis.setDevice(device);

            if (ok) {
                serviceLog.setResult(SUCCESS);
                serviceLog.setActionEndTime(new Date());
                serviceLogManager.save(serviceLog);

                logger.info("upgradeFirmwareByPolicy success: sn=" + serialNumber + ", policyId=" + policyId);
                result.addValue("Status", String.valueOf(cm.getDownloadResponseStt()));
                result.addResultValue(true);
                result.setErrorCode(WebServiceConfig.ErrorCode.SUCCESS);
                result.setMessage(WebServiceConfig.Message.SUCCESS);

                // update success info
                policyHis.setStatus(DeviceStatusInfo.UPDATE_FIRMWARE_UPDATING);
                policyHistoryManager.save(policyHis);

                return result;
            } else {
                serviceLog.setResult(FAILED);
                serviceLog.setError(cm.getException().getMessage());
                serviceLog.setActionEndTime(new Date());
                serviceLogManager.save(serviceLog);

                logger.error("upgradeFirmwareByPolicy fail: sn=" + serialNumber + ", policyId=" + policyId + ", errorString: " + cm.getErrorString());

                // update failure info
                policyHis.setEndTime(new Timestamp(new Date().getTime()));
                policyHis.setStatus(DeviceStatusInfo.UPDATE_FIRMWARE_FAIL);
                policyHistoryManager.save(policyHis);

                throw cm.getException();
            }
        } catch (Exception ex) {
            serviceLog.setResult(FAILED);
            serviceLog.setActionEndTime(new Date());
            serviceLog.setError(ex.getMessage());
            serviceLogManager.save(serviceLog);

            result.setErrorCode(getErrorCode(ex));
            result.setMessage(ex.getClass().getSimpleName() + ": " + ex.getMessage());
        }
        return result;
    }

    @Override
    public BasicResponse rebootDevice(String serialNumber, String connectionRequests) {
        BasicResponse result = new BasicResponse();
        ServiceLog serviceLog = new ServiceLog();
        serviceLog.setActionName(ACSService.METHOD_REBOOT_DEVICE);
        serviceLog.setActionStartTime(new Date());
        serviceLog.setSerialNumber(serialNumber);
        Device device = deviceManager.getDeviceBySerialNumber(serialNumber);
        if (device != null) {
            serviceLog.setIpDevice(device.getIpAddress());
        }
        try {
            Command cm = new RebootCommand(serialNumber);
            cm.setConnectionRequestURL(connectionRequests);
            boolean ok = cm.executeCommand();

            if (ok) {
                serviceLog.setResult(SUCCESS);
                serviceLog.setActionEndTime(new Date());
                serviceLogManager.save(serviceLog);

                result.addResultValue(true);
                result.setErrorCode(WebServiceConfig.ErrorCode.SUCCESS);
                result.setMessage(WebServiceConfig.Message.SUCCESS);
            } else {
                serviceLog.setResult(FAILED);
                serviceLog.setActionEndTime(new Date());
                serviceLog.setError(cm.getException().getMessage());
                serviceLogManager.save(serviceLog);

                throw cm.getException();
            }
        } catch (Exception ex) {
            serviceLog.setResult(FAILED);
            serviceLog.setActionEndTime(new Date());
            serviceLog.setError(ex.getMessage());
            serviceLogManager.save(serviceLog);

            result.setErrorCode(getErrorCode(ex));
            result.setMessage(ex.getClass().getSimpleName() + ": " + ex.getMessage());
        }
        return result;
    }

    public BasicResponse getDataTree(String serialNumber, String dataTreeItem, String connectionRequest) {
        BasicResponse result = new BasicResponse();
        ServiceLog serviceLog = new ServiceLog();
        serviceLog.setActionName(ACSService.METHOD_GETDATA_TREE);
        serviceLog.setActionStartTime(new Date());
        serviceLog.setSerialNumber(serialNumber);
        Device device = deviceManager.getDeviceBySerialNumber(serialNumber);
        if (device != null) {
            serviceLog.setIpDevice(device.getIpAddress());
        }
        try {
            if (StringUtils.isBlank(serialNumber)) {
                throw new Exception("Missing SerialNumber param");
            } else if (dataTreeItem == null || dataTreeItem.isEmpty()) {
                throw new Exception("Missing dataTreeItem param");
            }
            GetValueCommand cm = new GetValueCommand(serialNumber, dataTreeItem);
            cm.setConnectionRequestURL(connectionRequest);

            boolean ok = cm.executeCommand();
            if (ok) {
                Map<String, String> datas = cm.getReturnValue();
                SortedSet<String> keys = new TreeSet<String>(datas.keySet());
                for (String key : keys) {
                    result.addValue(key, datas.get(key));
                }

                result.setErrorCode(WebServiceConfig.ErrorCode.SUCCESS);
                result.setMessage(WebServiceConfig.Message.SUCCESS);

                serviceLog.setResult(SUCCESS);
                serviceLog.setActionEndTime(new Date());
                serviceLogManager.save(serviceLog);

            } else if (cm.getException() != null) {
                serviceLog.setResult(FAILED);
                serviceLog.setActionEndTime(new Date());
                serviceLog.setError(cm.getException().getMessage());
                serviceLogManager.save(serviceLog);
                throw cm.getException();
            } else {
                serviceLog.setResult(FAILED);
                serviceLog.setActionEndTime(new Date());
                serviceLog.setError(cm.getException().getMessage());
                serviceLogManager.save(serviceLog);
                throw cm.getException();
            }
        } catch (Exception ex) {
            serviceLog.setResult(FAILED);
            serviceLog.setActionEndTime(new Date());
            serviceLog.setError(ex.getMessage());
            serviceLogManager.save(serviceLog);

            result.setErrorCode(getErrorCode(ex));
            result.setMessage(ex.getClass().getSimpleName() + ": " + ex.getMessage());
        }
        return result;
    }

    public BasicResponse setUserAccount(String serialNumber, String userName, String password, String authURL) {
        BasicResponse result = new BasicResponse();
        ServiceLog serviceLog = new ServiceLog();
        serviceLog.setActionName(ACSService.METHOD_SET_USERACCOUNT);
        serviceLog.setActionStartTime(new Date());
        serviceLog.setSerialNumber(serialNumber);
        Device device = deviceManager.getDeviceBySerialNumber(serialNumber);
        if (device != null) {
            serviceLog.setIpDevice(device.getIpAddress());
        }
        try {
            ArrayList<SimpleObject> dataModel = DataFileUtils.getListSimpleObjectFromFile(FilePath.Set_UserAccount);
            ArrayList<SimpleObject> setValues = new ArrayList<SimpleObject>();
            for (SimpleObject model : dataModel) {
                String token = model.getName();
                if ("Username".equalsIgnoreCase(token)) {
                    model.setValue(userName);
                } else if ("Password".equalsIgnoreCase(token)) {
                    model.setValue(password);
                } else if ("AuthenticationURL".equalsIgnoreCase(token)) {
                    model.setValue(authURL);
                } else {
                    continue;
                }
                setValues.add(model);
            }

            SetValueCommand cm = new SetValueCommand(serialNumber, setValues);
            boolean ok = cm.executeCommand();
            if (ok) {

                serviceLog.setResult(SUCCESS);
                serviceLog.setActionEndTime(new Date());
                serviceLogManager.save(serviceLog);

                result.addResultValue(ok);
                result.setErrorCode(WebServiceConfig.ErrorCode.SUCCESS);
                result.setMessage(WebServiceConfig.Message.SUCCESS);
            } else {
                serviceLog.setResult(FAILED);
                serviceLog.setActionEndTime(new Date());
                serviceLog.setError(cm.getException().getMessage());
                serviceLogManager.save(serviceLog);

                result.addResultValue(ok);
                result.setErrorCode(WebServiceConfig.ErrorCode.CPE_CONFIG_ERROR);
                result.setMessage(cm.getErrorString());
            }

        } catch (Exception ex) {
            serviceLog.setResult(FAILED);
            serviceLog.setActionEndTime(new Date());
            serviceLog.setError(ex.getMessage());
            serviceLogManager.save(serviceLog);

            result.addResultValue(false);
            result.setErrorCode(getErrorCode(ex));
            result.setMessage(ex.getClass().getSimpleName() + ": " + ex.getMessage());
        }
        return result;
    }

    public BasicResponse getCPEStatus(String serialNumber) {
        BasicResponse result = new BasicResponse();
        ServiceLog serviceLog = new ServiceLog();
        serviceLog.setActionName(ACSService.METHOD_GETCPE_STATUS);
        serviceLog.setActionStartTime(new Date());
        serviceLog.setSerialNumber(serialNumber);
        Device device = deviceManager.getDeviceBySerialNumber(serialNumber);
        if (device != null) {
            serviceLog.setIpDevice(device.getIpAddress());
        }
        try {
            if (StringUtils.isBlank(serialNumber)) {
                throw new Exception("Missing SerialNumber param");
            }
            int status = XmppManager.getInstance().getUserStatus(serialNumber);
            String message = (status == XmppManager.XmppFriend.OFFLINE)
                    ? "CPE is Offline" : "CPE is Online";

            result.addValue("Status", status);
            result.addValue(XmppManager.XmppFriend.OFFLINE, "Offline");
            result.addValue(XmppManager.XmppFriend.ONLINE, "Online");
            result.addValue(XmppManager.XmppFriend.AWAY, "Away");
            result.addValue(XmppManager.XmppFriend.BUSY, "Busy");

            result.setErrorCode(WebServiceConfig.ErrorCode.SUCCESS);
            result.setMessage(message);

            serviceLog.setResult(SUCCESS);
            serviceLog.setActionEndTime(new Date());
            serviceLogManager.save(serviceLog);
            return result;
        } catch (Exception ex) {
            serviceLog.setResult(FAILED);
            serviceLog.setActionEndTime(new Date());
            serviceLog.setError(ex.getMessage());
            serviceLogManager.save(serviceLog);

            result.setErrorCode(getErrorCode(ex));
            result.setMessage(ex.getClass().getSimpleName() + ": " + ex.getMessage());
        }
        return result;
    }

    public BasicResponse setDefaulGatewayDNS(String serialNumber, String connectionRequest) {
        BasicResponse result = new BasicResponse();
        ServiceLog serviceLog = new ServiceLog();
        serviceLog.setActionName(ACSService.METHOD_SET_DEFAULTGATEWAYDNS);
        serviceLog.setActionStartTime(new Date());
        serviceLog.setSerialNumber(serialNumber);
        Device device = deviceManager.getDeviceBySerialNumber(serialNumber);
        if (device != null) {
            serviceLog.setIpDevice(device.getIpAddress());
        }
        try {
            ArrayList<SimpleObject> connectionModel = DataFileUtils.getListSimpleObjectFromFile(FilePath.Set_DefaultConnection);
            ArrayList<SimpleObject> setValues = new ArrayList<SimpleObject>();
            for (SimpleObject model : connectionModel) {
                String token = model.getName();
                if ("DefaultGateway".equalsIgnoreCase(token)) {
                    model.setValue(ConfigUtils.getInstance().getProDefaultGW());
                } else if ("DefaultDNS".equalsIgnoreCase(token)) {
                    model.setValue(ConfigUtils.getInstance().getProDefaultDNS());
                } else {
                    continue;
                }
                setValues.add(model);
            }

            SetValueCommand cm = new SetValueCommand(serialNumber, setValues);
            cm.setConnectionRequestURL(connectionRequest);
            boolean ok = cm.executeCommand();
            if (ok) {
                result.addResultValue(ok);
                result.setErrorCode(WebServiceConfig.ErrorCode.SUCCESS);
                result.setMessage(WebServiceConfig.Message.SUCCESS);

                serviceLog.setResult(SUCCESS);
                serviceLog.setActionEndTime(new Date());
                serviceLogManager.save(serviceLog);
            } else {
                serviceLog.setResult(FAILED);
                serviceLog.setActionEndTime(new Date());
                serviceLog.setError(cm.getException().getMessage());
                serviceLogManager.save(serviceLog);

                result.addResultValue(ok);
                result.setErrorCode(WebServiceConfig.ErrorCode.CPE_CONFIG_ERROR);
                result.setMessage(cm.getErrorString());
            }

        } catch (Exception ex) {
            serviceLog.setResult(FAILED);
            serviceLog.setActionEndTime(new Date());
            serviceLog.setError(ex.getMessage());
            serviceLogManager.save(serviceLog);

            result.addResultValue(false);
            result.setErrorCode(getErrorCode(ex));
            result.setMessage(ex.getClass().getSimpleName() + ": " + ex.getMessage());
        }
        return result;
    }

    public BasicResponse setIntervalAcsUrl(String serialNumber, String acsUrl, String connectionRequest, int interval) {
        BasicResponse result = new BasicResponse();
        ServiceLog serviceLog = new ServiceLog();
        serviceLog.setActionName(ACSService.METHOD_SET_INTERVAL_ACSURL);
        serviceLog.setActionStartTime(new Date());
        serviceLog.setSerialNumber(serialNumber);
        Device device = deviceManager.getDeviceBySerialNumber(serialNumber);
        if (device != null) {
            serviceLog.setIpDevice(device.getIpAddress());
        }
        try {
            ArrayList<SimpleObject> connectionModel = DataFileUtils.getListSimpleObjectFromFile(FilePath.SET_MANAGEMENT_SERVER);
            ArrayList<SimpleObject> setValues = new ArrayList<SimpleObject>();
            for (SimpleObject model : connectionModel) {
                String token = model.getName();
                if ("AcsUrl".equalsIgnoreCase(token)) {
                    model.setValue(acsUrl);
                } else if ("Interval".equalsIgnoreCase(token)) {
                    model.setValue(Integer.toString(interval));
                } else {
                    continue;
                }
                setValues.add(model);
            }

            SetValueCommand cm = new SetValueCommand(serialNumber, setValues);
            cm.setConnectionRequestURL(connectionRequest);
            boolean ok = cm.executeCommand();
            if (ok) {
                result.addResultValue(ok);
                result.setErrorCode(WebServiceConfig.ErrorCode.SUCCESS);
                result.setMessage(WebServiceConfig.Message.SUCCESS);

                serviceLog.setResult(SUCCESS);
                serviceLog.setActionEndTime(new Date());
                serviceLogManager.save(serviceLog);
            } else {
                serviceLog.setResult(FAILED);
                serviceLog.setActionEndTime(new Date());
                serviceLog.setError(cm.getException().getMessage());
                serviceLogManager.save(serviceLog);

                result.addResultValue(ok);
                result.setErrorCode(WebServiceConfig.ErrorCode.CPE_CONFIG_ERROR);
                result.setMessage(cm.getErrorString());
            }

        } catch (Exception ex) {
            serviceLog.setResult(FAILED);
            serviceLog.setActionEndTime(new Date());
            serviceLog.setError(ex.getMessage());
            serviceLogManager.save(serviceLog);

            result.addResultValue(false);
            result.setErrorCode(getErrorCode(ex));
            result.setMessage(ex.getClass().getSimpleName() + ": " + ex.getMessage());
        }
        return result;
    }

    public BasicResponse setStaticRoute(String destination, String subnetMask, String interfacename, String gateway, String connectionRequest, String serialNumber) {
        BasicResponse result = new BasicResponse();
        ServiceLog serviceLog = new ServiceLog();
        serviceLog.setActionName(ACSService.METHOD_SET_STATICROUTE);
        serviceLog.setActionStartTime(new Date());
        serviceLog.setSerialNumber(serialNumber);
        Device device = deviceManager.getDeviceBySerialNumber(serialNumber);
        if (device != null) {
            serviceLog.setIpDevice(device.getIpAddress());
        }
        try {
//            
            SetParameterValues msg = new SetParameterValues();

            ArrayList<String> lines1 = DataFileUtils.getLineFromFile(FilePath.STATIC_ROUTE_IP);
            ArrayList<SimpleObject> datas = DataFileUtils.getListSimpleObjectFromList(lines1, 1, lines1.size());

            datas.get(0).setValue(gateway);
            datas.get(1).setValue(subnetMask);
            datas.get(2).setValue(interfacename);
            datas.get(3).setValue("-1");
            datas.get(4).setValue(destination);
            datas.get(5).setValue("-1");
            datas.get(6).setValue("1");

            // Set params1
            AddObjectCommand cmd = new AddObjectCommand(serialNumber, connectionRequest, lines1.get(0), datas, AddObjectCommand.ADDOBJ_LAYER3, Command.USERNAME_DEFAULT, Command.PASSWORD_DEFAULT);
            boolean ok = cmd.executeCommand();

            if (ok) {
                result.addResultValue(true);
                result.setErrorCode(WebServiceConfig.ErrorCode.SUCCESS);
                result.setMessage(WebServiceConfig.Message.SUCCESS);

                serviceLog.setResult(SUCCESS);
                serviceLog.setActionEndTime(new Date());
                serviceLogManager.save(serviceLog);
            } else {
                serviceLog.setResult(FAILED);
                serviceLog.setActionEndTime(new Date());
                serviceLog.setError(cmd.getException().getMessage());
                serviceLogManager.save(serviceLog);

                result.addResultValue(false);
                result.setErrorCode(WebServiceConfig.ErrorCode.CPE_CONFIG_ERROR);
                result.setMessage(cmd.getErrorString());
            }
        } catch (Exception ex) {
            serviceLog.setResult(FAILED);
            serviceLog.setActionEndTime(new Date());
            serviceLog.setError(ex.getMessage());
            serviceLogManager.save(serviceLog);

            result.addResultValue(false);
            result.setErrorCode(getErrorCode(ex));
            result.setMessage(ex.getClass().getSimpleName() + ": " + ex.getMessage());
        }
        return result;
    }   
}
