package com.bluu.hdm.web.face;

import com.bluu.hdm.web.pojo.administracion.Access;
import com.bluu.hdm.web.pojo.administracion.User;
import com.bluu.hdm.web.rest.FactoryRest;
import com.bluu.hdm.web.util.AuthorizationUtil;
import com.bluu.hdm.web.util.GetClassUtils;
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
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import org.primefaces.model.menu.DefaultMenuItem;

@SessionScoped
@Named(MenuFace.BEAN_NAME)
public class MenuFace implements Serializable {

    public static final String BEAN_NAME = "menu";
    private static final long serialVersionUID = 1L;

    private Logger logger;

    private User userSession;

    @Inject
    private LocaleFace localeFace;

    private List<Access> menu;
    private MenuModel menumodel;

    @PostConstruct
    public void init() {
        getMenu();
        menumodel = new DefaultMenuModel();
        doGenerateMenu();
    }

    private void doGenerateMenu() {
        // Genera el menu para el componente de la vista
        try {
            for (Access m : menu) {
                String directory = m.getCode();
                if (m.getChild() != null) {
                    DefaultSubMenu firstSubMenu = new DefaultSubMenu(m.getDescription());
                    firstSubMenu.setIcon(m.getIcon());
                    for (Access subMenu : m.getChild()) {
                        if (Objects.equals(subMenu.getParent(), m.getId())) {
                            DefaultMenuItem menuItem = new DefaultMenuItem(subMenu.getDescription());
                            menuItem.setIcon(subMenu.getIcon());
                            menuItem.setOutcome(String.format("/auth/%s/%s.xhtml", directory, subMenu.getCode()));
                            firstSubMenu.addElement(menuItem);
                        }
                    }
                    menumodel.addElement(firstSubMenu);
                } else {
                    DefaultMenuItem item = new DefaultMenuItem(m.getDescription());
                    item.setIcon(m.getIcon());
                    item.setOutcome(String.format("/auth/%s/%s.xhtml", directory, m.getCode()));
                    menumodel.addElement(item);
                }
            }
        } catch (final Exception e) {
            menumodel = null;
            logger.error(e.getMessage());
        }
    }

    public List<Access> getMenu() {
        if (userSession == null && AuthorizationUtil.getUserSession(FacesContext.getCurrentInstance()).getUser() != null) {
            userSession = AuthorizationUtil.getUserSession(FacesContext.getCurrentInstance()).getUser();
        }
        menu = (List<Access>) (Object) GetClassUtils.castToList(
                Access.class,
                FactoryRest.getInstance().getListRestAPI(String.format("roles/access/%s", userSession.getIdRole().getId()))
        );
        return menu;
    }

    public void setMenu(List<Access> menu) {
        this.menu = menu;
    }

    public MenuModel getMenumodel() {
        if (menumodel == null) {
            localeFace.updateLocale();
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
        String homepage = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("authHome");
        redirectToHomeByOutcome(homepage);
    }
}
