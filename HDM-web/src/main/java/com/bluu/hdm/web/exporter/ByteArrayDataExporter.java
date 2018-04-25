package com.bluu.hdm.web.exporter;

public class ByteArrayDataExporter implements DataExporter {

    private byte[] bytes;

    public ByteArrayDataExporter(byte[] bytes) {
	this.bytes = bytes;
    }

    public byte[] getBytes() {
	return bytes;
    }

    public void setBytes(byte[] bytes) {
	this.bytes = bytes;
    }
}
