package com.bluu.hdm.web.exporter;

import com.bluu.hdm.web.enums.ExporterFormatEnum;
import com.bluu.hdm.web.servlet.DownloadByteArrServlet;
import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public abstract class BaseExporter implements Exporter {

    protected ExporterFormatEnum exporterType;

    @Inject
    private transient Logger logger;

    public BaseExporter(ExporterFormatEnum exporterType) {
	this.exporterType = exporterType;
    }

    @Override
    public void export(FacesContext facesContext, DataExporter data, String fileName) {
	final HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
	final HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
	export(request, response, data, fileName);
    }

    @Override
    public void export(HttpServletRequest request, HttpServletResponse response, DataExporter data, String fileName) {
	try {
	    // Obtiene el byte[] del contenido del fichero
	    final byte[] fileContent = getFileContent(data);

	    // Invoca al servlet de descarga
	    if (fileContent != null) {
		request.getSession().setAttribute(DownloadByteArrServlet.FILENAME, (StringUtils.isBlank(fileName) ? "file" : fileName) + "." + exporterType.getFileExtension());
		request.getSession().setAttribute(DownloadByteArrServlet.CONTENTTYPE, exporterType.getMimeType());
		request.getSession().setAttribute(DownloadByteArrServlet.FILECONTENT, fileContent);
		response.sendRedirect(request.getSession().getServletContext().getContextPath() + "/DownloadByteArrServlet");
	    } else {
		logger.error("Export failed: fileContent is null");
	    }
	} catch (final IOException exception) {
	    throw new RuntimeException(exception);
	}

    }

    protected abstract byte[] getFileContent(DataExporter data);
}
