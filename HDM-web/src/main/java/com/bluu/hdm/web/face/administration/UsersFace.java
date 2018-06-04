package com.bluu.hdm.web.face.administration;

import com.bluu.hdm.web.enums.ExporterFormatEnum;
import com.bluu.hdm.web.enums.ViewTypeEnum;
import com.bluu.hdm.web.exporter.DataTableExporter;
import com.bluu.hdm.web.exporter.Exporter;
import com.bluu.hdm.web.exporter.ExporterFactory;
import com.bluu.hdm.web.model.APILazyDataModel;
import com.bluu.hdm.web.pojo.administracion.Client;
import com.bluu.hdm.web.pojo.administracion.Role;
import com.bluu.hdm.web.pojo.administracion.User;
import com.bluu.hdm.web.rest.FactoryRest;
import com.bluu.hdm.web.util.AuthorizationUtil;
import com.bluu.hdm.web.util.GetClassUtils;
import com.bluu.hdm.web.util.MessageUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.Visibility;

import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;

@ViewScoped
@Named(UsersFace.BEAN_NAME)
public class UsersFace implements Serializable {

    public static final String BEAN_NAME = "users";
    private static final long serialVersionUID = 1L;
    private static Matcher mather;
    Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    private User currentItem;
    private LazyDataModel<Object> items;
    private List<User> itemsFiltered;
    private List<Boolean> toggleableColumns;

    private String profilePasswd;
    private String profilePasswdConf;
    private ViewTypeEnum viewType;

    private String exportFormat;
    private boolean exportPageOnly;

    private ObjectMapper mapper;

    @PostConstruct
    public void init() {
        if (mapper == null) {
            mapper = new ObjectMapper();
        }
        doRefresh();
        toggleableColumns = Arrays.asList(true, true, true, true, true, false);
    }

