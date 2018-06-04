package com.bluu.hdm.web.face.administration;

import com.bluu.hdm.web.enums.ExporterFormatEnum;
import com.bluu.hdm.web.enums.ViewTypeEnum;
import com.bluu.hdm.web.exporter.DataTableExporter;
import com.bluu.hdm.web.exporter.Exporter;
import com.bluu.hdm.web.exporter.ExporterFactory;
import com.bluu.hdm.web.model.APILazyDataModel;
import com.bluu.hdm.web.pojo.administracion.Access;
import com.bluu.hdm.web.pojo.administracion.Role;
import com.bluu.hdm.web.pojo.administracion.User;
import com.bluu.hdm.web.rest.FactoryRest;
import com.bluu.hdm.web.util.GetClassUtils;
import com.bluu.hdm.web.util.MessageUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.log4j.Logger;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.TreeNode;
import org.primefaces.model.Visibility;

@ViewScoped
@Named(RolesFace.BEAN_NAME)
public class RolesFace implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String BEAN_NAME = "roles";
    private Logger logger;

    private ObjectMapper mapper;

    private String exportFormat;
    private boolean exportPageOnly;
    private Boolean showtable;

    private ViewTypeEnum viewType;
    private List<Boolean> toggleableColumns;

    private LazyDataModel<Object> items;
    private Role currentItem;

    /* permissionsTree : es el objeto con el que trabaja el treetable de la vista */
    private TreeNode permissionsTree;
    /* treeNodeExpandCollapse : un flag para determinar cuando se expande
    y cuando se colapsa el treetable de la vista*/
    private boolean treeNodeExpandCollapse;

    private Access currentAccess;

    @PostConstruct
    public void init() {
        if (mapper == null) {
            mapper = new ObjectMapper();
        }
        doRefresh();
        toggleableColumns = Arrays.asList(true, true, false);
    }

    public void doRefresh() {
        doRefreshWOFilter();
        treeNodeExpandCollapse = true;
    }

    public void doRefreshWOFilter() {
        items = null;
        currentItem = new Role();
        showtable = false;
        doSetViewTypeList();
    }

    public void doSetViewTypeList() {
        viewType = ViewTypeEnum.list;
        showtable = !showtable;
    }

    public void doChangeAdd() {
        if (viewType != ViewTypeEnum.add) {
            currentItem = new Role();
            viewType = ViewTypeEnum.add;
        } else {
            viewType = ViewTypeEnum.list;
        }
    }

    public void doChangeDetail(SelectEvent event) {
        currentItem = new Role();
        if (event.getObject() != null) {
            currentItem = mapper.convertValue(event.getObject(), Role.class);
        }
        if (viewType != ViewTypeEnum.detail) {
            viewType = ViewTypeEnum.detail;
        }
    }

    public void doChangeEdit() {
        // Realiza un clonado profundo del objeto
        currentItem = mapper.convertValue(currentItem, Role.class);
        if (viewType != ViewTypeEnum.edit) {
            viewType = ViewTypeEnum.edit;
        }
    }

    public void doChangeAccess() {
        // Realiza un clonado profundo del objeto
        currentItem = mapper.convertValue(currentItem, Role.class);
        if (viewType != ViewTypeEnum.access) {
            viewType = ViewTypeEnum.access;
            getAccessTree(currentItem.getId());
            showtable = !showtable;
        }
    }

    //Events
    public void doAdd() {
        try {
            // Valida las entradas
            if (!validateAdd()) {
                return;
            } else if (FactoryRest.getInstance().getRestAPI(String.format("%s/getRoleName/%s", BEAN_NAME, currentItem.getName().trim())) != null) {
                MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "roles_nameexits");
                FacesContext.getCurrentInstance().validationFailed();
            } else {
                FactoryRest.getInstance().postRestAPI(String.format("%s/add", BEAN_NAME), currentItem);
                MessageUtils.addMessage(FacesMessage.SEVERITY_INFO, "roles_creation_success");
            }
        } catch (Exception e) {
            MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "general_operationerror");
        }
        // Refresca vista
        doRefreshWOFilter();
    }

    private boolean validateAdd() {
        boolean correcto = true;

        if (currentItem.getName().trim().length() == 0) {
            correcto = false;
            MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "roles_name_empty");
        }
        if (!correcto) {
            FacesContext.getCurrentInstance().validationFailed();
        }
        return correcto;
    }

    public void doEdit() {
        try {
            // Valida las entradas
            if (!validateEdit()) {
                return;
            } else {
                FactoryRest.getInstance().putRestAPI(String.format("%s/upd", BEAN_NAME), Role.class, currentItem);
                MessageUtils.addMessage(FacesMessage.SEVERITY_INFO, "roles_edit_success");
            }
            // Refresca vista
            doRefreshWOFilter();
        } catch (Exception e) {
            MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "general_operationerror");
        }

    }

    private boolean validateEdit() {
        boolean correcto = true;
        if (currentItem.getName().trim().length() == 0) {
            correcto = false;
            MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "users_firstname_empty");
        }
        if (!correcto) {
            FacesContext.getCurrentInstance().validationFailed();
        }
        return correcto;
    }

    public void doDelete() {
        try {
            if (!validateRolesForDelete()) {
                return;
            } else {
                // Borra el usuario
                if (FactoryRest.getInstance().delRestAPI(String.format("%s/del/%s", BEAN_NAME, currentItem.getId()))) {
                    MessageUtils.addMessage(FacesMessage.SEVERITY_INFO, "roles_delete_success", currentItem.getName());
                }
                else{
                    MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "general_operationerror");
                }
                // Refresca vista
                doRefreshWOFilter();
            }
        } catch (Exception e) {
            MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "general_operationerror");
        }
    }

    //Validations
    private boolean validateRolesForDelete() {
        boolean validate = true;
        List<User> userByRol = (List<User>) (Object) GetClassUtils.castToList(User.class, FactoryRest.getInstance().getListRestAPI(String.format("%s/users/%s", BEAN_NAME, currentItem.getId())));
        //comprueba si el rol esta asignado a algún usuario
        if (userByRol == null) {
            return validate;
        } else if (userByRol.size() > 0) {
            validate = false;
            MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "roles_delete_fail_roleused", currentItem.getName());
        }
        return validate;
    }

    public void doSecondStepFixSearch() {

    }

    public void doExport() {
        try {
            DataTable dt = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("form_dt:dataTable");
            Exporter exporter = ExporterFactory.getInstance(ExporterFormatEnum.valueOf(exportFormat));
            exporter.export(FacesContext.getCurrentInstance(), DataTableExporter.getDataExporter(dt, exportPageOnly), "roles");
        } catch (Exception e) {
            MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "general_operationerror");
        }
    }

    public void doOnColumnToggle(ToggleEvent event) {
        toggleableColumns.set((Integer) event.getData(), event.getVisibility() == Visibility.VISIBLE);
        RequestContext.getCurrentInstance().execute("fixSearch();");
    }

    //Getters & Setters
    public ViewTypeEnum getViewType() {
        return viewType;
    }

    public void setViewType(ViewTypeEnum viewType) {
        this.viewType = viewType;
    }

    public LazyDataModel<Object> getItems() {
        if (items == null) {
            items = new APILazyDataModel(Role.class, String.format("%s/all", BEAN_NAME), BEAN_NAME);
        }
        return items;
    }

    public void setItems(LazyDataModel<Object> items) {
        this.items = items;
    }

    public Role getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(Role rolItem) {
        currentItem = mapper.convertValue(rolItem, Role.class);
    }

    public List<Boolean> getToggleableColumns() {
        return toggleableColumns;
    }

    public void setToggleableColumns(List<Boolean> toggleableColumns) {
        this.toggleableColumns = toggleableColumns;
    }

    public String getExportFormat() {
        return exportFormat;
    }

    public void setExportFormat(String exportFormat) {
        this.exportFormat = exportFormat;
    }

    public boolean isExportPageOnly() {
        return exportPageOnly;
    }

    public void setExportPageOnly(boolean exportPageOnly) {
        this.exportPageOnly = exportPageOnly;
    }

    public Boolean getShowtable() {
        return showtable;
    }

    public void setShowtable(Boolean showtable) {
        this.showtable = showtable;
    }

    public TreeNode getPermissionsTree() {
        return permissionsTree;
    }

    public void setPermissionsTree(TreeNode permissionsTree) {
        this.permissionsTree = permissionsTree;
    }

    public boolean isTreeNodeExpandCollapse() {
        return treeNodeExpandCollapse;
    }

    public void setTreeNodeExpandCollapse(boolean treeNodeExpandCollapse) {
        this.treeNodeExpandCollapse = treeNodeExpandCollapse;
    }

    public void collapseAll() {
        setExpandedRecursively(permissionsTree, false);
    }

    public void expandAll() {
        setExpandedRecursively(permissionsTree, true);
    }

    private void setExpandedRecursively(final TreeNode node, final boolean expanded) {
        node.getChildren().forEach((child) -> {
            setExpandedRecursively(child, expanded);
        });
        node.setExpanded(expanded);
    }

    public void doCheckboxClick(Long idRole, Access ac) {
        System.out.println(String.format("%s %s %s %s", idRole, ac.getParent(), ac.getId(), ac.getActive()));
        currentAccess = new Access();
        currentAccess.setIdRole(idRole);
        if (ac.getParent() == null) { //Permisos al Padre y todos sus hijos
            currentAccess.setParent(ac.getId());
            currentAccess.setId(0l);
        } else {//Permisos al padre y solo al hijo
            currentAccess.setParent(0l);
            currentAccess.setId(ac.getId());
        }

        FactoryRest.getInstance().postRestAPI(String.format("%s/accessRol", BEAN_NAME), currentAccess);
        permissionsTree = null;
        getAccessTree(idRole);
    }

    private void getAccessTree(Long id) {
        if (id != null) {
            TreeNode root = new DefaultTreeNode("Permissions", null);
            List<Access> parents = (List<Access>) (Object) GetClassUtils.castToList(Access.class, FactoryRest.getInstance().getListRestAPI(String.format("%s/%s/parent", BEAN_NAME, id)));
            if (!parents.isEmpty()) {
                parents.forEach((parent) -> {
                    List<Access> childs = (List<Access>) (Object) GetClassUtils.castToList(Access.class, FactoryRest.getInstance().getListRestAPI(String.format("%s/%s/parent/%s", BEAN_NAME, id, parent.getId())));
                    if (!childs.isEmpty()) {
                        TreeNode parentNode = new DefaultTreeNode(parent, root);
                        childs.forEach((child) -> {
                            parentNode.getChildren().add(new DefaultTreeNode(child));
                        });
                        parentNode.setExpanded(true);
                    } else {
                        root.getChildren().add(new DefaultTreeNode(parent));
                    }
                });
            }
            permissionsTree = root;
        }
    }
}
