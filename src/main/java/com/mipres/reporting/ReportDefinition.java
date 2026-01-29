package com.mipres.reporting;

public class ReportDefinition {

    private final String id;
    private final String baseName;
    private final ReportExporter exporter;

    public ReportDefinition(String id, String baseName, ReportExporter exporter) {
        this.id = id;
        this.baseName = baseName;
        this.exporter = exporter;
    }

    public String getId() {
        return id;
    }

    public String getBaseName() {
        return baseName;
    }

    public ReportExporter getExporter() {
        return exporter;
    }
}