    public void doAdd() {
        try {
            // Valida las entradas
            if (!validateAdd()) {
                return;
            } else if (FactoryRest.getInstance().getRestAPI(String.format("%s/getUserName/%s", BEAN_NAME, currentItem.getUsername().trim().toLowerCase())) != null) {
                MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "users_username_exits");
                FacesContext.getCurrentInstance().validationFailed();
            } else {
                FactoryRest.getInstance().postRestAPI(String.format("%s/add", BEAN_NAME), currentItem);
                String userLogged = AuthorizationUtil.getUserSession(FacesContext.getCurrentInstance()).getUser().getUsername();
                MessageUtils.addMessage(FacesMessage.SEVERITY_INFO, "users_add_ok");
                // Refresca vista
                doRefreshWOFilter();
            }
        } catch (Exception e) {
            MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, UsersFace.BEAN_NAME, String.format("Error: %s", e.getMessage()));
        }
    }

    public void doChangeAdd() {
        if (viewType != ViewTypeEnum.add) {
            currentItem = new User();
            currentItem.setIdRole(new Role());
            currentItem.setIdClient(new Client());
            viewType = ViewTypeEnum.add;
        } else {
            viewType = ViewTypeEnum.list;
        }
    }

    public void doChangeDetail(SelectEvent event) {
        currentItem = new User();
        currentItem.setIdRole(new Role());
        currentItem.setIdClient(new Client());
        if (event.getObject() != null) {
            currentItem = mapper.convertValue(event.getObject(), User.class);
        }
        if (viewType != ViewTypeEnum.detail) {
            viewType = ViewTypeEnum.detail;
        }
    }

    public void doChangeEdit() {
        // Realiza un clonado profundo del objeto
        currentItem = mapper.convertValue(currentItem, User.class);
        if (viewType != ViewTypeEnum.edit) {
            viewType = ViewTypeEnum.edit;
        }
    }

    public void doDelete() {
        try {
            // Borra el usuario
            if (FactoryRest.getInstance().delRestAPI(String.format("%s/del/%s", BEAN_NAME, currentItem.getId()))) {
                MessageUtils.addMessage(FacesMessage.SEVERITY_INFO, "users_delete_ok");
            } else {
                MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "general_operationerror");
            }
            // Refresca vista
            doRefreshWOFilter();
        } catch (Exception e) {
            MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "general_operationerror");
        }
    }

    public void doEdit() {
        try {
            // Valida las entradas
            if (!validateEdit()) {
                return;
            } else {
                FactoryRest.getInstance().putRestAPI(String.format("%s/upd", BEAN_NAME), User.class, currentItem);
                MessageUtils.addMessage(FacesMessage.SEVERITY_INFO, "users_update_ok");
            }
            // Refresca vista
            doRefreshWOFilter();
        } catch (Exception e) {
            MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "general_operationerror");
        }

    }

    public void doEditProfile() {
        try {
            if (!validateEditProfile()) {
                return;
            }
//	    // Modifica el usuario
            User userLogged = AuthorizationUtil.getUserSession(FacesContext.getCurrentInstance()).getUser();
            userLogged.setPassword(profilePasswd);

            MessageUtils.addMessage(FacesMessage.SEVERITY_INFO, "users_profile_ok");
        } catch (Exception e) {
            MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "general_operationerror");
        }
    }

    public void doExport() {
        try {
            DataTable dt = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("form_dt:dataTable");
            Exporter exporter = ExporterFactory.getInstance(ExporterFormatEnum.valueOf(exportFormat));
            exporter.export(FacesContext.getCurrentInstance(), DataTableExporter.getDataExporter(dt, exportPageOnly), "users");
        } catch (Exception e) {
            MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "general_operationerror");
        }
    }

    public void doOnColumnToggle(ToggleEvent event) {
        toggleableColumns.set((Integer) event.getData(), event.getVisibility() == Visibility.VISIBLE);
        RequestContext.getCurrentInstance().execute("fixSearch();");
    }

    public void doSecondStepFixSearch() {

    }

    public void doRefresh() {
        doRefreshWOFilter();
    }

    public void doRefreshWOFilter() {
        items = null;
        currentItem = new User();
        currentItem.setIdRole(new Role());
        currentItem.setIdClient(new Client());
        doSetViewTypeList();
        profilePasswd = null;
        profilePasswdConf = null;
    }

    public void doSearch() {
        items = null;
    }

    public void doSetViewTypeList() {
        viewType = ViewTypeEnum.list;
    }

    public List<SelectItem> getAvailableRoles() {
        List<SelectItem> result = new ArrayList<>();
        List<Role> roles = (List<Role>) (Object) GetClassUtils.castToList(Role.class, FactoryRest.getInstance().getListRestAPI("roles/all"));
        if (!roles.isEmpty()) {
            roles.forEach((role) -> {
                result.add(new SelectItem(role, role.getName()));
            });
        }
        return result;
    }

    public List<SelectItem> getAvailableClients() {
        List<SelectItem> result = new ArrayList<>();
        List<Client> clients = (List<Client>) (Object) GetClassUtils.castToList(Client.class, FactoryRest.getInstance().getListRestAPI("clients/all"));
        if (!clients.isEmpty()) {
            clients.forEach((client) -> {
                result.add(new SelectItem(client, client.getName(), client.getName()));
            });
        }
        return result;
    }

    public User getCurrentItem() {
        return currentItem;
    }

    public boolean getIsItemsFiltered() {
        return true;
    }

    public LazyDataModel<Object> getItems() {
        if (items == null) {
            items = new APILazyDataModel(User.class,
                    String.format("%s/all", BEAN_NAME), BEAN_NAME);
        }
        return items;
    }

    public String getProfilePasswd() {
        return profilePasswd;
    }

    public String getProfilePasswdConf() {
        return profilePasswdConf;
    }

    public List<Boolean> getToggleableColumns() {
        return toggleableColumns;
    }

    public ViewTypeEnum getViewType() {
        return viewType;
    }

    public void setCurrentItem(User currentItem) {
        User u = mapper.convertValue(currentItem, User.class);
        this.currentItem = u;
    }

    public void setExportFormat(String exportFormat) {
        this.exportFormat = exportFormat;
    }

    public void setExportPageOnly(boolean exportPageOnly) {
        this.exportPageOnly = exportPageOnly;
    }

    public void setProfilePasswd(String profilePasswd) {
        this.profilePasswd = profilePasswd;
    }

    public void setProfilePasswdConf(String profilePasswdConf) {
        this.profilePasswdConf = profilePasswdConf;
    }

    public void setViewType(ViewTypeEnum viewType) {
        this.viewType = viewType;
    }

    public List<User> getItemsFiltered() {
        return itemsFiltered;
    }

    public void setItemsFiltered(List<User> itemsFiltered) {
        this.itemsFiltered = itemsFiltered;
    }

    private boolean validateAdd() {
        boolean correcto = true;

        if (currentItem.getFirstname().trim().length() == 0) {
            correcto = false;
            MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "users_firstname_empty");
        } else if (currentItem.getLastname().trim().length() == 0) {
            correcto = false;
            MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "users_lastname_empty");
        } else if (currentItem.getEmail().trim().length() == 0) {
            correcto = false;
            MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "users_email_empty");
        } else if (!currentItem.getEmail().trim().isEmpty()) {
            mather = pattern.matcher(currentItem.getEmail().trim());
            if (!mather.find()) {
                correcto = false;
                MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "users_email_error_format");
            }
        } else if (!currentItem.getPassword().equals(currentItem.getPasswordConf())) {
            correcto = false;
            MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "users_passwd_different");
        } else if (currentItem.getIdRole().getId() == null || currentItem.getIdRole().getId() <= 0) {
            correcto = false;
            MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "users_rol_not_select");
        }

        if (!correcto) {
            FacesContext.getCurrentInstance().validationFailed();
        }
        return correcto;
    }

    private boolean validateEdit() {
        boolean correcto = true;

        if (currentItem.getFirstname().trim().length() == 0) {
            correcto = false;
            MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "users_firstname_empty");
        } else if (currentItem.getLastname().trim().length() == 0) {
            correcto = false;
            MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "users_lastname_empty");
        } else if (currentItem.getEmail().trim().length() == 0) {
            correcto = false;
            MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "users_email_empty");
        } else if (!currentItem.getEmail().trim().isEmpty()) {
            mather = pattern.matcher(currentItem.getEmail().trim());
            if (!mather.find()) {
                correcto = false;
                MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "users_email_error_format");
            }
        } else if (StringUtils.isNotBlank(currentItem.getPassword()) && StringUtils.isBlank(currentItem.getPasswordConf())) {
            correcto = false;
            MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "users_passwdconf_required");
        } else if (StringUtils.isBlank(currentItem.getPassword()) && StringUtils.isNotBlank(currentItem.getPasswordConf())) {
            correcto = false;
            MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "users_passwd_required");
        } else if (StringUtils.isNotBlank(currentItem.getPassword()) && StringUtils.isNotBlank(currentItem.getPasswordConf())
                && !currentItem.getPassword().equals(currentItem.getPasswordConf())) {
            correcto = false;
            MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "users_passwd_different");
        } else if (currentItem.getIdRole().getId() == null || currentItem.getIdRole().getId() <= 0) {
            correcto = false;
            MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "users_rol_not_select");
        }
        if (!correcto) {
            FacesContext.getCurrentInstance().validationFailed();
        }

        return correcto;
    }

    private boolean validateEditProfile() {
        boolean correcto = true;

        if (!profilePasswd.equals(profilePasswdConf)) {
            correcto = false;
            MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "users_passwd_different");
        }

        if (!correcto) {
            FacesContext.getCurrentInstance().validationFailed();
        }

        return correcto;
    }
}
