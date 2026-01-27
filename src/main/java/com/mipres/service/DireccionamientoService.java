package com.mipres.service;

import java.util.List;

import com.mipres.dto.DireccionamientoDTO;

public interface DireccionamientoService {

    public List<DireccionamientoDTO> getDireccionamientoByFecha(String date);

    public List<DireccionamientoDTO> getDireccionamientoByPrescripcion(String numPrescripcion);

    public DireccionamientoDTO crearDireccionamiento(DireccionamientoDTO direccionamiento);

    public List<DireccionamientoDTO> crearDireccionamientos(List<DireccionamientoDTO> direccionamientos);

    public DireccionamientoDTO actualizarDireccionamiento(DireccionamientoDTO direccionamiento);

}
