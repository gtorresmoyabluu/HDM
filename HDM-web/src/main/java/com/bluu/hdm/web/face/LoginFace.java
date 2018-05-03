package com.bluu.hdm.web.face;

import com.bluu.hdm.web.pojo.Message;
import com.bluu.hdm.web.pojo.OAuthToken;
import com.bluu.hdm.web.pojo.User;
import com.bluu.hdm.web.pojo.UserSession;
import com.bluu.hdm.web.rest.ConsumeREST;
import com.bluu.hdm.web.rest.IConsumeREST;
import com.bluu.hdm.web.util.AuthorizationUtil;
import com.bluu.hdm.web.util.MessageUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.ws.rs.core.MultivaluedMap;
import org.apache.log4j.Logger;

/**
 *
 * @author Gonzalo Torres
 */
@ViewScoped
@Named(LoginFace.BEAN_NAME)
public class LoginFace implements Serializable {

    public static final String BEAN_NAME = "login";
    private ObjectMapper mapper;
    private IConsumeREST apiRest;
    private MultivaluedMap params;

    private Logger logger;
    private String userName;
    private String passwd;

    public LoginFace() {
	if (mapper == null) {
	    mapper = new ObjectMapper();
	}
	if (apiRest == null) {
	    apiRest = new ConsumeREST();
	}
    }

    public String getPasswd() {
	return passwd;
    }

    public String getUserName() {
	return userName;
    }

    public void setPasswd(String passwd) {
	this.passwd = passwd;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public String doLogin() {
	try {
	    OAuthToken token = (OAuthToken) apiRest.getToken(userName, passwd);
	    if (token == null) {
		MessageUtils.addMessage("form:button", FacesMessage.SEVERITY_ERROR, "Usuario no existe");
		FacesContext.getCurrentInstance().validationFailed();
		return null;
	    } else if (token.getStatus() == 200) {
		params = new MultivaluedMapImpl();
		params.add("access_token", token.getAccess_token());

		Object obj = apiRest.getRestAPI(String.format("users/get/%s", userName), params);
		User user = mapper.convertValue(obj, User.class);
		if (user != null) {
		    user.setToken(token);
		    // Almacena el usuario en sesión
		    AuthorizationUtil.doLogin(FacesContext.getCurrentInstance(), new UserSession(user));
		    try {
			//Obtiene los mensajes desde la BBDD dependiendo del idioma (locale)
			String locate = FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage();
			AuthorizationUtil.doRefreshAll(mapper, apiRest, params);
		    } catch (IllegalArgumentException ex) {

		    }
		    return "/" + FacesContext.getCurrentInstance().getExternalContext().getInitParameter("homePage") + "?faces-redirect=true";
		} else {
		    MessageUtils.addMessage("form:button", FacesMessage.SEVERITY_ERROR, "Error en acceso de Usuario " + userName);
		    FacesContext.getCurrentInstance().validationFailed();
		    return null;
		}
	    } else {
		MessageUtils.addMessage("form:button", FacesMessage.SEVERITY_ERROR, token.getError_description());
		FacesContext.getCurrentInstance().validationFailed();
		return null;
	    }
	} catch (IllegalArgumentException t) {
	    MessageUtils.addMessage("form:button", FacesMessage.SEVERITY_ERROR, "Usuario no existe");
	    FacesContext.getCurrentInstance().validationFailed();
	    logger.error(null, t);
	    return null;
	}
    }
}
