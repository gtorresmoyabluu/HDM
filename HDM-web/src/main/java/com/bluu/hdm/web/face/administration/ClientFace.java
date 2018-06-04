/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.face.administration;

import com.bluu.hdm.web.enums.ExporterFormatEnum;
import com.bluu.hdm.web.enums.ViewTypeEnum;
import com.bluu.hdm.web.exporter.DataTableExporter;
import com.bluu.hdm.web.exporter.Exporter;
import com.bluu.hdm.web.exporter.ExporterFactory;
import com.bluu.hdm.web.model.APILazyDataModel;
import com.bluu.hdm.web.pojo.administracion.Client;
import com.bluu.hdm.web.rest.FactoryRest;
import com.bluu.hdm.web.util.AuthorizationUtil;
import com.bluu.hdm.web.util.MessageUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.log4j.Logger;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.Visibility;

/**
 *
 * @author Gonzalo Torres
 */
@ViewScoped
@Named(ClientFace.BEAN_NAME)
public class ClientFace implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String BEAN_NAME = "clients";
    private Logger logger;

    private ObjectMapper mapper;

    private String exportFormat;
    private boolean exportPageOnly;

    private ViewTypeEnum viewType;
    private List<Boolean> toggleableColumns;
    private Client currentItem;
    private LazyDataModel<Object> items;

    public ClientFace() {
    }

    // <editor-fold defaultstate="collapsed" desc="Getters / Setters">    
    public Client getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(Client currentItem) {
        this.currentItem = currentItem;
    }

    public LazyDataModel<Object> getItems() {
        if (items == null) {
            items = new APILazyDataModel(Client.class, String.format("%s/all", BEAN_NAME), BEAN_NAME);
        }
        return items;
    }

    public void setItems(LazyDataModel<Object> items) {
        this.items = items;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public String getExportFormat() {
        return exportFormat;
    }

    public void setExportFormat(String exportFormat) {
        this.exportFormat = exportFormat;
    }

    public boolean isExportPageOnly() {
        return exportPageOnly;
    }

    public void setExportPageOnly(boolean exportPageOnly) {
        this.exportPageOnly = exportPageOnly;
    }

    public ViewTypeEnum getViewType() {
        return viewType;
    }

    public void setViewType(ViewTypeEnum viewType) {
        this.viewType = viewType;
    }

    public List<Boolean> getToggleableColumns() {
        return toggleableColumns;
    }

    public void setToggleableColumns(List<Boolean> toggleableColumns) {
        this.toggleableColumns = toggleableColumns;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Methods">
    public void doAdd() {
        try {
            // Valida las entradas
            if (!validateAdd()) {
                return;
            } else if (FactoryRest.getInstance().getRestAPI(String.format("%s/getClientName/%s", BEAN_NAME, currentItem.getName().trim())) != null) {
                MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "client_client_exits");
                FacesContext.getCurrentInstance().validationFailed();
            } else {
                FactoryRest.getInstance().postRestAPI(String.format("%s/add", BEAN_NAME), currentItem);
                String userLogged = AuthorizationUtil.getUserSession(FacesContext.getCurrentInstance()).getUser().getUsername();
                MessageUtils.addMessage(FacesMessage.SEVERITY_INFO, "client_add_ok");
            }
            // Refresca vista
            doRefreshWOFilter();
        } catch (Exception e) {
            MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, UsersFace.BEAN_NAME, String.format("Error: %s", e.getMessage()));
        }
    }

    public void doChangeAdd() {
        if (viewType != ViewTypeEnum.add) {
            currentItem = new Client();
            viewType = ViewTypeEnum.add;
        } else {
            viewType = ViewTypeEnum.list;
        }
    }

    public void doChangeDetail(SelectEvent event) {
        currentItem = new Client();
        if (event.getObject() != null) {
            currentItem = mapper.convertValue(event.getObject(), Client.class);
        }
        if (viewType != ViewTypeEnum.detail) {
            viewType = ViewTypeEnum.detail;
        }
    }

    public void doChangeEdit() {
        // Realiza un clonado profundo del objeto
        currentItem = mapper.convertValue(currentItem, Client.class);
        if (viewType != ViewTypeEnum.edit) {
            viewType = ViewTypeEnum.edit;
        }
    }

    public void doDelete() {
        try {
            // Borra el usuario
            if (FactoryRest.getInstance().delRestAPI(String.format("%s/del/%s", BEAN_NAME, currentItem.getId()))) {
                MessageUtils.addMessage(FacesMessage.SEVERITY_INFO, "client_delete_ok");
            } else {
                MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "general_operationerror");
            }
            // Refresca vista
            doRefreshWOFilter();
        } catch (Exception e) {
            MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "general_operationerror");
        }
    }

    public void doEdit() {
        try {
            // Valida las entradas
            if (!validateEdit()) {
                return;
            } else {
                FactoryRest.getInstance().putRestAPI(String.format("%s/upd", BEAN_NAME), Client.class, currentItem);
                MessageUtils.addMessage(FacesMessage.SEVERITY_INFO, "client_update_ok");
            }
            // Refresca vista
            doRefreshWOFilter();
        } catch (Exception e) {
            MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "general_operationerror");
        }

    }

    public void doExport() {
        try {
            DataTable dt = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("form_dt:dataTable");
            Exporter exporter = ExporterFactory.getInstance(ExporterFormatEnum.valueOf(exportFormat));
            exporter.export(FacesContext.getCurrentInstance(), DataTableExporter.getDataExporter(dt, exportPageOnly), BEAN_NAME);
        } catch (Exception e) {
            MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "general_operationerror");
        }
    }

    public void doOnColumnToggle(ToggleEvent event) {
        toggleableColumns.set((Integer) event.getData(), event.getVisibility() == Visibility.VISIBLE);
        RequestContext.getCurrentInstance().execute("fixSearch();");
    }

    private boolean validateAdd() {
        boolean correcto = true;
        if (currentItem.getName().trim().length() == 0) {
            correcto = false;
            MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "client_client_required");
        }

        if (!correcto) {
            FacesContext.getCurrentInstance().validationFailed();
        }
        return correcto;
    }

    private boolean validateEdit() {
        boolean correcto = true;
        if (currentItem.getName().trim().length() == 0) {
            correcto = false;
            MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "client_client_required");
        }
        if (!correcto) {
            FacesContext.getCurrentInstance().validationFailed();
        }

        return correcto;
    }

    public void doRefresh() {
        doRefreshWOFilter();
        AuthorizationUtil.doRefreshAll(mapper);
    }

    public void doRefreshWOFilter() {
        items = null;
        currentItem = new Client();
        doSetViewTypeList();
    }

    public void doSetViewTypeList() {
        viewType = ViewTypeEnum.list;
    }

    public void doSecondStepFixSearch() {

    }
    // </editor-fold>

    @PostConstruct
    public void init() {
        if (mapper == null) {
            mapper = new ObjectMapper();
        }
        doRefresh();
        toggleableColumns = Arrays.asList(true, true, true);
    }
}
