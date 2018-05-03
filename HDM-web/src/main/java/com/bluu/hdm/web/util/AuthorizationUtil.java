package com.bluu.hdm.web.util;

import com.bluu.hdm.web.pojo.Message;
import com.bluu.hdm.web.pojo.User;
import com.bluu.hdm.web.pojo.UserSession;
import com.bluu.hdm.web.rest.IConsumeREST;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MultivaluedMap;

public abstract class AuthorizationUtil {

    private static String USER_FACES_SESSION = "USER_FACES_SESSION";

    /**
     * Metodo para login
     *
     * @param session
     * @param user
     */
    public static void doLogin(HttpSession session, User user) {
	session.setAttribute(USER_FACES_SESSION, user);
    }

    /**
     * Método para logout
     *
     * @param request
     * @param session
     */
    public static void doLogout(HttpServletRequest request, HttpSession session) {
	if (!session.isNew()) {
	    try {
		session.removeAttribute(USER_FACES_SESSION);
		session.invalidate();
	    } catch (final Throwable ignore) {
	    }
	}
    }

    /**
     * Método para logout
     *
     * @param facesContext
     */
    public static void doLogout(FacesContext facesContext) {
	doLogout((HttpServletRequest) facesContext.getExternalContext().getRequest(), (HttpSession) facesContext.getExternalContext().getSession(false));

    }

    /**
     * Método para objeter el usuario en sesión
     *
     * @param request
     * @param session
     * @return
     */
    public static UserSession getUserSession(HttpServletRequest request, HttpSession session) {
	return (UserSession) session.getAttribute(USER_FACES_SESSION);
    }

    /**
     * Método para objeter el usuario en sesión
     *
     * @param facesContext
     * @return
     */
    public static UserSession getUserSession(FacesContext facesContext) {
	return getUserSession((HttpServletRequest) facesContext.getExternalContext().getRequest(), (HttpSession) facesContext.getExternalContext().getSession(false));
    }

    /**
     * Método para comprobar si se está logado o no
     *
     * @param request
     * @param session
     * @return
     */
    public static boolean isUserLoged(HttpServletRequest request, HttpSession session) {
	return session.getAttribute(USER_FACES_SESSION) != null;
    }

    /**
     * Método para comprobar si se está logado o no
     *
     * @param facesContext
     * @return
     */
    public static boolean isUserLoged(FacesContext facesContext) {
	return isUserLoged((HttpServletRequest) facesContext.getExternalContext().getRequest(), (HttpSession) facesContext.getExternalContext().getSession(false));
    }

    /**
     * Método para login
     *
     * @param facesContext
     * @param user
     */
    public static void doLogin(FacesContext facesContext, UserSession user) {
	((HttpSession) facesContext.getExternalContext().getSession(false)).setAttribute(USER_FACES_SESSION, user);
    }

    public static void doRefreshAll(ObjectMapper mapper, IConsumeREST apiRest, MultivaluedMap params) {
	String locate = FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage();
	List<Message> messages = mapper.convertValue(
		apiRest.getListRestAPI(String.format("messages/messages/%s", locate), params),
		new TypeReference<List<Message>>() {
	}
	);
	if (!messages.isEmpty()) {
	    messages.forEach((m) -> {
		if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey(m.getCode())) {
		    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(m.getCode(), null);
		}
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(m.getCode(), m.getDescription());
	    });
	}
    }
}
