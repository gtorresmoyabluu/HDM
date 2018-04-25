package com.bluu.hdm.web.exporter;

import java.util.List;

public class TabularDataExporter implements DataExporter {

    private List<List<String>> data;
    private List<String> headers;

    public TabularDataExporter(List<String> headers, List<List<String>> data) {
        this.headers = headers;
        this.data = data;
    }

    public List<List<String>> getData() {
        return data;
    }

    public List<String> getHeaders() {
        return headers;
    }

    public void setData(List<List<String>> data) {
        this.data = data;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }
}
