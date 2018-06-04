package com.bluu.hdm.web.filter;

import com.bluu.hdm.web.enums.FilterOperationEnum;
import com.bluu.hdm.web.enums.LogSeverity;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;



public class LogViewerFilter implements Filters {

    private Integer lines;
    private String message;
    private LogSeverity severity;

    @Override
    public List<FilterField> getFilters() {
	final List<FilterField> filters = new ArrayList<>();
	if (StringUtils.isNotBlank(message)) {
	    filters.add(new FilterField("message", FilterOperationEnum.LIKE, message));
	}
	if (lines != null) {
	    filters.add(new FilterField("lines", FilterOperationEnum.EQ, lines));
	}
	if (severity != null) {
	    filters.add(new FilterField("severity", FilterOperationEnum.EQ, severity));
	}

	return filters;
    }

    @Override
    public boolean isFilled() {
	return lines != null || StringUtils.isNotBlank(message) || severity != null;
    }

    public Integer getLines() {
	return lines;
    }

    public String getMessage() {
	return message;
    }

    public LogSeverity getSeverity() {
	return severity;
    }

    public void setLines(Integer lines) {
	this.lines = lines;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    public void setSeverity(LogSeverity severity) {
	this.severity = severity;
    }
}
