package com.bluu.hdm.web.filter;

import com.bluu.hdm.web.enums.FilterOperationEnum;
import com.bluu.hdm.web.pojo.administracion.Access;
import com.bluu.hdm.web.pojo.firmware.Firmware;
import com.bluu.hdm.web.pojo.inventory.Manufacturer;
import com.bluu.hdm.web.pojo.inventory.Model;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;



public class DeviceFilter implements Filters {

    private String ip;
    private String mac;
    private String cpeid;
    private String error;
    private String status;

    private List<String> hostnames;
    private List<String> nodes;
    private List<String> provisiongroups;

    private List<Access> accesses;
    private List<Firmware> firmwares;
    private List<Manufacturer> manufacturers;
    private List<Model> models;
    
    @Override
    public List<FilterField> getFilters() {
        List<FilterField> filters = new ArrayList<>();
        if (StringUtils.isNotBlank(cpeid)) {
            filters.add(new FilterField("cpe.cpeid", FilterOperationEnum.LIKE, cpeid));
        }
        if (StringUtils.isNotBlank(mac)) {
            filters.add(new FilterField("cpe.mac", FilterOperationEnum.LIKE, mac));
        }
        if (StringUtils.isNotBlank(ip)) {
            filters.add(new FilterField("cpe.ip", FilterOperationEnum.LIKE, ip));
        }
        if (StringUtils.isNotBlank(status)) {
            filters.add(new FilterField("cpe.status", FilterOperationEnum.LIKE, status));
        }
        if (StringUtils.isNotBlank(error)) {
            filters.add(new FilterField("cpe.error", FilterOperationEnum.LIKE, error));
        }

        if (provisiongroups != null && provisiongroups.size() > 0) {
            addStringConditionsList(filters, provisiongroups, "acc.provisiongroup");
        }
        if (nodes != null && nodes.size() > 0) {
            addStringConditionsList(filters, nodes, "acc.node");
        }
        if (hostnames != null && hostnames.size() > 0) {
            addStringConditionsList(filters, hostnames, "acc.hostname");
        }
        if (manufacturers != null && manufacturers.size() > 0) {
            addObjectConditionsList(filters, manufacturers, "man.id");
        }
        if (models != null && models.size() > 0) {
            addObjectConditionsList(filters, models, "mdl.id");
        }
        if (firmwares != null && firmwares.size() > 0) {
            addObjectConditionsList(filters, firmwares, "fir.id");
        }
        return filters;
    }

    @Override
    public boolean isFilled() {
        return StringUtils.isNotBlank(cpeid) || StringUtils.isNotBlank(mac) || StringUtils.isNotBlank(ip) || StringUtils.isNotBlank(status)
            || StringUtils.isNotBlank(error) || (accesses != null && accesses.size() > 0) || (firmwares != null && firmwares.size() > 0);
            
    }

    private void addObjectConditionsList(List<FilterField> filters, List<?> list, String leftSide) {
        try {
            List<FilterField> orNameConditions = new ArrayList<>();
            for (Object obj : list) {
                String id = (obj.getClass().getMethod("getId")).invoke(obj).toString();
                orNameConditions.add(new FilterField(leftSide, FilterOperationEnum.EQ, id));
            }
            filters.add(new FilterField(orNameConditions));
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException ignore) {
        }
    }

    private void addStringConditionsList(List<FilterField> filters, List<String> list, String leftSide) {
        try {
            List<FilterField> orNameConditions = new ArrayList<>();
            for (String s : list) {
                orNameConditions.add(new FilterField(leftSide, FilterOperationEnum.EQ, s));
            }
            filters.add(new FilterField(orNameConditions));
        } catch (Exception ignore) {
        }
    }

    public List<Access> getAccesses() {
        return accesses;
    }

    public String getCpeid() {
        return cpeid;
    }

    public String getError() {
        return error;
    }

    public List<Firmware> getFirmwares() {
        return firmwares;
    }

    public List<String> getHostnames() {
        return hostnames;
    }

    public String getIp() {
        return ip;
    }

    public String getMac() {
        return mac;
    }

    public List<Manufacturer> getManufacturers() {
        return manufacturers;
    }

    public List<Model> getModels() {
        return models;
    }

    public List<String> getNodes() {
        return nodes;
    }

    public List<String> getProvisiongroups() {
        return provisiongroups;
    }

    public String getStatus() {
        return status;
    }
    
   

    public void setAccesses(List<Access> accesses) {
        this.accesses = accesses;
    }

    public void setCpeid(String cpeid) {
        this.cpeid = cpeid;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setFirmwares(List<Firmware> firmwares) {
        this.firmwares = firmwares;
    }

    public void setHostnames(List<String> hostnames) {
        this.hostnames = hostnames;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public void setManufacturers(List<Manufacturer> manufacturers) {
        this.manufacturers = manufacturers;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }

    public void setNodes(List<String> nodes) {
        this.nodes = nodes;
    }

    public void setProvisiongroups(List<String> provisiongroups) {
        this.provisiongroups = provisiongroups;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
