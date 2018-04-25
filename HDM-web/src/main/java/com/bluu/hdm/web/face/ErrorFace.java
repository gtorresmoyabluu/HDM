package com.bluu.hdm.web.face;

import com.bluu.hdm.web.util.CryptoUtils;
import com.bluu.hdm.web.util.MessageUtils;
import com.bluu.hdm.web.util.error.RuntimeExceptionHandler;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

@ViewScoped
@Named(ErrorFace.BEAN_NAME)
public class ErrorFace implements Serializable {

    public static final String BEAN_NAME = "error";
    private static final long serialVersionUID = 1L;

//    @Inject
//    private transient Logger logger;
    private String message;

    @PostConstruct
    public void init() {
	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	if (session.getAttribute(RuntimeExceptionHandler.ERROR_MESSAGE) != null) {
	    String aux = (String) session.getAttribute(RuntimeExceptionHandler.ERROR_MESSAGE);
	    session.removeAttribute(RuntimeExceptionHandler.ERROR_MESSAGE);
	    if (StringUtils.isNotBlank(aux)) {
		message = CryptoUtils.decodeBase64(aux);
	    }
	}
    }

    public String getDescription() {
	String descrKey = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("default_error_desc");
	boolean showStackTrace = Boolean.valueOf(FacesContext.getCurrentInstance().getExternalContext().getInitParameter("showStackTrace"));
	return showStackTrace ? message : descrKey;
    }

    public String getTitle() {
	return (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("default_error_title");
    }

}
