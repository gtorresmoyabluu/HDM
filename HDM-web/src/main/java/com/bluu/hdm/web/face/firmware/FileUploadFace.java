/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.face.firmware;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Gonzalo Torres
 */
@SessionScoped
@Named(FileUploadFace.BEAN_NAME)
public class FileUploadFace implements Serializable {

    private Logger logger = LogManager.getLogger(FileUploadFace.class.getName());
    public static final String BEAN_NAME = "upload";
    private static final long serialVersionUID = 1L;
    private List<UploadedFile> uploadedFiles;

    @PostConstruct
    public void init() {
        uploadedFiles = new ArrayList<>();
    }

    public void doHandleFileUpload(FileUploadEvent event) {
        logger.debug(event.getFile().getFileName());
        uploadedFiles.add(event.getFile());
    }

    public void doClear() {
        uploadedFiles.clear();
    }

    public void doClear(int index) {
        uploadedFiles.remove(index);
    }

    public int getUploadedSize() {
        return uploadedFiles.size();
    }

    /**
     * Devuelve el último fichero cargado
     * @return 
     */
    public UploadedFile getUploadedFile() {
        return uploadedFiles != null && uploadedFiles.size() > 0 ? uploadedFiles.get(uploadedFiles.size() - 1) : null;
    }

    /**
     * Devuelve una copia del listado de ficheros cargados
     * @return 
     */
    public List<UploadedFile> getUploadedFiles() {
        return uploadedFiles != null ? new ArrayList<>(uploadedFiles) : null;
    }
}
