package com.bluu.hdm.web.filter;

import com.bluu.hdm.web.face.MenuFace;
import com.bluu.hdm.web.pojo.administracion.Access;
import com.bluu.hdm.web.util.AuthorizationUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Filtro para controlar el acceso a la aplicación por parte de usuarios
 * autorizados
 */
public class AuthorizationFilter implements Filter {

    private String homePage;
    private String loginPage;
    private String errorPage;
    private List<String> allowedPages;

    //@Inject
    private Logger logger;
    @Inject
    private MenuFace menu;

    private void cacheControl(HttpServletResponse httpResponse) {
	httpResponse.setHeader("pragma", "no-cache");
	httpResponse.setHeader("Cache-Control", "no-cache");
	httpResponse.setHeader("Cache-Control", "no-store");
	httpResponse.setDateHeader("Expires", 0L);
    }

    /**
     * Comprueba si la página solicitada está contenida en las páginas del grupo
     * de menús
     */
    private boolean checkGroup(HttpServletRequest request, Access groupItem, String requestedUrl) {
	boolean allowed = false;

	final List<Access> submenu = groupItem.getChild();
	if (submenu != null) {
	    for (final Access permissionXml : submenu) {
		if ((checkGroup(request, permissionXml, requestedUrl)) || checkPage(request, permissionXml, requestedUrl)) {
		    allowed = true;
		    break;
		}
	    }
	}

	return allowed;
    }

    /**
     * Compruebe si la página solicitada es la correspondiente a la opción de
     * menú
     */
    private boolean checkPage(HttpServletRequest request, Access pageItem, String requestedUrl) {

	return StringUtils.isNotBlank(pageItem.getCode())
		&& requestedUrl.equals(request.getContextPath() + pageItem.getCode());
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	    throws IOException, ServletException {
	final HttpServletRequest httpRequest = (HttpServletRequest) request;
	final HttpServletResponse httpResponse = (HttpServletResponse) response;

	final HttpSession session = httpRequest.getSession();
	cacheControl(httpResponse);

	final String requestedUrl = httpRequest.getRequestURI();
	try {
	    // Compruebe si la solicitud es sólo el contexto
	    if (httpRequest.getRequestURI().equals(httpRequest.getContextPath() + "/")) {
		// Si no está logado, va a la páginad de login
		if (!AuthorizationUtil.isUserLoged(httpRequest, session)) {
		    redirectToLoginPage(httpRequest, httpResponse);
		    return;
		}

		// Si está logado, va al home
		if (AuthorizationUtil.isUserLoged(httpRequest, session)) {
		    redirectToHomePage(httpRequest, httpResponse);
		    return;
		}
	    } else {
		// Si no ha iniciado sesión y solicita una página que no está entre los que se puede acceder sin estar
		// conectado ir a inicio de sesión
		if (!AuthorizationUtil.isUserLoged(httpRequest, session) && requestedUrl.toLowerCase().endsWith(".xhtml")
			&& !isUrlAllowedWithoutLogin(httpRequest, requestedUrl)) {
		    redirectToLoginPage(httpRequest, httpResponse);
		    return;
		}

		// Si está conectado y solicita una página que no está en el menú ir a casa
		if (AuthorizationUtil.isUserLoged(httpRequest, session) && requestedUrl.toLowerCase().endsWith(".xhtml")
			&& !isUrlAllowedWithLogin(httpRequest, session, requestedUrl)) {
		    redirectToHomePage(httpRequest, httpResponse);
		    return;
		}
	    }

	    // Entrega la solicitud al filtro siguiente
	    chain.doFilter(request, response);
	} catch (final IOException | ServletException t) {
	    logger.error(null, t);
	}
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
	allowedPages = new ArrayList<>();

	// Página de login
	loginPage = filterConfig.getServletContext().getInitParameter("loginPage");
	allowedPages.add(loginPage.trim());

	// Página de inicio
	homePage = filterConfig.getServletContext().getInitParameter("homePage");

	// Página de error
	errorPage = filterConfig.getServletContext().getInitParameter("errorPage");

	// Otras páginas permitidas sin estar logado
	final String allowedPagesStr = filterConfig.getInitParameter("allowedPages");
	if (StringUtils.isNotBlank(allowedPagesStr)) {
	    final String[] allowedPagesArr = allowedPagesStr.trim().split(",");
	    for (final String page : allowedPagesArr) {
		if (StringUtils.isNotBlank(page)) {
		    allowedPages.add(page.trim());
		}
	    }
	}
    }

    /**
     * Método que indica si la página solicitada está permitida estando logado
     */
    private boolean isUrlAllowedWithLogin(HttpServletRequest request, HttpSession session, String requestedUrl) {
	boolean allowed = false;

	// Comprueba la página de inicio o de error
	if (requestedUrl.equals(request.getContextPath() + "/" + homePage)
		|| requestedUrl.equals(request.getContextPath() + "/" + errorPage)) {
	    allowed = true;
	}

	// Comprueba las opciones de menú
	if (!allowed) {
//	    final List<Access> m = menu.getMenuMap();
//	    if (m != null) {
//		for (final Access permissionXml : m) {
//		    if ((permissionXml.getChild() != null && checkGroup(request, permissionXml, requestedUrl)) || checkPage(request, permissionXml, requestedUrl)) {
	    allowed = true;
//			break;
//		    }
//		}
//	    }
	}

	// Comprueba si es un recurso JSF
	if (!allowed && requestedUrl.contains("javax.faces.resource")) {
	    allowed = true;
	}

	return allowed;
    }

    /**
     * Método que indica si la página solicitada está permitida sin estarlogado
     */
    private boolean isUrlAllowedWithoutLogin(HttpServletRequest request, String requestedUrl) {
	boolean allowed = false;

	// Comprueba las páginas permitidas sin estar logado
	for (final String allowPage : allowedPages) {
	    if (requestedUrl.equals(request.getContextPath() + "/" + allowPage)) {
		allowed = true;
		break;
	    }
	}

	// Comprueba si es un recurso JSF
	if (!allowed && requestedUrl.contains("javax.faces.resource")) {
	    allowed = true;
	}

	return allowed;
    }

    private void redirectToHomePage(HttpServletRequest request, HttpServletResponse response) throws IOException {
	response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/" + homePage));
    }

    private void redirectToLoginPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
	response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/" + loginPage));
    }

}
