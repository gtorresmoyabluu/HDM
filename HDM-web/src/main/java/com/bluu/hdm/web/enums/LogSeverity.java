package com.bluu.hdm.web.enums;

public enum LogSeverity {

    INFO("Info"),
    WARN("Warning"),
    ERROR("Error"),
    FATAL("Fatal"),
    DEBUG("Debug");

    private String label;

    LogSeverity(String label) {
        this.label = label;
    }

    public String getName() {
        return name();
    }

    public String getLabel() {
        return label;
    }

}
