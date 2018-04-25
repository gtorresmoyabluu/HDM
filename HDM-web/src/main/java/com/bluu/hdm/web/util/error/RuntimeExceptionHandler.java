package com.bluu.hdm.web.util.error;

import com.bluu.hdm.web.util.CryptoUtils;
import java.io.IOException;

import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

public class RuntimeExceptionHandler extends ExceptionHandlerWrapper {

    public static final String ERROR_MESSAGE = "errorMessage";

    private Logger logger;
    private ExceptionHandler wrapped;

    @SuppressWarnings("unused")
    public RuntimeExceptionHandler() {
    }

    public RuntimeExceptionHandler(ExceptionHandler wrapped, Logger logger) {
	this.wrapped = wrapped;
	this.logger = logger;
    }

    @Override
    public ExceptionHandler getWrapped() {
	return wrapped;
    }

    @Override
    @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
    public void handle() throws FacesException {
	RuntimeException runtimeException = null;
	for (ExceptionQueuedEvent exceptionQueuedEvent : getUnhandledExceptionQueuedEvents()) {
	    Throwable t = exceptionQueuedEvent.getContext().getException();
	    if (t instanceof RuntimeException && !(t instanceof ViewExpiredException)) {
		runtimeException = (RuntimeException) t;
		break;
	    }
	}

	if (runtimeException != null) {

	    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	    HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
	    HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
	    String contextPath = ((HttpServletRequest) externalContext.getRequest()).getContextPath();
	    String dest = externalContext.getInitParameter("errorPage");

	    // Obtiene el mensaje de error
	    String message;
	    if (StringUtils.isBlank(runtimeException.getMessage())) {
		message = ExceptionUtils.getStackTrace(runtimeException);
	    } else {
		message = runtimeException.getMessage();
	    }

	    // Lo añade a la sesión
	    request.getSession().setAttribute(ERROR_MESSAGE, CryptoUtils.encodeBase64(message));

	    // Lo escribe en el log
	    if (logger != null) {
		logger.error(null, runtimeException);
	    }

	    // Redireccióna a la página de error
	    try {
		externalContext.redirect(response.encodeRedirectURL(contextPath + "/" + dest));
	    } catch (IOException ex) {
		if (logger != null) {
		    logger.error(ex.getMessage());
		}
	    }
	}

    }
}
