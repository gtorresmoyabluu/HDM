package com.bluu.hdm.web.face;

import com.bluu.hdm.web.pojo.License;
import com.bluu.hdm.web.pojo.User;
import com.bluu.hdm.web.pojo.UserSession;
import com.bluu.hdm.web.util.AuthorizationUtil;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@SessionScoped
@Named(SessionFace.BEAN_NAME)
public class SessionFace implements Serializable {

    public final static String BEAN_NAME = "sessionFace";
    private static final long serialVersionUID = 1L;

    private boolean troubleshootInCertifMode;

    public String doLogout() {
	AuthorizationUtil.doLogout(FacesContext.getCurrentInstance());
	return "/" + FacesContext.getCurrentInstance().getExternalContext().getInitParameter("loginPage") + "?faces-redirect=true";
    }

    public String gotoHomePage() {
	return "/" + FacesContext.getCurrentInstance().getExternalContext().getInitParameter("homePage") + "?faces-redirect=true";
    }

    public String getAppVersion() {
	return "1.0.0";
    }

    public String getTrbLibVersion() {
	return "1.0.0";
    }

    public String getAppEnvironment() {
	return "1.0.0";
    }

    public String getAppTimestamp() {
	return "1.0.0";
    }

    public License getLicense() {
	return new License();
    }

    public Boolean getIsUserLogged() {
	return AuthorizationUtil.isUserLoged(FacesContext.getCurrentInstance());
    }

    public User getUserLogged() {
	return getUserSession().getUser();
    }

    public UserSession getUserSession() {
	return AuthorizationUtil.getUserSession(FacesContext.getCurrentInstance());
    }

    public boolean hasPermission(String permissionName) {
	return getUserSession().hasPermissionTo(permissionName);
    }

    public boolean isTroubleshootInCertifMode() {
	return troubleshootInCertifMode;
    }

    @SuppressWarnings("unused")
    public String setTroubleshootInCertifMode(String url, boolean troubleshootInCertifMode) {
	this.troubleshootInCertifMode = troubleshootInCertifMode;
	return url + "?faces-redirect=true";
    }
}
