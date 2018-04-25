package com.bluu.hdm.web.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Utility class for handling internationalized messages
 */
public abstract class MessageUtils {

    /**
     * Añade un mensaje en el faces context
     *
     * @param severity Grado de severidad del mensaje
     * @param messageKey Clave del mensaje dentro del paquete
     * @param arguments Parámetros de mensaje
     */
    public static void addMessage(FacesMessage.Severity severity, String messageKey, Object... arguments) {
	addMessage("messages", severity, messageKey, arguments);
    }

    /**
     * Añade un mensaje en el faces context
     *
     * @param clientId ID de mensaje en el cliente
     * @param severity Grado de severidad del mensaje
     * @param messageKey Clave del mensaje dentro del paquete
     * @param arguments Parámetros de mensaje
     */
    public static void addMessage(String clientId, FacesMessage.Severity severity, String messageKey, Object... arguments) {
	String msg = messageKey;
	if (msg != null && arguments != null && arguments.length != 0) {
	    msg = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(messageKey);
	    msg = MessageFormat.format(msg, arguments);
	}

	addMessage(clientId, severity, msg);
    }

    /**
     * Añade un mensaje en el faces context
     *
     * @param clientId ID de mensaje en el cliente
     * @param severity Grado de severidad del mensaje
     * @param messageText Texto del mensaje
     */
    public static void addMessage(String clientId, FacesMessage.Severity severity, String messageText) {
	FacesContext.getCurrentInstance().addMessage(
		clientId,
		new FacesMessage(
			severity,
			(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(messageText) == null ? messageText : (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(messageText),
			""
		)
	);
    }
}
