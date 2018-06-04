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
import com.bluu.hdm.web.pojo.administracion.Category;
import com.bluu.hdm.web.pojo.administracion.Configuration;
import com.bluu.hdm.web.rest.FactoryRest;
import com.bluu.hdm.web.util.GetClassUtils;
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
@Named(CategoryFace.BEAN_NAME)
public class CategoryFace implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String BEAN_NAME = "category";
    private Logger logger;

    private ObjectMapper mapper;

    private String exportFormat;
    private boolean exportPageOnly;
    private Boolean showtable;

    private ViewTypeEnum viewType;
    private List<Boolean> toggleableColumns;

    private LazyDataModel<Object> items;
    private Category currentItem;

    private Configuration currentConfig;

    @PostConstruct
    public void init() {
        if (mapper == null) {
            mapper = new ObjectMapper();
        }
        doRefresh();
        toggleableColumns = Arrays.asList(true, true, false);
    }

    public void doRefresh() {
        doRefreshWOFilter();
    }

    public void doRefreshWOFilter() {
        items = null;
        currentItem = new Category();
        showtable = false;
        doSetViewTypeList();
    }

    public void doSetViewTypeList() {
        viewType = ViewTypeEnum.list;
        showtable = !showtable;
    }

    public void doSecondStepFixSearch() {

    }

    public ViewTypeEnum getViewType() {
        return viewType;
    }

    public void setViewType(ViewTypeEnum viewType) {
        this.viewType = viewType;
    }

    public void doChangeAdd() {
        if (viewType != ViewTypeEnum.add) {
            currentItem = new Category();
            viewType = ViewTypeEnum.add;
        } else {
            viewType = ViewTypeEnum.list;
        }
    }

    public void doChangeDetail(SelectEvent event) {
        currentItem = new Category();
        if (event.getObject() != null) {
            currentItem = mapper.convertValue(event.getObject(), Category.class);
        }
        if (viewType != ViewTypeEnum.detail) {
            viewType = ViewTypeEnum.detail;
        }
    }

    public void doChangeEdit() {
        // Realiza un clonado profundo del objeto
        currentItem = mapper.convertValue(currentItem, Category.class);
        if (viewType != ViewTypeEnum.edit) {
            viewType = ViewTypeEnum.edit;
        }
    }

    public void doChangeAccess() {
        // Realiza un clonado profundo del objeto
        currentItem = mapper.convertValue(currentItem, Category.class);
        if (viewType != ViewTypeEnum.config) {
            viewType = ViewTypeEnum.config;
            showtable = !showtable;
        }
    }

    public void doCheckboxClick(Long idCategory) {
        // FactoryRest.getInstance().postRestAPI(String.format("%s/accessRol", BEAN_NAME, 1));
        items = null;
    }

    public void doDelete() {
        try {
            if (!validateForDelete()) {
                return;
            } else {
                // Borra el usuario
                if (FactoryRest.getInstance().delRestAPI(String.format("%s/del/%s", BEAN_NAME, currentItem.getId()))) {
                    MessageUtils.addMessage(FacesMessage.SEVERITY_INFO, "roles_delete_success", currentItem.getName());
                } else {
                    MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "general_operationerror");
                }
                // Refresca vista
                doRefreshWOFilter();
            }
        } catch (Exception e) {
            MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "general_operationerror");
        }
    }

    private boolean validateForDelete() {
        boolean validate = true;
        List<Category> data = (List<Category>) (Object) GetClassUtils.castToList(Category.class, FactoryRest.getInstance().getListRestAPI(String.format("%s/users/%s", BEAN_NAME, currentItem.getId())));
        //comprueba si el rol esta asignado a algún usuario
        if (data == null) {
            return validate;
        } else if (data.size() > 0) {
            validate = false;
            MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "roles_delete_fail_roleused", currentItem.getName());
        }
        return validate;
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

    public List<Boolean> getToggleableColumns() {
        return toggleableColumns;
    }

    public void setToggleableColumns(List<Boolean> toggleableColumns) {
        this.toggleableColumns = toggleableColumns;
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

    public void doOnColumnToggle(ToggleEvent event) {
        toggleableColumns.set((Integer) event.getData(), event.getVisibility() == Visibility.VISIBLE);
        RequestContext.getCurrentInstance().execute("fixSearch();");
    }

    public Boolean getShowtable() {
        return showtable;
    }

    public void setShowtable(Boolean showtable) {
        this.showtable = showtable;
    }

    public LazyDataModel<Object> getItems() {
        if (items == null) {
            items = new APILazyDataModel(Category.class, String.format("%s/all", BEAN_NAME), BEAN_NAME);
        }
        return items;
    }

    public void setItems(LazyDataModel<Object> items) {
        this.items = items;
    }

    public Category getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(Category rolItem) {
        currentItem = mapper.convertValue(rolItem, Category.class);
    }
}
