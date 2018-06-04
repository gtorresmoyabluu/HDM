package com.bluu.hdm.rest.enums;

public enum LogSeverityEnum {

    INFO("Info"),
    WARN("Warning"),
    ERROR("Error"),
    FATAL("Fatal"),
    DEBUG("Debug"),
    DEPLOY("deploy");

    private String label;

    LogSeverityEnum(String label) {
        this.label = label;
    }

    public String getName() {
        return name();
    }

    public String getLabel() {
        return label;
    }

}
