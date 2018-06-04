/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.face.firmware;

import com.bluu.hdm.web.enums.ExporterFormatEnum;
import com.bluu.hdm.web.enums.ViewTypeEnum;
import com.bluu.hdm.web.exporter.DataTableExporter;
import com.bluu.hdm.web.exporter.Exporter;
import com.bluu.hdm.web.exporter.ExporterFactory;
import com.bluu.hdm.web.model.APILazyDataModel;
import com.bluu.hdm.web.pojo.firmware.Firmware;
import com.bluu.hdm.web.pojo.inventory.Manufacturer;
import com.bluu.hdm.web.pojo.inventory.Model;
import com.bluu.hdm.web.rest.FactoryRest;
import com.bluu.hdm.web.util.AuthorizationUtil;
import com.bluu.hdm.web.util.GetClassUtils;
import com.bluu.hdm.web.util.MessageUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.Visibility;

/**
 *
 * @author Gonzalo Torres
 */
@ViewScoped
@Named(FirmwareFace.BEAN_NAME)
public class FirmwareFace implements Serializable {

    public static final String BEAN_NAME = "firmware";
    private ObjectMapper mapper;
    private Logger logger = LogManager.getLogger(FirmwareFace.class.getName());

    @Inject
    private FileUploadFace upload;

    private Firmware currentItem;
    private Manufacturer manufacturer;
    private Model model;

    private String exportFormat;
    private boolean exportPageOnly;

    private LazyDataModel<Object> items;
    private List<Boolean> toggleableColumns;

    private ViewTypeEnum viewType;

    @PostConstruct
    public void init() {
        if (mapper == null) {
            mapper = new ObjectMapper();
        }
        doRefresh();
        toggleableColumns = Arrays.asList(true, true, true, true, true, false);
    }

    public void doSearch() {
        items = null;

        if (currentItem == null) {
            currentItem = new Firmware();
        }
    }

    public void doClearModels() {
        if (currentItem.getIdModel() != null) {
            currentItem.setIdModel(null);
        }
    }

    public void doClearDescription() {
        if (currentItem.getName() != null) {
            currentItem.setName(null);
        }
    }

