package com.mipres.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.mipres.entity.DireccionamientosPendientesEntrega;
import com.mipres.entity.DireccionamientosPendientesProgramacion;
import com.mipres.reporting.ReportingExportService;
import com.mipres.service.DireccionamientoQueryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/direccionamientos")
@RequiredArgsConstructor
public class DireccionamientosQueryController {

    private final DireccionamientoQueryService queryService;
    private final ReportingExportService reportingExportService;

    @GetMapping("/pendientes-entrega")
    public ResponseEntity<List<DireccionamientosPendientesEntrega>> getPendientesEntrega() {
        List<DireccionamientosPendientesEntrega> datos = queryService.listarPendientesEntrega();
        if (datos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(datos);
    }

    @GetMapping("/pendientes-entrega/paged")
    public ResponseEntity<Page<DireccionamientosPendientesEntrega>> getPendientesEntregaPaged(Pageable pageable) {
        Page<DireccionamientosPendientesEntrega> datos = queryService.listarPendientesEntrega(pageable);
        if (datos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(datos);
    }

    @GetMapping("/pendientes-programacion")
    public ResponseEntity<List<DireccionamientosPendientesProgramacion>> getPendientesProgramacion() {
        List<DireccionamientosPendientesProgramacion> datos = queryService.listarPendientesProgramacion();
        if (datos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(datos);
    }

    @GetMapping("/pendientes-programacion/paged")
    public ResponseEntity<Page<DireccionamientosPendientesProgramacion>> getPendientesProgramacionPaged(Pageable pageable) {
        Page<DireccionamientosPendientesProgramacion> datos = queryService.listarPendientesProgramacion(pageable);
        if (datos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(datos);
    }

    @GetMapping("/pendientes-programacion/export")
    public ResponseEntity<StreamingResponseBody> exportPendientesProgramacion(
            @RequestParam(name = "format", defaultValue = "csv") String format) {
        return reportingExportService.export("pendientes_programacion", format);
    }

    @GetMapping("/pendientes-entrega/export")
    public ResponseEntity<StreamingResponseBody> exportPendientesEntrega(
            @RequestParam(name = "format", defaultValue = "csv") String format) {
        return reportingExportService.export("pendientes_entrega", format);
    }

}
