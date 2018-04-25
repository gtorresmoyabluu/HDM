package com.bluu.hdm.web.face;

import com.bluu.hdm.web.pojo.Access;
import com.bluu.hdm.web.pojo.User;
import com.bluu.hdm.web.rest.ConsumeREST;
import com.bluu.hdm.web.rest.IConsumeREST;
import com.bluu.hdm.web.util.AuthorizationUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.component.UIViewRoot;
import javax.ws.rs.core.MultivaluedMap;
import org.primefaces.model.menu.DefaultMenuItem;

@SessionScoped
@Named(MenuFace.BEAN_NAME)
public class MenuFace implements Serializable {

    public static final String BEAN_NAME = "menu";
    private static final long serialVersionUID = 1L;

    private Logger logger;

    private ObjectMapper mapper;
    private IConsumeREST apiRest;
    private MultivaluedMap params;
    private User userSession;
    @Inject
    private LocaleFace localeFace;

    private List<Access> menu;
    private MenuModel menumodel;

    public MenuFace() {
	if (mapper == null) {
	    mapper = new ObjectMapper();
	}
	if (apiRest == null) {
	    apiRest = new ConsumeREST();
	}
    }

    private void doGenerateMenu() {
	// Genera el menu para el componente de la vista
	menumodel = new DefaultMenuModel();
	try {
	    getMenu();
	    menu.forEach((permission) -> {
		if (permission.getChild() != null) {
		    DefaultSubMenu submenu = createSubmenuInMenumodel(permission, null);
		    if (!permission.getChild().isEmpty()) {
			permission.getChild().forEach((childPermission) -> {
			    createPageInMenuModel(childPermission, submenu, permission.getCode());
			});
		    }
		} else {
		    DefaultSubMenu submenu = createSubmenuInMenumodel(permission, null);
		    createPageInMenuModel(permission, submenu, permission.getCode());
		}
	    });
	} catch (final Exception e) {
	    menumodel = null;
	    logger.error(e.getMessage());
	}
    }

    private DefaultSubMenu createSubmenuInMenumodel(Access access, DefaultSubMenu parent) {
	//Crea una entrada de submenu en el menu de la aplicación
	DefaultSubMenu submenu = new DefaultSubMenu(access.getDescription(), access.getIcon());
	submenu.setId(new UIViewRoot().createUniqueId());
	if (parent != null) {
	    parent.addElement(submenu);
	} else {
	    menumodel.addElement(submenu);
	}
	return submenu;
    }

    private void createPageInMenuModel(Access access, DefaultSubMenu parent, String directory) {
	//Crea una entrada de pagina en el menu de la aplicacion
	DefaultMenuItem menuItem = new DefaultMenuItem();
	menuItem.setId(new UIViewRoot().createUniqueId());
	menuItem.setAjax(false);
	menuItem.setValue(access.getDescription());
//	if (!StringUtils.isEmpty(parent.getId())) {
//	    menuItem.setHref(String.format("./%s.xhtml", access.getCode()));
//	} else {
	menuItem.setHref(String.format("%s/%s.xhtml", directory, access.getCode()));
//	}
	menuItem.setIcon(access.getIcon());
//	if (access.getCode() != null) {
//	    menuItem.setCommand(access.getCode());
//	} else {
//	    menuItem.setOutcome(access.getCode());
//	}

	if (parent != null) {
	    parent.addElement(menuItem);
	} else {
	    menumodel.addElement(menuItem);
	}
    }

    public List<Access> getMenuMap() {
	return menu;
    }

    private List<Access> getMenu() {
	//if (menu == null) {
	if (userSession == null && AuthorizationUtil.getUserSession(FacesContext.getCurrentInstance()).getUser() != null) {
	    userSession = AuthorizationUtil.getUserSession(FacesContext.getCurrentInstance()).getUser();
	}
	if (params == null) {
	    params = new MultivaluedMapImpl();
	}
	if (!params.containsKey("access_token")) {
	    params.add("access_token", userSession.getToken().getAccess_token());
	}
	List<Access> fullMenu;
	List<Access> list = mapper.convertValue(
		apiRest.getListRestAPI(String.format("roles/access/%s", userSession.getIdRole().getId()), params),
		new TypeReference<List<Access>>() {
	}
	);
	if (list != null) {
	    fullMenu = new ArrayList<>();
	    list.forEach(fullMenu::add);
	    menu = fullMenu;
	}
	//}
	return menu;
    }

    private Access getFromMap(String xmlId, List<Access> map) {
	// Encuentra un permiso en el arbol de permissos
	// El segundo argumento debe ser el arbol de permisos (menu)
	Access returnedPermission;
	for (Access permission : map) {
	    if (permission.getCode() != null) {
		if (permission.getCode().equals(xmlId)) {
		    return permission;
		}
	    }
	    if (permission.getChild() != null) {
		if ((returnedPermission = getFromMap(xmlId, permission.getChild())) != null) {
		    return returnedPermission;
		}
		getFromMap(xmlId, permission.getChild());
	    }
	}
	return null;
    }

    public MenuModel getMenumodel() {
	if (menumodel == null) {
	    localeFace.updateLocale();
	    doGenerateMenu();
	}
	return menumodel;
    }

    public void reset() {
	menumodel = null;
	menu = null;
    }

    public void setMenumodel(MenuModel menumodel) {
	this.menumodel = menumodel;
    }

    private void redirectToHomeByOutcome(String outcome) throws IOException {
	if (StringUtils.isNotBlank(outcome)) {
	    String contextPath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
	    HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	    response.sendRedirect(contextPath + outcome);
	} else {
	    logger.error("No command or outcome found in home page");
	}
    }

    public void redirectToHome() throws IOException {
	if (menumodel == null) {
	    getMenumodel();
	}
	String homepage = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("authHome");
	redirectToHomeByOutcome(homepage);
    }
}
