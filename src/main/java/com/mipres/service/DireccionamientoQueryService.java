package com.mipres.service;

import java.util.List;

import com.mipres.entity.DireccionamientosPendientesEntrega;
import com.mipres.entity.DireccionamientosPendientesProgramacion;

public interface DireccionamientoQueryService {

    public List<DireccionamientosPendientesEntrega> listarPendientesEntrega();
    public List<DireccionamientosPendientesProgramacion> listarPendientesProgramacion();

}
