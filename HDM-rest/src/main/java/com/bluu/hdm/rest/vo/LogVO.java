package com.bluu.hdm.rest.vo;

import com.bluu.hdm.rest.enums.LogSeverityEnum;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;

public class LogVO {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss','S");

    private Long id;
    private LogSeverityEnum severity;
    private Date timestamp;
    private String clazz;
    private String message;
    private String tipoLog;

    public LogVO() {
    }

    public LogVO(LogSeverityEnum severity, String timestamp, String clazz, String message, Long id, String tipoLog) {

        this.severity = severity;
        try {
            this.timestamp = StringUtils.isNotBlank(timestamp) ? sdf.parse(timestamp) : null;
        } catch (ParseException ex) {
            Logger.getLogger(LogVO.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.clazz = clazz;
        this.message = message;
        this.id = id;
        this.tipoLog = tipoLog;

    }

    public LogVO(LogSeverityEnum severity, String timestamp, String clazz, String message, Long id) {
        this.severity = severity;
        try {
            this.timestamp = StringUtils.isNotBlank(timestamp) ? sdf.parse(timestamp) : null;
        } catch (ParseException ex) {
            Logger.getLogger(LogVO.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.clazz = clazz;
        this.message = message;
        this.id = id;
    }
    /**
     * @return the severity
     */
    public LogSeverityEnum getSeverity() {
        return severity;
    }

    /**
     * @param severity the severity to set
     */
    public void setSeverity(LogSeverityEnum severity) {
        this.severity = severity;
    }

    /**
     * @return the timestamp
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return the clazz
     */
    public String getClazz() {
        return clazz;
    }

    /**
     * @param clazz the clazz to set
     */
    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
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
