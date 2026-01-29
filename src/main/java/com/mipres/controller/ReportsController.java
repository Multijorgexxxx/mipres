package com.mipres.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.mipres.reporting.ReportingExportService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportsController {

    private final ReportingExportService reportingExportService;

    @GetMapping("/{reportId}/export")
    public ResponseEntity<StreamingResponseBody> export(
            @PathVariable("reportId") String reportId,
            @RequestParam(name = "format", defaultValue = "csv") String format) {
        return reportingExportService.export(reportId, format);
    }
}
