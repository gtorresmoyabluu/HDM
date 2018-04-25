package com.bluu.hdm.web.exporter;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Exporter {

    void export(FacesContext facesContext, DataExporter data, String fileName);

    void export(HttpServletRequest request, HttpServletResponse response, DataExporter data, String fileName);
}
