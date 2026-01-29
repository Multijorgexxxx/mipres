package com.mipres.reporting;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.mipres.service.DireccionamientosExportService;

@Component
public class ReportRegistry {

    private final Map<String, ReportDefinition> reports;

    public ReportRegistry(DireccionamientosExportService direccionamientosExportService) {
        Map<String, ReportDefinition> map = new LinkedHashMap<>();

        map.put("pendientes_programacion",
                new ReportDefinition(
                        "pendientes_programacion",
                        "pendientes_programacion",
                        (format, outputStream) -> direccionamientosExportService.exportPendientesProgramacion(format,
                                outputStream)));

        map.put("pendientes_entrega",
                new ReportDefinition(
                        "pendientes_entrega",
                        "pendientes_entrega",
                        (format, outputStream) -> direccionamientosExportService.exportPendientesEntrega(format, outputStream)));

        this.reports = Collections.unmodifiableMap(map);
    }

    public ReportDefinition get(String reportId) {
        return reports.get(reportId);
    }

    public Set<String> ids() {
        return reports.keySet();
    }
}
