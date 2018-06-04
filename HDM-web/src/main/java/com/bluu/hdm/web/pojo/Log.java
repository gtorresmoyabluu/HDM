package com.bluu.hdm.web.pojo;

import com.bluu.hdm.web.enums.LogSeverity;
import com.bluu.hdm.web.util.CustomDateDeserializer;
import com.bluu.hdm.web.util.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss','S");
    private Long id;
    private LogSeverity severity;
    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date timestamp;
    private String clazz;
    private String message;
    private String tipoLog;  

    public Log(){        
    }    
    
    public Log(Log log) {	
        severity = log.getSeverity();
        timestamp = log.getTimestamp();
        clazz = log.getClazz();
        message = log.getMessage();
        tipoLog = log.getTipoLog();
    }

    public LogSeverity getSeverity() {
	return severity;
    }

    public Date getTimestamp() {
	return timestamp;
    }

    public String getMessage() {
	return message != null ? message.replace("\n", "<br/>") : null;
    }

    public String getClazz() {
	return clazz;
    }

    public String getFirstLineOfMessage() {
	return message != null ? (getIsTrace() ? message.substring(0, message.indexOf("\n")) : message) : null;
    }

    public boolean getIsTrace() {
	return message != null && message.contains("\n");
    }

    public void setSeverity(LogSeverity severity) {
	this.severity = severity;
    }

    public void setTimestamp(Date timestamp) {
	this.timestamp = timestamp;
    }

    public void setClazz(String clazz) {
	this.clazz = clazz;
    }

    public void setMessage(String message) {
	this.message = message;
    }
    
    public String getTimestampToString() {
	return timestamp != null ? sdf.format(timestamp) : null;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoLog() {
        return tipoLog;
    }

    public void setTipoLog(String tipoLog) {
        
        this.tipoLog = tipoLog;
    }    
}
