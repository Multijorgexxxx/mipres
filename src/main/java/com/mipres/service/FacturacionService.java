package com.mipres.service;

import java.util.List;

import com.mipres.dto.FacturacionDTO;

public interface FacturacionService {

    public List<FacturacionDTO> getFacturacionByFecha(String date);

    public List<FacturacionDTO> getFacturacionByPrescripcion(String numPrescripcion);

    public FacturacionDTO crearFacturacion(FacturacionDTO facturacion);

    public List<FacturacionDTO> crearFacturaciones(List<FacturacionDTO> facturaciones);

    public FacturacionDTO actualizarFacturacion(FacturacionDTO facturacion);

}
