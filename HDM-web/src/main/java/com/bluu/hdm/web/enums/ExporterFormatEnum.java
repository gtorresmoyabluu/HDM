package com.bluu.hdm.web.enums;

public enum ExporterFormatEnum {

    csv("text/csv", "csv"), zip("application/zip", "zip");

    private String mimeType;
    private String fileExtension;

    ExporterFormatEnum(String mimeType, String fileExtension) {
	this.mimeType = mimeType;
	this.fileExtension = fileExtension;
    }

    public String getMimeType() {
	return mimeType;
    }

    public String getFileExtension() {
	return fileExtension;
    }
}
