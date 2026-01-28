package com.mipres.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mipres.entity.DireccionamientosPendientesEntrega;
import com.mipres.entity.DireccionamientosPendientesProgramacion;
import com.mipres.service.DireccionamientoQueryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/direccionamientos")
@RequiredArgsConstructor
public class DireccionamientosQueryController {

    private final DireccionamientoQueryService queryService;

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

}
