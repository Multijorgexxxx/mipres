package com.mipres.reporting;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.mipres.utils.ExportResponseFactory;

@Service
public class ReportingExportService {

    private final ReportRegistry reportRegistry;
    private final ExportResponseFactory exportResponseFactory;

    public ReportingExportService(ReportRegistry reportRegistry, ExportResponseFactory exportResponseFactory) {
        this.reportRegistry = reportRegistry;
        this.exportResponseFactory = exportResponseFactory;
    }

    public ResponseEntity<StreamingResponseBody> export(String reportId, String format) {
        ReportDefinition def = reportRegistry.get(reportId);
        if (def == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return exportResponseFactory.build(def.getBaseName(), format, outputStream -> {
            try {
                def.getExporter().export(format, outputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
