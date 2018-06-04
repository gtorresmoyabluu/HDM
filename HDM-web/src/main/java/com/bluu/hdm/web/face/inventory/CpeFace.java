package com.bluu.hdm.web.face.inventory;

import com.bluu.hdm.web.enums.ExporterFormatEnum;
import com.bluu.hdm.web.enums.ViewTypeEnum;
import com.bluu.hdm.web.exporter.DataTableExporter;
import com.bluu.hdm.web.exporter.Exporter;
import com.bluu.hdm.web.exporter.ExporterFactory;
import com.bluu.hdm.web.filter.DeviceFilter;
import com.bluu.hdm.web.model.APILazyDataModel;
import com.bluu.hdm.web.pojo.inventory.Cpe;
import com.bluu.hdm.web.pojo.inventory.cpe.MapEntry;
import com.bluu.hdm.web.util.MessageUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.Visibility;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;

@ViewScoped
@Named(CpeFace.BEAN_NAME)
public class CpeFace implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String BEAN_NAME = "cpe";
    private ObjectMapper mapper;
    private transient Logger logger = LogManager.getLogger(CpeFace.class.getName());
    private DeviceFilter currentFilter;
    private Cpe currentItem;
    private Cwmp inventoryBean;
    private ViewTypeEnum viewType;

    private boolean exportPageOnly;
    private String exportFormat;

    private LazyDataModel<Object> items;

    private List<Boolean> toggleableColumns;
    private HashMap<String, String> hmCpeTypes;

    // CWMP
    private ArrayList<MapEntry> parameterNames, parameterValues;
    private ArrayList<String> rpcMethods;

    private String cwmpGetParValue, cwmpGetParName, cwmpSetParName, cwmpSetParValue, cwmpObjectName, cwmpParameterKey;
    private boolean cwmpGetParNext = false;

    @PostConstruct
    public void initData() {
        if (mapper == null) {
            mapper = new ObjectMapper();
        }
        if(inventoryBean == null){
             inventoryBean = new Cwmp();
        }
        doRefresh();
        toggleableColumns = Arrays.asList(true, false, true, true, false, true, true, true, true);
    }

    public LazyDataModel<Object> getItems() {
        if (items == null) {
            items = new APILazyDataModel(Cpe.class, "deviceviewer/all", BEAN_NAME);
        }
        return items;
    }

    public void doSetViewTypeList() {
        viewType = ViewTypeEnum.list;
    }

    public void doRefreshWOFilter() {
        items = null;
        currentItem = null;
        doSetViewTypeList();
    }

    public void doRefresh() {
        doRefreshWOFilter();
        currentFilter = null;
    }

    public void doSearch() {
        items = null;

        if (currentFilter == null) {
            //currentFilter = new DeviceFilter();
        }
    }

    public void doExport() {
        try {
            DataTable dt = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("form_dt:dataTable");
            Exporter exporter = ExporterFactory.getInstance(ExporterFormatEnum.valueOf(exportFormat));
            exporter.export(FacesContext.getCurrentInstance(), DataTableExporter.getDataExporter(dt, exportPageOnly), BEAN_NAME);
        } catch (Exception e) {
            logger.error("method " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " : " + e.getMessage());
            MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "mtext", "general.operationerror");
        }
    }

    public void doChangeDetail(SelectEvent event) {
        currentItem = (Cpe) event.getObject();
        if (viewType != ViewTypeEnum.detail) {
            viewType = ViewTypeEnum.detail;
        }
    }

    public void doChangeSearch() {
        if (viewType != ViewTypeEnum.search) {
            if (currentFilter == null) {
                currentFilter = new DeviceFilter();
            }
            viewType = ViewTypeEnum.search;
        } else {
            viewType = ViewTypeEnum.list;
        }
    }

    // CWMP
    public void doChangeEdit() {
        if (viewType != ViewTypeEnum.edit) {
            if (currentFilter == null) {
                currentFilter = new DeviceFilter();
            }
            viewType = ViewTypeEnum.edit;
        }
    }

    public void doGetParameterNames() {
        try {
            this.parameterNames = inventoryBean.getParameterNames(currentItem.getIdDevice(), cwmpGetParName, cwmpGetParNext);
            MessageUtils.addMessage(null, FacesMessage.SEVERITY_INFO, "Petición realizada");
        } catch (Exception ex) {
            MessageUtils.addMessage(null, FacesMessage.SEVERITY_ERROR, ex.getMessage());
        }
    }

    public void doGetParameterValues() {
        try {
            this.parameterValues = inventoryBean.getParameterValues(currentItem.getIdDevice(), cwmpGetParValue);
            MessageUtils.addMessage(null, FacesMessage.SEVERITY_INFO, "Petición realizada");
        } catch (Exception ex) {
            MessageUtils.addMessage(null, FacesMessage.SEVERITY_ERROR, ex.getMessage());
        }
    }

    public void doGetRpcMethods() {
        try {
            this.rpcMethods = inventoryBean.getRpcMethods(currentItem.getIdDevice());
            MessageUtils.addMessage(null, FacesMessage.SEVERITY_INFO, "Petición realizada");
        } catch (Exception ex) {
            MessageUtils.addMessage(null, FacesMessage.SEVERITY_ERROR, ex.getMessage());
        }
    }

    public void doReboot() {
        try {
            //inventoryBean.reboot(currentItem.getIdDevice());
            MessageUtils.addMessage(null, FacesMessage.SEVERITY_INFO, "Petición realizada");
        } catch (Exception ex) {
            MessageUtils.addMessage(null, FacesMessage.SEVERITY_ERROR, ex.getMessage());
        }
    }

    public void doSetParameterValues() {
        try {
            //inventoryBean.setParameterValues(currentItem.getIdDevice(), cwmpSetParName, cwmpSetParValue);
        } catch (Exception ex) {
            MessageUtils.addMessage(null, FacesMessage.SEVERITY_ERROR, ex.getMessage());
        }
    }

    public void doAddObject() {
        try {
            inventoryBean.doAddObject(currentItem.getIdDevice(), cwmpObjectName, cwmpParameterKey);
        } catch (Exception ex) {
            MessageUtils.addMessage(null, FacesMessage.SEVERITY_ERROR, ex.getMessage());
        }
    }

    public void doDeleteObject() {
        try {
            inventoryBean.doDeleteObject(currentItem.getIdDevice(), cwmpObjectName, cwmpParameterKey);
        } catch (Exception ex) {
            MessageUtils.addMessage(null, FacesMessage.SEVERITY_ERROR, ex.getMessage());
        }
    }

    public ArrayList<MapEntry> getParameterNames() {
        return parameterNames;
    }

    public ArrayList<MapEntry> getParameterValues() {
        return parameterValues;
    }

    public ArrayList<String> getRpcMethods() {
        return rpcMethods;
    }

    // CWMP End
    public void onColumnToggle(ToggleEvent event) {
        toggleableColumns.set((Integer) event.getData(), event.getVisibility() == Visibility.VISIBLE);
    }

    public boolean isCat1() {
        return getHmCpeTypes().containsKey("1");
    }

    public boolean isCat2() {
        return getHmCpeTypes().containsKey("2");
    }

    public boolean isCat3() {
        return getHmCpeTypes().containsKey("3");
    }

    public boolean isCat4() {
        return getHmCpeTypes().containsKey("4");
    }

    public boolean isCat5() {
        return getHmCpeTypes().containsKey("5");
    }

    public String getCatValue1() {
        return getHmCpeTypes().get("1");
    }

    public String getCatValue2() {
        return getHmCpeTypes().get("2");
    }

    public String getCatValue3() {
        return getHmCpeTypes().get("3");
    }

    public String getCatValue4() {
        return getHmCpeTypes().get("4");
    }

    public String getCatValue5() {
        return getHmCpeTypes().get("5");
    }

    public DeviceFilter getCurrentFilter() {
        return currentFilter;
    }

    public Cpe getCurrentItem() {
        return currentItem;
    }

    public ViewTypeEnum getViewType() {
        return viewType;
    }

    public boolean getIsItemsFiltered() {
        return currentFilter != null && currentFilter.isFilled();
    }

    public List<Boolean> getToggleableColumns() {
        return toggleableColumns;
    }

    public HashMap<String, String> getHmCpeTypes() {
        return hmCpeTypes;
    }

    public void setCurrentItem(Cpe currentItem) {
        this.currentItem = currentItem;
    }

    public void setViewType(ViewTypeEnum viewType) {
        this.viewType = viewType;
    }

    /* public void setAdminBean(InventoryBean inventoryBean) {
        this.inventoryBean = inventoryBean;
    }*/
    public void setCurrentFilter(DeviceFilter currentFilter) {
        this.currentFilter = currentFilter;
    }

    public void setExportPageOnly(boolean exportPageOnly) {
        this.exportPageOnly = exportPageOnly;
    }

    public void setExportFormat(String exportFormat) {
        this.exportFormat = exportFormat;
    }

    public String getCwmpGetParValue() {
        return cwmpGetParValue;
    }

    public void setCwmpGetParValue(String cwmpGetParValue) {
        this.cwmpGetParValue = cwmpGetParValue == null ? "" : cwmpGetParValue;
    }

    public String getCwmpGetParName() {
        return cwmpGetParName;
    }

    public void setCwmpGetParName(String cwmpGetParName) {
        this.cwmpGetParName = cwmpGetParName == null ? "" : cwmpGetParName;
    }

    public String getCwmpSetParName() {
        return cwmpSetParName;
    }

    public void setCwmpSetParName(String cwmpSetParName) {
        this.cwmpSetParName = cwmpSetParName == null ? "" : cwmpSetParName;
    }

    public String getCwmpObjectName() {
        return cwmpObjectName;
    }

    public void setCwmpObjectName(String cwmpObjectName) {
        this.cwmpObjectName = cwmpObjectName == null ? "" : cwmpObjectName;
    }

    public String getCwmpParameterKey() {
        return cwmpParameterKey;
    }

    public void setCwmpParameterKey(String cwmpParameterKey) {
        this.cwmpParameterKey = cwmpParameterKey == null ? "" : cwmpParameterKey;
    }

    public String getCwmpSetParValue() {
        return cwmpSetParValue;
    }

    public void setCwmpSetParValue(String cwmpSetParValue) {
        this.cwmpSetParValue = cwmpSetParValue == null ? "" : cwmpSetParValue;
    }

    public boolean isCwmpGetParNext() {
        return cwmpGetParNext;
    }

    public void setCwmpGetParNext(boolean cwmpGetParNext) {
        this.cwmpGetParNext = cwmpGetParNext;
    }

}
