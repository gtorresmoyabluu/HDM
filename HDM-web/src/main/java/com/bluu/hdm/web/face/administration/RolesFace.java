package com.bluu.hdm.web.face.administration;

import com.bluu.hdm.web.enums.ExporterFormatEnum;
import com.bluu.hdm.web.enums.ViewTypeEnum;
import com.bluu.hdm.web.exporter.DataTableExporter;
import com.bluu.hdm.web.exporter.Exporter;
import com.bluu.hdm.web.exporter.ExporterFactory;
import com.bluu.hdm.web.model.APILazyDataModel;
import com.bluu.hdm.web.pojo.Role;
import com.bluu.hdm.web.pojo.User;
import com.bluu.hdm.web.rest.ConsumeREST;
import com.bluu.hdm.web.rest.IConsumeREST;
import com.bluu.hdm.web.util.AuthorizationUtil;
import com.bluu.hdm.web.util.MessageUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.ws.rs.core.MultivaluedMap;
import org.apache.log4j.Logger;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.Visibility;

@ViewScoped
@Named(RolesFace.BEAN_NAME)
public class RolesFace implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String BEAN_NAME = "roles";
    private Logger logger;

    private ObjectMapper mapper;
    private IConsumeREST apiRest;
    private MultivaluedMap params;
    private User userSession;

    private String exportFormat;
    private boolean exportPageOnly;
    private Boolean showtable;

    private ViewTypeEnum viewType;
    private List<Boolean> toggleableColumns;
    private LazyDataModel<Object> items;
    private Role currentItem;

    @PostConstruct
    public void init() {
	if (mapper == null) {
	    mapper = new ObjectMapper();
	}
	if (apiRest == null) {
	    apiRest = new ConsumeREST();
	}
	if (userSession == null && AuthorizationUtil.getUserSession(FacesContext.getCurrentInstance()).getUser() != null) {
	    userSession = AuthorizationUtil.getUserSession(FacesContext.getCurrentInstance()).getUser();
	}
	if (params == null) {
	    params = new MultivaluedMapImpl();
	}
	if (!params.containsKey("access_token")) {
	    params.add("access_token", userSession.getToken().getAccess_token());
	}

	doRefresh();
	toggleableColumns = Arrays.asList(true, true, false);
    }

    public void doRefresh() {
	doRefreshWOFilter();
    }

    public void doRefreshWOFilter() {
	items = null;
	currentItem = new Role();
	showtable = true;
	doSetViewTypeList();
    }

    public void doSetViewTypeList() {
	viewType = ViewTypeEnum.list;
	showtable = true;
    }

    public void doChangeAdd() {
	if (viewType != ViewTypeEnum.add) {
	    currentItem = new Role();
	    viewType = ViewTypeEnum.add;
	} else {
	    viewType = ViewTypeEnum.list;
	}
    }

    public void doChangeDetail(SelectEvent event) {
	currentItem = new Role();
	if (event.getObject() != null) {
	    currentItem = mapper.convertValue(event.getObject(), Role.class);
	}
	if (viewType != ViewTypeEnum.detail) {
	    viewType = ViewTypeEnum.detail;
	}
    }

    public void doChangeEdit() {
	// Realiza un clonado profundo del objeto
	currentItem = mapper.convertValue(currentItem, Role.class);
	if (viewType != ViewTypeEnum.edit) {
	    viewType = ViewTypeEnum.edit;
	}
    }

    public void doChangeAccess() {
	// Realiza un clonado profundo del objeto
	currentItem = mapper.convertValue(currentItem, Role.class);
	if (viewType != ViewTypeEnum.access) {
	    viewType = ViewTypeEnum.access;
	    showtable = false;
	}
    }

    //Events
    public void doAdd() {
	try {
	    // Valida las entradas
	    if (!validateAdd()) {
		return;
	    } else if (apiRest.getRestAPI(String.format("%s/getRoleName/%s", BEAN_NAME, currentItem.getName().trim()), params) != null) {
		MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "roles_nameexits");
		FacesContext.getCurrentInstance().validationFailed();
	    } else {
		apiRest.postRestAPI(String.format("%s/add", BEAN_NAME), params, currentItem);
		MessageUtils.addMessage(FacesMessage.SEVERITY_INFO, "roles_creation_success");
	    }
	} catch (Exception e) {
	    MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "general_operationerror");
	}
	// Refresca vista
	doRefreshWOFilter();
    }

    private boolean validateAdd() {
	boolean correcto = true;

	if (currentItem.getName().trim().length() == 0) {
	    correcto = false;
	    MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "roles_name_empty");
	}
	if (!correcto) {
	    FacesContext.getCurrentInstance().validationFailed();
	}
	return correcto;
    }

    public void doEdit() {
	try {
	    // Valida las entradas
	    if (!validateEdit()) {
		return;
	    } else {
		apiRest.putRestAPI(String.format("%s/upd/%s", BEAN_NAME, currentItem.getId()), params, Role.class, currentItem);
		MessageUtils.addMessage(FacesMessage.SEVERITY_INFO, "roles_edit_success");
	    }
	    // Refresca vista
	    doRefreshWOFilter();
	} catch (Exception e) {
	    MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "general_operationerror");
	}

    }

    private boolean validateEdit() {
	boolean correcto = true;
	if (currentItem.getName().trim().length() == 0) {
	    correcto = false;
	    MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "users_firstname_empty");
	}
	if (!correcto) {
	    FacesContext.getCurrentInstance().validationFailed();
	}
	return correcto;
    }

    public void doDelete() {
	try {
	    if (!validateRolesForDelete()) {
		return;
	    } else {
		// Borra el usuario
		apiRest.delRestAPI(String.format("%s/del/%s", BEAN_NAME, currentItem.getId()), params);
		MessageUtils.addMessage(FacesMessage.SEVERITY_INFO, "roles_delete_success", currentItem.getName());

		// Refresca vista
		doRefreshWOFilter();
	    }
	} catch (Exception e) {
	    MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "general_operationerror");
	}
    }

    //Validations
    private boolean validateRolesForDelete() {
	boolean validate = true;
	List<User> userByRol = apiRest.getListRestAPI(String.format("%s/users/%s", BEAN_NAME, currentItem.getId()), params);
	//comprueba si el rol esta asignado a algún usuario
	if (userByRol == null) {
	    return validate;
	} else if (userByRol.size() > 0) {
	    validate = false;
	    MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "roles_delete_fail_roleused", currentItem.getName());
	}
	return validate;
    }

    public void doSecondStepFixSearch() {

    }

    public void doExport() {
	try {
	    DataTable dt = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("form_dt:dataTable");
	    Exporter exporter = ExporterFactory.getInstance(ExporterFormatEnum.valueOf(exportFormat));
	    exporter.export(FacesContext.getCurrentInstance(), DataTableExporter.getDataExporter(dt, exportPageOnly), "roles");
	} catch (Exception e) {
	    MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "general_operationerror");
	}
    }

    public void doOnColumnToggle(ToggleEvent event) {
	toggleableColumns.set((Integer) event.getData(), event.getVisibility() == Visibility.VISIBLE);
	RequestContext.getCurrentInstance().execute("fixSearch();");
    }

    //Getters & Setters
    public ViewTypeEnum getViewType() {
	return viewType;
    }

    public void setViewType(ViewTypeEnum viewType) {
	this.viewType = viewType;
    }

    public LazyDataModel<Object> getItems() {
	if (items == null) {
	    items = new APILazyDataModel(Role.class, String.format("%s/all", BEAN_NAME), params, BEAN_NAME);
	}
	return items;
    }

    public void setItems(LazyDataModel<Object> items) {
	this.items = items;
    }

    public Role getCurrentItem() {
	return currentItem;
    }

    public void setCurrentItem(Role rolItem) {
	currentItem = mapper.convertValue(rolItem, Role.class);
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

    public Boolean getShowtable() {
	return showtable;
    }

    public void setShowtable(Boolean showtable) {
	this.showtable = showtable;
    }

}
