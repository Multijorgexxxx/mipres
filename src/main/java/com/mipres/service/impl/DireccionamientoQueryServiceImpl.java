package com.mipres.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mipres.entity.DireccionamientosPendientesEntrega;
import com.mipres.entity.DireccionamientosPendientesProgramacion;
import com.mipres.repository.DireccionamientosPendientesEntregaRepository;
import com.mipres.repository.DireccionamientosPendientesProgramacionRepository;
import com.mipres.service.DireccionamientoQueryService;

@Service
public class DireccionamientoQueryServiceImpl implements DireccionamientoQueryService {

    private final DireccionamientosPendientesEntregaRepository entregaRepository;
    private final DireccionamientosPendientesProgramacionRepository programacionRepository;

    public DireccionamientoQueryServiceImpl(
            DireccionamientosPendientesEntregaRepository entregaRepository,
            DireccionamientosPendientesProgramacionRepository programacionRepository) {
        this.entregaRepository = entregaRepository;
        this.programacionRepository = programacionRepository;
    }

    @Override
    public List<DireccionamientosPendientesEntrega> listarPendientesEntrega() {
        return entregaRepository.findAll();
    }

    @Override
    public List<DireccionamientosPendientesProgramacion> listarPendientesProgramacion() {
        return programacionRepository.findAll();
    }

}
