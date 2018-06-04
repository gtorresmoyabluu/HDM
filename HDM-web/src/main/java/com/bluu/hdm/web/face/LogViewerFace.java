package com.bluu.hdm.web.face;

import com.bluu.hdm.web.enums.LogSeverity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.Visibility;
import com.bluu.hdm.web.enums.ViewTypeEnum;
import com.bluu.hdm.web.filter.LogViewerFilter;
import com.bluu.hdm.web.model.APILazyDataModel;
import com.bluu.hdm.web.pojo.Log;
import static com.bluu.hdm.web.servlet.DownloadByteArrServlet.CONTENTTYPE;
import static com.bluu.hdm.web.servlet.DownloadByteArrServlet.FILECONTENT;
import static com.bluu.hdm.web.servlet.DownloadByteArrServlet.FILENAME;
import com.bluu.hdm.web.util.MessageUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.text.ParseException;
import javax.faces.application.FacesMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;

@ViewScoped
@Named(LogViewerFace.BEAN_NAME)
public class LogViewerFace implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String BEAN_NAME = "logViewer";
    private transient Logger logger;
    private ObjectMapper mapper;

    private LogViewerFilter currentFilter;
    private Log currentItem;

    private LazyDataModel<Object> items;
    private List<Boolean> toggleableColumns;
    private ViewTypeEnum viewType;

    @PostConstruct
    public void initData() {
        if (mapper == null) {
            mapper = new ObjectMapper();
        }
        doRefresh();
        toggleableColumns = Arrays.asList(true, true, true, true, true);
    }

    public void doChangeSearch() {
        if (viewType != ViewTypeEnum.search) {
            if (currentFilter == null) {
                currentFilter = new LogViewerFilter();
                currentFilter.setSeverity(LogSeverity.INFO);
                currentFilter.setLines(30);
            }

            viewType = ViewTypeEnum.search;
        } else {
            viewType = ViewTypeEnum.list;
        }
    }

    public void doDownload() {
        try {
            // Obtiene el byte array del contenido del fichero
            byte[] fileContent = null;//adminBean.getLogContent();

            // Invoca al servlet de descarga
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            request.getSession().setAttribute(FILENAME, "schaman.log");
            request.getSession().setAttribute(CONTENTTYPE, "text/plain");
            request.getSession().setAttribute(FILECONTENT, fileContent);
            response.sendRedirect(request.getSession().getServletContext().getContextPath() + "/DownloadByteArrServlet");
        } catch (IOException e) {
            logger.error(e.getMessage());
            MessageUtils.addMessage(FacesMessage.SEVERITY_ERROR, "logviewer_error");
        }
    }

    public void doOnColumnToggle(ToggleEvent event) {
        toggleableColumns.set((Integer) event.getData(), event.getVisibility() == Visibility.VISIBLE);
    }

    public void doRefresh() {
        doRefreshWOFilter();
        currentFilter = new LogViewerFilter();
        currentFilter.setSeverity(LogSeverity.INFO);
        currentFilter.setLines(30);
    }

    public void doRefreshWOFilter() {
        items = null;
        currentItem = null;
        doSetViewTypeList();
    }

    public void doSearch() {
        items = null;

        if (currentFilter == null) {
            currentFilter = new LogViewerFilter();
            currentFilter.setSeverity(LogSeverity.INFO);
            currentFilter.setLines(30);
        }
    }

    public void doSetViewTypeList() {
        viewType = ViewTypeEnum.list;
    }

    public List<SelectItem> getAvailableSeverities() {

        List<SelectItem> result = new ArrayList<>();
        for (LogSeverity severity : LogSeverity.values()) {
            result.add(new SelectItem(severity, severity.getLabel()));
        }

        return result;
    }

    public LogViewerFilter getCurrentFilter() {
        return currentFilter;
    }

    public Log getCurrentItem() {
        return currentItem;
    }

    public boolean getIsItemsFiltered() {
        return currentFilter != null && currentFilter.isFilled();
    }

    public LazyDataModel<Object> getItems() throws ParseException {
        if (items == null) {
            if (items == null) {
                items = new APILazyDataModel(Log.class, "logviewer/all", BEAN_NAME);
            }
        }
        return items;
    }

    public List<Boolean> getToggleableColumns() {
        return toggleableColumns;
    }

    public ViewTypeEnum getViewType() {
        return viewType;
    }
    
    public void setCurrentFilter(LogViewerFilter currentFilter) {
        this.currentFilter = currentFilter;
    }

    public void setCurrentItem(Log currentItem) {
        this.currentItem = currentItem;
    }

    public void setViewType(ViewTypeEnum viewType) {
        this.viewType = viewType;
    }
}
