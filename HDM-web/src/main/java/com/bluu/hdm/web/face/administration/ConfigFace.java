/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.face.administration;

import com.bluu.hdm.web.enums.LicenseErrorEnum;
import com.bluu.hdm.web.face.LocaleFace;
import com.bluu.hdm.web.face.MenuFace;
import com.bluu.hdm.web.face.SessionFace;
import com.bluu.hdm.web.pojo.Category;
import com.bluu.hdm.web.pojo.Configuration;
import com.bluu.hdm.web.pojo.License;
import com.bluu.hdm.web.pojo.User;
import com.bluu.hdm.web.rest.ConsumeREST;
import com.bluu.hdm.web.rest.IConsumeREST;
import com.bluu.hdm.web.util.AuthorizationUtil;
import com.bluu.hdm.web.util.GetClassUtils;
import com.bluu.hdm.web.util.MessageUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.MultivaluedMap;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *
 * @author Gonzalo Torres
 */
@ViewScoped
@Named(ConfigFace.BEAN_NAME)
public class ConfigFace implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String BEAN_NAME = "config";
    private Logger logger = LogManager.getLogger(ConfigFace.class.getName());
    ;

    private ObjectMapper mapper;
    private IConsumeREST apiRest;
    private MultivaluedMap params;
    private User userSession;

    @Inject
    private SessionFace sessionFace;
    @Inject
    private LocaleFace locale;
    @Inject
    private MenuFace menu;

    private List<Category> categories;
    private HashMap<String, String> editedValues = new HashMap<>();
    private boolean running = false;

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

    public List<Category> getCategories() {
	if (categories == null) {
	    categories = new ArrayList<>();
	    List<Category> list = GetClassUtils.castToList(Category.class, apiRest.getListRestAPI("category/all", params));
	    for (Category category : list) {
		category.setConfigurationList(GetClassUtils.castToList(Configuration.class, apiRest.getListRestAPI(String.format("%s/all/%s", BEAN_NAME, category.getId()), params)));
		categories.add(category);
	    }
	}
	return categories;
    }

    public void setCategories(List<Category> categories) {
	this.categories = categories;
    }

    public boolean isRunning() {
	return running;
    }

    public void setRunning(boolean running) {
	this.running = running;
    }

    //Methods
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
	logger.debug("Init ConfigurationBean");
	doRefresh();
    }

    public void doRefresh() {
	categories = null;
	running = false;
	AuthorizationUtil.doRefreshAll(mapper, apiRest, params);
    }

    public String getActiveIndexString() {
	int totalNumberOfTabs = getCategories().size();
	StringBuilder out = new StringBuilder();
	String prefix = "";
	for (int i = 0; i < totalNumberOfTabs; i++) {
	    out.append(prefix);
	    prefix = ",";
	    out.append(i);
	}
	return out.toString();
    }

    public List<Configuration> getConfigurationsByCategory(String name) {
	List<Configuration> list = new ArrayList<>();
	for (final Category category : categories) {
	    if (category.getName().equals(name)) {
		list = category.getConfigurationList();
		break;
	    }
	}
	return list;
    }

    public boolean isValidate(String key) {
	return (editedValues.get(key) != null);
    }

    public boolean isEdit(String key) {
	return (editedValues.get(key) == null);
    }

    public License getLicense() {
	return sessionFace.getLicense();
    }

    public void doResetConfig(String confKey) {
	try {
	    if (confKey != null && editedValues.get(confKey) != null) {
		// Si se cambió el valor pero no se confirmó y se edita otro campo, se debe recuperar el valor original
		//configurationStBean.setConfiguration(confKey, editedValues.get(confKey),
		//AuthorizationUtil.getUserSession(FacesContext.getCurrentInstance()).getUser().getUsername());
	    }
	    editedValues.remove(confKey);
	} catch (Exception ex) {
	    logger.error("Error (resetConfig): " + ex.getMessage());
	    MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "mtext", "validation_action_update_error_detail");
	}
    }

    public String doProcess(String confKey, String confValue) {
	try {
	    if (isEdit(confKey)) {
		logger.info("Edited: (" + confKey + "," + confValue + ")");
		editedValues.put(confKey, confValue);
	    } else {

		logger.info("Updated: (" + confKey + "," + confValue + ")");

		// Envía el valor al singleton y a base de datos
		//configurationStBean.setConfiguration(confKey, confValue, AuthorizationUtil.getUserSession(FacesContext.getCurrentInstance()).getUser().getUserName());
		if (confKey.equals("language")) {
		    String lang = confValue.substring(0, confValue.indexOf("_"));
		    String coun = confValue.substring(confValue.indexOf("_") + 1, confValue.length());
		    locale.setCurrent(new Locale(lang, coun));
		    menu.reset();
		    return "/administration/configuration.xhtml?faces-redirect=true";
		} else if (confKey.equals("license")) {
		    // Se comprueba que la licencia es correcta. Si no se vuelve a escribir en bbdd la licencia anterior
		    License license = mapper.convertValue(apiRest.getRestAPI(String.format("license/check/%s", confValue), params), License.class);

		    if (license.isBlocked()) {
			//configurationStBean.setConfiguration(confKey, editedValues.get(confKey),AuthorizationUtil.getUserSession(FacesContext.getCurrentInstance()).getUser().getUsername());
			MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "configuration_update_license_error");

			String errors = "";
			List<LicenseErrorEnum> licerrors = license.getErrors();
			for (LicenseErrorEnum err : licerrors) {
			    errors += MessageUtils.getMessage("license_" + err) + ", ";
			}
			if (errors.endsWith(", ")) {
			    errors = errors.substring(0, errors.length() - 2);
			}

			logger.error(MessageUtils.getMessage("configuration_update_license_error") + ": " + errors);
		    } else {
			// Inicializa la nueva licencia
			apiRest.putRestAPI("license/upd", params, License.class, license);
		    }
		}

		editedValues.remove(confKey);

	    }
	} catch (IllegalArgumentException ex) {
	    logger.error("Error (setConfigurationValue): " + ex.getMessage());
	    MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "configuration_update_error");
	}
	return null;
    }

    public String getServerIP() {
	try {
	    return InetAddress.getLocalHost().getHostAddress();
	} catch (UnknownHostException e) {
	    return "unknown";
	}
    }

    public String getMessage(String key) {
	return MessageUtils.getMessage(key);
    }
}
