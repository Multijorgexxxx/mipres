package com.mipres.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mipres.entity.DireccionamientosPendientesEntrega;
import com.mipres.entity.DireccionamientosPendientesProgramacion;

public interface DireccionamientoQueryService {

    public List<DireccionamientosPendientesEntrega> listarPendientesEntrega();
    public List<DireccionamientosPendientesProgramacion> listarPendientesProgramacion();

    public Page<DireccionamientosPendientesEntrega> listarPendientesEntrega(Pageable pageable);
    public Page<DireccionamientosPendientesProgramacion> listarPendientesProgramacion(Pageable pageable);

}
