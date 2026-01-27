package com.mipres.service;

import java.util.List;

import com.mipres.dto.EntregaDTO;

public interface EntregaService {

    public List<EntregaDTO> getEntregaByFecha(String date);

    public List<EntregaDTO> getEntregaByPrescripcion(String numPrescripcion);

    public EntregaDTO crearEntrega(EntregaDTO entrega);

    public List<EntregaDTO> crearEntregas(List<EntregaDTO> entregas);

    public EntregaDTO actualizarEntrega(EntregaDTO entrega);

}
