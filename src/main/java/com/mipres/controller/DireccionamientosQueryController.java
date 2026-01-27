package com.mipres.controller;

import java.util.List;

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

    @GetMapping("/pendientes-programacion")
    public ResponseEntity<List<DireccionamientosPendientesProgramacion>> getPendientesProgramacion() {
        List<DireccionamientosPendientesProgramacion> datos = queryService.listarPendientesProgramacion();
        if (datos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(datos);
    }

}
