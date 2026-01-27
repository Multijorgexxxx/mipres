package com.mipres.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.mipres.dto.DireccionamientoDTO;
import com.mipres.entity.Direccionamiento;
import com.mipres.mapper.DireccionamientoMapper;
import com.mipres.repository.DireccionamientoRepository;
import com.mipres.service.DireccionamientoService;
import com.mipres.service.GenerarTokenService;

@Service
public class DireccionamientoServiceImpl implements DireccionamientoService {

    @Value("${spring.sispro.sispro-nit}")
    private String nit;

    private final WebClient webClient;

    private final GenerarTokenService generarTokenService;
    
    private final DireccionamientoRepository direccionamientoRepository;

    private final DireccionamientoMapper direccionamientoMapper;

    public DireccionamientoServiceImpl(@Qualifier("sisproWebClient") WebClient webClient, GenerarTokenService generarTokenService, DireccionamientoRepository direccionamientoRepository, DireccionamientoMapper direccionamientoMapper) {
        this.webClient = webClient;
        this.generarTokenService = generarTokenService;
        this.direccionamientoRepository = direccionamientoRepository;
        this.direccionamientoMapper = direccionamientoMapper;
    }

    @Override
    public List<DireccionamientoDTO> getDireccionamientoByFecha(String fecha) {

        String token = generarTokenService.getToken();

        return webClient.get()
                .uri("/DireccionamientoXFecha/{nit}/{token}/{fecha}", nit, token, fecha)
                .retrieve()
                .bodyToFlux(DireccionamientoDTO.class)
                .collectList()
                .block();
    }

    @Override
    public List<DireccionamientoDTO> getDireccionamientoByPrescripcion(String numPrescripcion) {

        String token = generarTokenService.getToken();

        return webClient.get()
                .uri("/DireccionamientoXPrescripcion/{nit}/{token}/{numPrescription}", nit, token, numPrescripcion)
                .retrieve()
                .bodyToFlux(DireccionamientoDTO.class)
                .collectList()
                .block();
    }
    
    @Override
    public DireccionamientoDTO crearDireccionamiento(DireccionamientoDTO direccionamiento) {
        Direccionamiento saved = direccionamientoRepository.save(direccionamientoMapper.toEntity(direccionamiento));
        return direccionamientoMapper.toDto(saved);
    }
    
    @Override
    public List<DireccionamientoDTO> crearDireccionamientos(List<DireccionamientoDTO> direccionamientos) {
        List<Direccionamiento> saved = direccionamientoRepository.saveAll(direccionamientoMapper.toEntityList(direccionamientos));
        return direccionamientoMapper.toDtoList(saved);
    }
    
    @Override
    public DireccionamientoDTO actualizarDireccionamiento(DireccionamientoDTO direccionamiento) {
        Direccionamiento existing = direccionamientoRepository.findById(direccionamiento.getId())
                .orElseThrow(() -> new RuntimeException("Direccionamiento not found"));
        Direccionamiento updated = direccionamientoMapper.toEntity(direccionamiento);
        updated.setId(existing.getId());
        return direccionamientoMapper.toDto(direccionamientoRepository.save(updated));
    }

}
