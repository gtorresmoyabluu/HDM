/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.face.administration;

import com.bluu.hdm.web.enums.ViewTypeEnum;
import com.bluu.hdm.web.model.APILazyDataModel;
import com.bluu.hdm.web.pojo.Access;
import com.bluu.hdm.web.pojo.Role;
import com.bluu.hdm.web.pojo.User;
import com.bluu.hdm.web.rest.ConsumeREST;
import com.bluu.hdm.web.rest.IConsumeREST;
import com.bluu.hdm.web.util.AuthorizationUtil;
import com.bluu.hdm.web.util.GetClassUtils;
import com.bluu.hdm.web.util.MessageUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.ws.rs.core.MultivaluedMap;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.TreeNode;
import org.primefaces.model.Visibility;

/**
 *
 * @author Gonzalo Torres
 */
@ViewScoped
@Named(AccessFace.BEAN_NAME)
public class AccessFace implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String BEAN_NAME = "access";
    private Logger logger;

    private ObjectMapper mapper;
    private IConsumeREST apiRest;
    private MultivaluedMap params;
    private User userSession;

    private String exportFormat;
    private boolean exportPageOnly;

    private ViewTypeEnum viewType;
    private List<Boolean> toggleableColumns;

    private TreeNode root;
    private TreeNode selectedNode;
    private List<Access> parents;
    private LazyDataModel<Object> items;
    private Access currentItem;

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
	if (parents == null) {
	    parents = GetClassUtils.castToList(Access.class, apiRest.getListRestAPI(String.format("%s/all/parent", BEAN_NAME), params));
	}
	doRefresh();
	toggleableColumns = Arrays.asList(true, true, true);
    }

    // <!-- Getters and Setters -->
    public TreeNode getRoot() {
	return root;
    }

    public void setRoot(TreeNode root) {
	this.root = root;
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

    public IConsumeREST getApiRest() {
	return apiRest;
    }

    public void setApiRest(IConsumeREST apiRest) {
	this.apiRest = apiRest;
    }

    public MultivaluedMap getParams() {
	return params;
    }

    public void setParams(MultivaluedMap params) {
	this.params = params;
    }

    public User getUserSession() {
	return userSession;
    }

    public void setUserSession(User userSession) {
	this.userSession = userSession;
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

    public LazyDataModel<Object> getItems() {
	if (items == null) {
	    items = new APILazyDataModel(Access.class, String.format("%s/all/parent", BEAN_NAME), params, BEAN_NAME);
	}
	return items;
    }

    public void setItems(LazyDataModel<Object> items) {
	this.items = items;
    }

    public Access getCurrentItem() {
	if (currentItem == null) {
	    currentItem = new Access();
	}
	return currentItem;
    }

    public void setCurrentItem(Access currentItem) {
	this.currentItem = currentItem;
    }

    // Methods
    public void doRefresh() {
	doRefreshWOFilter();
    }

    public void doRefreshWOFilter() {
	//items = null;
	root = getItemsData();
	currentItem = new Access();
	doSetViewTypeList();
    }

    public void doSetViewTypeList() {
	viewType = ViewTypeEnum.list;
    }

    public void doSecondStepFixSearch() {

    }

    public void doChangeAdd() {
	if (viewType != ViewTypeEnum.add) {
	    currentItem = new Access();
	    viewType = ViewTypeEnum.add;
	} else {
	    viewType = ViewTypeEnum.list;
	}
    }

    public void doOnColumnToggle(ToggleEvent event) {
	toggleableColumns.set((Integer) event.getData(), event.getVisibility() == Visibility.VISIBLE);
	RequestContext.getCurrentInstance().execute("fixSearch();");
    }

    public void doAdd() {
	try {
	    // Valida las entradas
	    if (!validateAdd()) {
		return;
	    } else if (apiRest.getRestAPI(String.format("%s/getAccessName/%s", BEAN_NAME, currentItem.getCode().trim()), params) != null) {
		MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "access_nameexits");
		FacesContext.getCurrentInstance().validationFailed();
	    } else {
		if (currentItem.getIdParent() != null) {
		    currentItem.setParent(currentItem.getIdParent().getId());
		} else {
		    currentItem.setParent(null);
		}
		apiRest.postRestAPI(String.format("%s/add", BEAN_NAME), params, currentItem);
		MessageUtils.addMessage(FacesMessage.SEVERITY_INFO, "access_creation_success");
	    }
	} catch (Exception e) {
	    MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "general_operationerror");
	}
	// Refresca vista
	doRefreshWOFilter();
    }

    private boolean validateAdd() {
	boolean correcto = true;
	if (currentItem.getCode().trim().length() == 0) {
	    correcto = false;
	    MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "access_code_empty");
	}
	if (currentItem.getDescription().trim().length() == 0) {
	    correcto = false;
	    MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "access_description_empty");
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
		if (currentItem.getIdParent() != null) {
		    currentItem.setParent(currentItem.getIdParent().getId());
		} else {
		    currentItem.setParent(null);
		}
		apiRest.putRestAPI(String.format("%s/upd", BEAN_NAME), params, Access.class, currentItem);
		MessageUtils.addMessage(FacesMessage.SEVERITY_INFO, "access_edit_success");
	    }
	    // Refresca vista
	    doRefreshWOFilter();
	} catch (Exception e) {
	    MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "general_operationerror");
	}

    }

    private boolean validateEdit() {
	boolean correcto = true;
	if (currentItem.getCode().trim().length() == 0) {
	    correcto = false;
	    MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "access_code_empty");
	}
	if (currentItem.getDescription().trim().length() == 0) {
	    correcto = false;
	    MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "access_description_empty");
	}
	if (!correcto) {
	    FacesContext.getCurrentInstance().validationFailed();
	}
	return correcto;
    }

    public void doDelete() {
	try {
	    if (!validateForDelete()) {
		return;
	    } else {
		// Borra el usuario
		apiRest.delRestAPI(String.format("%s/del/%s", BEAN_NAME, currentItem.getId()), params);
		MessageUtils.addMessage(FacesMessage.SEVERITY_INFO, "access_delete_success", currentItem.getDescription());

		// Refresca vista
		doRefreshWOFilter();
	    }
	} catch (Exception e) {
	    MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "general_operationerror");
	}
    }

    //Validations
    private boolean validateForDelete() {
	boolean validate = true;
	List<Role> accessByRol = apiRest.getListRestAPI(String.format("%s/users/%s", BEAN_NAME, currentItem.getId()), params);
	//comprueba si el rol esta asignado a algún usuario
	if (accessByRol == null) {
	    return validate;
	} else if (accessByRol.size() > 0) {
	    validate = false;
	    MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "roles_delete_fail_roleused", currentItem.getDescription());
	}
	return validate;
    }

    public List<SelectItem> getAvailableParents() {
	List<SelectItem> result = new ArrayList<>();
	if (!parents.isEmpty()) {
	    parents.forEach((parent) -> {
		result.add(new SelectItem(parent, parent.getDescription()));
	    });
	}
	return result;
    }

    public void doChangeDetail(SelectEvent event) {
	currentItem = new Access();
	if (event.getObject() != null) {
	    currentItem = new Access();
	    currentItem = mapper.convertValue(event.getObject(), Access.class);
	}
	if (viewType != ViewTypeEnum.detail) {
	    viewType = ViewTypeEnum.detail;
	}
    }

    public void doChangeEdit() {
	// Realiza un clonado profundo del objeto
	currentItem = mapper.convertValue(currentItem, Access.class);
	if (viewType != ViewTypeEnum.edit) {
	    viewType = ViewTypeEnum.edit;
	}
    }

    public TreeNode getSelectedNode() {
	return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
	if (selectedNode != null) {
	    currentItem = mapper.convertValue(selectedNode.getData(), Access.class);
	}
	this.selectedNode = selectedNode;
    }

    private TreeNode getItemsData() {
	TreeNode _root = new DefaultTreeNode(new Access(), null);
	List<Access> parents = GetClassUtils.castToList(Access.class, apiRest.getListRestAPI(String.format("%s/all/parent", BEAN_NAME), params));
	if (!parents.isEmpty()) {
	    parents.forEach((parent) -> {
		TreeNode _main = new DefaultTreeNode(new Access(parent.getId(), parent.getDescription(), parent.getCode(), parent.getIcon(), "-", parent.getParent(), new Access()), _root);
		List<Access> childs = GetClassUtils.castToList(Access.class, apiRest.getListRestAPI(String.format("%s/all/parent/%s", BEAN_NAME, parent.getId()), params));
		if (!childs.isEmpty()) {
		    childs.forEach((child) -> {
			Access a = mapper.convertValue(apiRest.getRestAPI(String.format("%s/find/%s", BEAN_NAME, child.getParent()), params), Access.class);
			TreeNode _submain = new DefaultTreeNode(parent.getCode(), new Access(child.getId(), child.getDescription(), child.getCode(), child.getIcon(), a.getDescription(), child.getParent(), parent), _main);
		    });
		}
	    });
	}
	return _root;
    }
}
