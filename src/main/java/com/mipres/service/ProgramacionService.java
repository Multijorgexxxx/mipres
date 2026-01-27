package com.mipres.service;

import java.util.List;

import com.mipres.dto.ProgramacionDTO;

public interface ProgramacionService {

    public List<ProgramacionDTO> getProgramacionByFecha(String date);

    public List<ProgramacionDTO> getProgramacionByPrescripcion(String numPrescripcion);

    public ProgramacionDTO crearProgramacion(ProgramacionDTO programacion);

    public List<ProgramacionDTO> crearProgramaciones(List<ProgramacionDTO> programaciones);

    public ProgramacionDTO actualizarProgramacion(ProgramacionDTO programacion);

}
