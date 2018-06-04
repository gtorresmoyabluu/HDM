package com.bluu.hdm.web.face;

import com.bluu.hdm.web.pojo.OAuthToken;
import com.bluu.hdm.web.rest.FactoryRest;
import com.bluu.hdm.web.util.AuthorizationUtil;
import com.bluu.hdm.web.util.MessageUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
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
    private Logger logger;
    private String userName;
    private String passwd;

    @PostConstruct
    public void init() {
        if (mapper == null) {
            mapper = new ObjectMapper();
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
            OAuthToken token = (OAuthToken) FactoryRest.getInstance().getToken(userName, passwd);
            if (token == null) {
                MessageUtils.addMessage("form:button", FacesMessage.SEVERITY_ERROR, "Ah ocurrido un error en la conexión, favor verificar.");
                FacesContext.getCurrentInstance().validationFailed();
                return null;
            } else if (token.getStatus() == 200) {
                try {
                    //Obtiene los mensajes desde la BBDD dependiendo del idioma (locale)
                    AuthorizationUtil.doRefreshAll(mapper);
                } catch (IllegalArgumentException ex) {

                }
                return "/" + FacesContext.getCurrentInstance().getExternalContext().getInitParameter("homePage") + "?faces-redirect=true";
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
