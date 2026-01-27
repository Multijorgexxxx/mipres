package com.mipres.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.mipres.dto.ProgramacionDTO;
import com.mipres.entity.Programacion;
import com.mipres.mapper.ProgramacionMapper;
import com.mipres.repository.ProgramacionRepository;
import com.mipres.service.GenerarTokenService;
import com.mipres.service.ProgramacionService;

@Service
public class ProgramacionServiceImpl implements ProgramacionService {

    @Value("${spring.sispro.sispro-nit}")
    private String nit;

    private final WebClient webClient;

    private final GenerarTokenService generarTokenService;
    
    private final ProgramacionRepository programacionRepository;
    
    private final ProgramacionMapper programacionMapper;

    public ProgramacionServiceImpl(@Qualifier("sisproWebClient") WebClient webClient, GenerarTokenService generarTokenService, ProgramacionRepository programacionRepository, ProgramacionMapper programacionMapper) {
        this.webClient = webClient;
        this.generarTokenService = generarTokenService;
        this.programacionRepository = programacionRepository;
        this.programacionMapper = programacionMapper;
    }

    @Override
    public List<ProgramacionDTO> getProgramacionByFecha(String fecha) {

        String token = generarTokenService.getToken();

        return webClient.get()
                .uri("/ProgramacionXFecha/{nit}/{token}/{fecha}", nit, token, fecha)
                .retrieve()
                .bodyToFlux(ProgramacionDTO.class)
                .collectList()
                .block();
    }

    @Override
    public List<ProgramacionDTO> getProgramacionByPrescripcion(String numPrescripcion) {

        String token = generarTokenService.getToken();

        return webClient.get()
                .uri("/ProgramacionXPrescripcion/{nit}/{token}/{numPrescription}", nit, token, numPrescripcion)
                .retrieve()
                .bodyToFlux(ProgramacionDTO.class)
                .collectList()
                .block();
    }
    
    @Override
    public ProgramacionDTO crearProgramacion(ProgramacionDTO programacion) {
        Programacion saved = programacionRepository.save(programacionMapper.toEntity(programacion));
        return programacionMapper.toDto(saved);
    }
    
    @Override
    public List<ProgramacionDTO> crearProgramaciones(List<ProgramacionDTO> programaciones) {
        List<Programacion> saved = programacionRepository.saveAll(programacionMapper.toEntityList(programaciones));
        return programacionMapper.toDtoList(saved);
    }
    
    @Override
    public ProgramacionDTO actualizarProgramacion(ProgramacionDTO programacion) {
        Programacion existing = programacionRepository.findById(programacion.getId())
                .orElseThrow(() -> new RuntimeException("Programacion not found"));
        Programacion updated = programacionMapper.toEntity(programacion);
        updated.setId(existing.getId());
        return programacionMapper.toDto(programacionRepository.save(updated));
    }

}
