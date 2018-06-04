/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.face.administration;

import com.bluu.hdm.web.enums.LicenseErrorEnum;
import com.bluu.hdm.web.face.LocaleFace;
import com.bluu.hdm.web.face.MenuFace;
import com.bluu.hdm.web.pojo.DataList;
import com.bluu.hdm.web.pojo.administracion.Category;
import com.bluu.hdm.web.pojo.administracion.Client;
import com.bluu.hdm.web.pojo.administracion.Configuration;
import com.bluu.hdm.web.pojo.administracion.License;
import com.bluu.hdm.web.rest.FactoryRest;
import com.bluu.hdm.web.util.AuthorizationUtil;
import com.bluu.hdm.web.util.CryptoUtils;
import com.bluu.hdm.web.util.GetClassUtils;
import com.bluu.hdm.web.util.MessageUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *
 * @author Gonzalo Torres
 */
@ViewScoped
@Named(ConfigFace.BEAN_NAME)
public class ConfigFace implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String BEAN_NAME = "config";
    private Logger logger = LogManager.getLogger(ConfigFace.class.getName());

    private ObjectMapper mapper;

    @Inject
    private LocaleFace locale;
    @Inject
    private MenuFace menu;

    private List<Category> categories;

    private HashMap<Long, HashMap<String, String>> configValues = new HashMap<>();
    private HashMap<Long, HashMap<String, String>> editedValues = new HashMap<>();
    private boolean exitsClient;

    private Configuration currentItem;

    private boolean running = false;

    //Methods
    @PostConstruct
    public void init() {
        if (mapper == null) {
            mapper = new ObjectMapper();
        }
        exitsClient = AuthorizationUtil.getUserSession(FacesContext.getCurrentInstance()).getUser().getIdClient() != null;
        initData();
        doRefresh();
    }

    public HashMap<Long, HashMap<String, String>> getConfigValues() {
        return configValues;
    }

    public void setConfigValues(HashMap<Long, HashMap<String, String>> configValues) {
        this.configValues = configValues;
    }

    public HashMap<Long, HashMap<String, String>> getEditedValues() {
        return editedValues;
    }

    public void setEditedValues(HashMap<Long, HashMap<String, String>> editedValues) {
        this.editedValues = editedValues;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public Configuration getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(Configuration currentItem) {
        this.currentItem = currentItem;
    }

    public List<SelectItem> getAvailableClients() {
        List<SelectItem> result = new ArrayList<>();
        List<Client> clients = (List<Client>) (Object) GetClassUtils.castToList(Client.class, FactoryRest.getInstance().getListRestAPI("clients/all"));
        if (!clients.isEmpty()) {
            clients.forEach((client) -> {
                result.add(new SelectItem(client, client.getName()));
            });
        }
        return result;
    }

    public List<Category> getCategories() {
        if (categories == null) {
            configValues = new HashMap<>();
            categories = new ArrayList<>();
            Long idClient = AuthorizationUtil.getUserSession(FacesContext.getCurrentInstance()).getUser().getIdClient() == null ? currentItem.getClient().getId() : AuthorizationUtil.getUserSession(FacesContext.getCurrentInstance()).getUser().getIdClient().getId();
            List<Category> list = (List<Category>) (Object) GetClassUtils.castToList(Category.class, FactoryRest.getInstance().getListRestAPI("category/all"));
            for (Category category : list) {
                if (category.isShowServer()) {
                    System.out.println("Category: " + category.getId());
                    category.setConfigurationList(
                            (List<Configuration>) (Object) GetClassUtils.castToList(
                                    Configuration.class,
                                    FactoryRest.getInstance().getListRestAPI(String.format("%s/all/%s/%s", BEAN_NAME, idClient, category.getId()))
                            )
                    );
                    categories.add(category);
                    HashMap<String, String> values = new HashMap<>();
                    category.getConfigurationList().forEach((cnf) -> {
                        values.put(cnf.getDataKey(), cnf.getDataValue());
                        editedValues.put(cnf.getId(), new HashMap<>());
                        configValues.put(cnf.getId(), values);
                    });
                }
            }
        }
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void doEditData() {
        this.running = !this.running;
    }

    public boolean isExitsClient() {
        return exitsClient;
    }

    public void setExitsClient(boolean b) {
        this.exitsClient = b;
    }

    public void initData() {
        running = false;
        editedValues = new HashMap<>();
        configValues = new HashMap<>();
    }

    public void doRefresh() {
        running = false;
        categories = null;
        editedValues = new HashMap<>();
        configValues = new HashMap<>();
        AuthorizationUtil.doRefreshAll(mapper);
    }

    public void doSelectClient() {
        if (currentItem.getClient() != null) {
            if (currentItem.getClient().getId() == null) {
                MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "configuration_no_select_client");
                running = exitsClient = false;
            } else {
                DataList obj = mapper.convertValue(FactoryRest.getInstance().getRestAPI(String.format("%s/all/%s", BEAN_NAME, currentItem.getClient().getId())), DataList.class);
                if (obj.getSize() <= 0) {
                    FactoryRest.getInstance().postRestAPI(String.format("%s/init", BEAN_NAME), currentItem.getClient());
                    categories = null;
                }
                running = exitsClient = true;
            }
        }
    }

    public String getActiveIndexString() {
        int totalNumberOfTabs = getCategories().size();
        StringBuilder out = new StringBuilder();
        String prefix = "";
        for (int i = 0; i < totalNumberOfTabs; i++) {
            out.append(prefix);
            prefix = ",";
            out.append(i);
        }
        return out.toString();
    }

    public List<Configuration> getConfigurationsByCategory(String name) {
        List<Configuration> list = new ArrayList<>();
        for (final Category category : categories) {
            if (category.getName().equals(name)) {
                list = category.getConfigurationList();
                break;
            }
        }
        return list;
    }

    public boolean isValidate(Long idKey, String dataKey) {
        if (editedValues.get(idKey) != null) {
            return (editedValues.get(idKey).get(dataKey) != null);
        }
        return false;
    }

    public boolean isEdit(Long idKey, String dataKey) {
        if (editedValues.get(idKey) != null) {
            return (editedValues.get(idKey).get(dataKey) == null);
        }
        return false;
    }

    public void doResetConfig(Long idKey, String confKey) {
        try {
            if (editedValues.get(idKey) != null) {
                if (confKey != null && editedValues.get(idKey).get(confKey) != null) {
                    // Si se cambió el valor pero no se confirmó y se edita otro campo, se debe recuperar el valor original
                    setConfiguration(confKey, editedValues.get(idKey).get(confKey));
                }
                editedValues.get(idKey).remove(confKey);
            }
        } catch (Exception ex) {
            logger.error("Error (resetConfig): " + ex.getMessage());
            MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "validation_action_update_error_detail");
        }
    }

    public void setConfiguration(String key, String val) throws Exception {
        // Como he modificado un valor de configuración, lo modifico también en el singleton
        boolean found = false;
        for (final Category category : categories) {
            if (!found) {
                for (final Configuration configuration : category.getConfigurationList()) {
                    if (configuration.getDataKey().equals(key)) {
                        configuration.setDataValue(val);
                        found = true;
                        break;
                    }
                }
            } else {
                break;
            }
        }
        categories = getCategories();
    }

    public String doProcess(String confKey, String confValue, Long idKey) {
        try {
            if (isEdit(idKey, confKey)) {
                HashMap<String, String> values = new HashMap<>();
                values.put(confKey, confValue);
                editedValues.put(idKey, values);
                logger.info("Edited: (" + confKey + "," + editedValues.get(idKey).get(confKey) + ")");

            } else {
                logger.info("Updated: (" + confKey + "," + editedValues.get(idKey).get(confKey) + ")");
                // Envía el valor al singleton y a base de datos
                Configuration confObj = mapper.convertValue(
                        FactoryRest.getInstance().getRestAPI(String.format("%s/find/%s", BEAN_NAME, idKey)),
                        Configuration.class
                );

                if (confKey.contains("Password") || confObj.getFieldType().contains("Secret")) {
                    confValue = CryptoUtils.encrypt(confValue);
                }
                confObj.setId(idKey);
                confObj.setDataValue(confValue);
                confObj.setDataKey(confKey);
                Configuration result = mapper.convertValue(
                        FactoryRest.getInstance().putRestAPI(String.format("%s/upd", BEAN_NAME), Configuration.class, confObj),
                        Configuration.class
                );
                if (result == null) {
                    MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "general_operationerror");
                } else {
                    if (confKey.equals("language")) {
                        String lang = confValue.substring(0, confValue.indexOf("_"));
                        String coun = confValue.substring(confValue.indexOf("_") + 1, confValue.length());
                        locale.setCurrent(new Locale(lang, coun));
                        menu.reset();
                        //return "/administration/configuration.xhtml?faces-redirect=true";
                    } else if (confKey.equals("license")) {
                        // Se comprueba que la licencia es correcta. Si no se vuelve a escribir en bbdd la licencia anterior
                        License license = mapper.convertValue(FactoryRest.getInstance().getRestAPI(String.format("license/check/%s", confValue)), License.class);
                        if (license == null) {
                            license = new License(confValue);
                            license.setIdClient(AuthorizationUtil.getUserSession(FacesContext.getCurrentInstance()).getUser().getIdClient() == null ? currentItem.getClient() : AuthorizationUtil.getUserSession(FacesContext.getCurrentInstance()).getUser().getIdClient());
                            FactoryRest.getInstance().postRestAPI("license/add", license);
                        } else {
                            if (license.isBlocked()) {
                                setConfiguration(confKey, editedValues.get(idKey).get(confKey));
                                MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "configuration_update_license_error");

                                String errors = "";
                                List<LicenseErrorEnum> licerrors = license.getErrors();
                                for (LicenseErrorEnum err : licerrors) {
                                    errors += MessageUtils.getMessage("license_" + err) + ", ";
                                }
                                if (errors.endsWith(", ")) {
                                    errors = errors.substring(0, errors.length() - 2);
                                }

                                logger.error(MessageUtils.getMessage("configuration_update_license_error") + ": " + errors);
                            } else {
                                // Inicializa la nueva licencia
                                license.setCode(confValue);
                                license.setIdClient(AuthorizationUtil.getUserSession(FacesContext.getCurrentInstance()).getUser().getIdClient() == null ? currentItem.getClient() : AuthorizationUtil.getUserSession(FacesContext.getCurrentInstance()).getUser().getIdClient());
                                FactoryRest.getInstance().putRestAPI("license/upd", License.class, license);
                            }
                        }
                    }
                    MessageUtils.addMessage(FacesMessage.SEVERITY_INFO, "configuration_update_success");
                }
                editedValues.get(idKey).remove(confKey);
            }
        } catch (Exception ex) {
            logger.error("Error (setConfigurationValue): " + ex.getMessage());
            MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "configuration_update_error");
        }
        return null;
    }

    public String getServerIP() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "unknown";
        }
    }

    public String getMessage(String key) {
        return MessageUtils.getMessage(key);
    }
}
