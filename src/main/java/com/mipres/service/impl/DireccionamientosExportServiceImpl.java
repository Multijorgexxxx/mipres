package com.mipres.service.impl;

import java.io.IOException;
import java.io.OutputStream;

import org.springframework.stereotype.Service;

import com.mipres.entity.DireccionamientosPendientesEntrega;
import com.mipres.entity.DireccionamientosPendientesProgramacion;
import com.mipres.reporting.TabularExportEngine;
import com.mipres.repository.DireccionamientosPendientesEntregaRepository;
import com.mipres.repository.DireccionamientosPendientesProgramacionRepository;
import com.mipres.service.DireccionamientosExportService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DireccionamientosExportServiceImpl implements DireccionamientosExportService {

    private static final int DEFAULT_PAGE_SIZE = 2000;

    private final DireccionamientosPendientesProgramacionRepository programacionRepository;
    private final DireccionamientosPendientesEntregaRepository entregaRepository;
    private final TabularExportEngine tabularExportEngine;

    @Override
    public void exportPendientesProgramacion(String format, OutputStream outputStream) throws IOException {
        tabularExportEngine.export(
                format,
                outputStream,
                DireccionamientosPendientesProgramacion.class,
                pageable -> programacionRepository.findAll(pageable),
                DEFAULT_PAGE_SIZE,
                "id");
    }

    @Override
    public void exportPendientesEntrega(String format, OutputStream outputStream) throws IOException {
        tabularExportEngine.export(
                format,
                outputStream,
                DireccionamientosPendientesEntrega.class,
                pageable -> entregaRepository.findAll(pageable),
                DEFAULT_PAGE_SIZE,
                "id");
    }
}
