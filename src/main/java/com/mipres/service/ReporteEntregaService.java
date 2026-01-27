package com.mipres.service;

import java.util.List;

import com.mipres.dto.ReporteEntregaDTO;

public interface ReporteEntregaService {

    public List<ReporteEntregaDTO> getReporteEntregaByFecha(String date);

    public List<ReporteEntregaDTO> getReporteEntregaByPrescripcion(String numPrescripcion);

    public ReporteEntregaDTO crearReporteEntrega(ReporteEntregaDTO reporteEntrega);

    public List<ReporteEntregaDTO> crearReporteEntregas(List<ReporteEntregaDTO> reporteEntregas);

    public ReporteEntregaDTO actualizarReporteEntrega(ReporteEntregaDTO reporteEntrega);

}