    public void doAdd() {
        try {
            // Valida las entradas
            if (!validateAdd()) {
                return;
            }
            if (doProcessFile()) {
                //firmwareBean.registerFirmwareFile(currentItem);
                if (currentItem == null) {
                    currentItem = new Firmware();
                    currentItem.setId(0l);
                }
                model.setManufacturer(manufacturer);
                currentItem.setIdModel(model);
                currentItem.setName(upload.getUploadedFile().getFileName());
                FactoryRest.getInstance().uploadFile(String.format("%s/upload", BEAN_NAME), upload.getUploadedFile(), currentItem);
                // Escribe en el log
                String msg = MessageUtils.getMessage("firmwareFile_add_ok_log");
                String data = currentItem.getName();
                String userLogged = AuthorizationUtil.getUserSession(FacesContext.getCurrentInstance()).getUser().getUsername();

                if (msg != null) {
                    logger.info(MessageFormat.format(msg, data, userLogged));
                }

                // Muestra mensaje
                MessageUtils.addMessage(FacesMessage.SEVERITY_INFO, "firmwareFile_add_ok");
            }
            // Refresca la vista
            doRefreshWOFilter();
        } catch (Exception e) {
            logger.error("method " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " : " + e.getMessage());
            MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "general_operationerror");
        }

    }

    public void doEdit() {
        try {
            // Se validan las entradas
            if (!validateEdit()) {
                return;
            }

            if (upload.getUploadedFile() != null && upload.getUploadedFile().getFileName() != null) {
                doProcessFile();
            }

            //firmwareBean.updateFirmwareFile(currentItem);
            // Escribe en el log
            String msg = MessageUtils.getMessage("firmwareFile_update_ok_log");
            String data = currentItem.getName();
            String userLogged = AuthorizationUtil.getUserSession(FacesContext.getCurrentInstance()).getUser().getUsername();

            if (msg != null) {
                logger.info(MessageFormat.format(msg, data, userLogged));
            }

            // Muestra mensaje
            MessageUtils.addMessage(FacesMessage.SEVERITY_INFO, "firmwareFile_update_ok");

            // Refresca la vista
            doRefreshWOFilter();
        } catch (Exception e) {
            logger.error("method " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " : " + e.getMessage());
            MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "general_operationerror");
        }

    }

    public void doChangeEdit() {

        try {
            // Obtiene primero las variables de la bdd que son obligatorias
//            Manufacturer manufacturer;
//            manufacturer = inventoryBean.findManufacturer(currentItem.getManufacturer().getManufacturer());
//            if (manufacturer == null) {
//                throw new Exception("recovered manufacturer can't be null");
//            }
//            Model model;
//            List<Model> list = new ArrayList<>(currentItem.getModels().size());
//            for (Model m : currentItem.getModels()) {
//                model = inventoryBean.findModel(m.getModel());
//                if (model == null) {
//                    throw new Exception("recovered model can't be null");
//                }
//                list.add(model);
//            }
//
//            // Clonado profundo del objeto
//            currentItem = SerializationUtils.clone(currentItem);
//
//            if (viewType != ViewTypeEnum.edit) {
//                viewType = ViewTypeEnum.edit;
//            }
//
//            currentItem.getIdModel().setManufacturers(manufacturer);
//            currentItem.setFirmware(firmwareBean.findFirmware(currentItem.getFirmware().getFirmware()));
//
//            currentItem.setIdModels(list);
        } catch (Exception e) {
            logger.error("method " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " : " + e.getMessage());
            MessageUtils.addMessage(FacesMessage.SEVERITY_INFO, "firmwareFile_edit_manufacturererror");
        }
    }

    public StreamedContent doDownloadFile() {
        try {
            String storagePath = "";//configurationSt.getConfiguration("firmwareLibraryPath");
            File file = new File(storagePath, currentItem.getName());
            byte[] bytes = FileUtils.readFileToByteArray(file);
            if (bytes != null) {
                return new DefaultStreamedContent(new ByteArrayInputStream(bytes), "application/octet-stream", currentItem.getName());
            }
            return null;
        } catch (IOException e) {
            MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "firmwareFile_download_exist");
            return null;
        }
    }

    public void doDelete() {
        try {
            //firmwareBean.deleteFirmwareFile(currentItem);

            // Escribe en el log
            String msg = MessageUtils.getMessage("firmwareFile_delete_ok_log");
            String data = currentItem.getName();
            String userLogged = AuthorizationUtil.getUserSession(FacesContext.getCurrentInstance()).getUser().getUsername();

            if (msg != null) {
                logger.info(MessageFormat.format(msg, data, userLogged));
            }

            // Muestra mensaje
            MessageUtils.addMessage(FacesMessage.SEVERITY_INFO, "firmwareFile_delete_ok");

            // Se refresca la vista
            doRefreshWOFilter();
        } catch (Exception e) {
            logger.error("method " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " : " + e.getMessage());
            MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "general_operationerror");
        }

    }

    private boolean validateEdit() {
        boolean correcto = true;

        Firmware firmwareFile = currentItem;//.getName();
        if (firmwareFile != null && firmwareFile.getId().intValue() != currentItem.getId().intValue()) {
            MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "firmwareFile_name_exits");
            correcto = false;
        }

        if (currentItem.getName() == null) {
            if (upload.getUploadedFile() == null || upload.getUploadedFile().getFileName() == null) {
                MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "firmwareFile_description_required");
                correcto = false;
            } else {
                firmwareFile = null;//upload.getUploadedFile().getFileName();
                if (firmwareFile != null && firmwareFile.getId().intValue() != currentItem.getId().intValue()) {
                    MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "firmwareFile_description_exits");
                    correcto = false;
                }
            }
        }

        if (!correcto) {
            FacesContext.getCurrentInstance().validationFailed();
        }
        return correcto;
    }

    public boolean doProcessFile() {
        boolean uploaded = true;
        if (upload != null && upload.getUploadedFile() != null) {
            String fileName = upload.getUploadedFile().getFileName();
            if (fileName.lastIndexOf('/') >= 0) { // Quitar path con '/'
                fileName = fileName.substring(fileName.lastIndexOf('/') + 1);
            } else if (fileName.lastIndexOf('\\') >= 0) { // Quitar path con '\'
                fileName = fileName.substring(fileName.lastIndexOf('\\') + 1);
            }
            if (upload.getUploadedFile().getSize() == 0) {
                MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "firmwareFile_upload_file_exist");
            } else {
                currentItem.setName(fileName);
                uploaded = true;
            }
        }
        return uploaded;
    }

    private boolean validateAdd() {
        boolean correcto = true;
        if (upload.getUploadedFile() == null || upload.getUploadedFile().getFileName() == null) {
            MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "firmwareFile_description_required");
            correcto = false;
        }
        if (!correcto) {
            FacesContext.getCurrentInstance().validationFailed();
        }
        return correcto;
    }

    public void doChangeDetail(SelectEvent event) {
        currentItem = (Firmware) event.getObject();
        if (viewType != ViewTypeEnum.detail) {
            viewType = ViewTypeEnum.detail;
        }
    }

    public void doChangeSearch() {
        if (viewType != ViewTypeEnum.search) {
            if (currentItem == null) {
                currentItem = new Firmware();
            }
            viewType = ViewTypeEnum.search;
        } else {
            viewType = ViewTypeEnum.list;
        }
    }

    public void doChangeAdd() {
        if (viewType != ViewTypeEnum.add) {
            currentItem = new Firmware();
            viewType = ViewTypeEnum.add;
        } else {
            viewType = ViewTypeEnum.list;
        }
        upload.doClear();
    }

    public void doExport() {
        DataTable dt = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("form_dt:dataTable");
        Exporter exporter = ExporterFactory.getInstance(ExporterFormatEnum.valueOf(exportFormat));
        exporter.export(FacesContext.getCurrentInstance(), DataTableExporter.getDataExporter(dt, exportPageOnly), BEAN_NAME);
    }

    public void doRefresh() {
        doRefreshWOFilter();
    }

    public void doRefreshWOFilter() {
        items = null;
        currentItem = null;
        model = new Model();
        manufacturer = new Manufacturer();
        model.setManufacturer(manufacturer);
        doSetViewTypeList();
    }

    public void doSetViewTypeList() {
        viewType = ViewTypeEnum.list;
    }

    public Firmware getCurrentItem() {
        return currentItem;
    }

    public List<Manufacturer> getAllManufacturers() {
        List<Manufacturer> list = new ArrayList<>();
        list = (List<Manufacturer>) (Object) GetClassUtils.castToList(
                Manufacturer.class,
                FactoryRest.getInstance().getListRestAPI("manufacturers/all")
        );
        return list;
    }

    public List<Model> getAllModels(Manufacturer manufacturer) {
        List<Model> models = null;
        if (manufacturer != null && manufacturer.getId() != null) {
            models = (List<Model>) (Object) GetClassUtils.castToList(
                    Model.class,
                    FactoryRest.getInstance().getListRestAPI(String.format("models/all/%s", manufacturer.getId()))
            );
        }
        return models;
    }

    public List<Firmware> getAllFirmwares() {
        return null;
    }

    public List<Boolean> getToggleableColumns() {
        return toggleableColumns;
    }

    public ViewTypeEnum getViewType() {
        return viewType;
    }

    public void onColumnToggle(ToggleEvent event) {
        toggleableColumns.set((Integer) event.getData(), event.getVisibility() == Visibility.VISIBLE);
    }

    public void setCurrentItem(Firmware currentItem) {
        this.currentItem = currentItem;
    }

    public void setExportFormat(String exportFormat) {
        this.exportFormat = exportFormat;
    }

    public void setExportPageOnly(boolean exportPageOnly) {
        this.exportPageOnly = exportPageOnly;
    }

    public void setViewType(ViewTypeEnum viewType) {
        this.viewType = viewType;
    }

    public LazyDataModel<Object> getItems() {
        if (items == null) {
            items = new APILazyDataModel(Firmware.class, String.format("%s/all", BEAN_NAME), BEAN_NAME);
        }
        return items;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

}
