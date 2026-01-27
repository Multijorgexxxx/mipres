package com.mipres.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.mipres.dto.EntregaDTO;
import com.mipres.entity.Entrega;
import com.mipres.mapper.EntregaMapper;
import com.mipres.repository.EntregaRepository;
import com.mipres.service.EntregaService;
import com.mipres.service.GenerarTokenService;

@Service
public class EntregaServiceImpl implements EntregaService {

    @Value("${spring.sispro.sispro-nit}")
    private String nit;


    private final WebClient webClient;

    private final GenerarTokenService generarTokenService;
    
    private final EntregaRepository entregaRepository;

    private final EntregaMapper entregaMapper;

    public EntregaServiceImpl(@Qualifier("sisproWebClient") WebClient webClient, GenerarTokenService generarTokenService, EntregaRepository entregaRepository, EntregaMapper entregaMapper) {
        this.webClient = webClient;
        this.generarTokenService = generarTokenService;
        this.entregaRepository = entregaRepository;
        this.entregaMapper = entregaMapper;
    }

    @Override
    public List<EntregaDTO> getEntregaByFecha(String fecha) {

        String token = generarTokenService.getToken();

        return webClient.get()
                .uri("/EntregaXFecha/{nit}/{token}/{fecha}", nit, token, fecha)
                .retrieve()
                .bodyToFlux(EntregaDTO.class)
                .collectList()
                .block();
    }

    @Override
    public List<EntregaDTO> getEntregaByPrescripcion(String numPrescripcion) {

        String token = generarTokenService.getToken();

        return webClient.get()
                .uri("/EntregaXPrescripcion/{nit}/{token}/{numPrescription}", nit, token, numPrescripcion)
                .retrieve()
                .bodyToFlux(EntregaDTO.class)
                .collectList()
                .block();
    }
    
    @Override
    public EntregaDTO crearEntrega(EntregaDTO entrega) {
        Entrega saved = entregaRepository.save(entregaMapper.toEntity(entrega));
        return entregaMapper.toDto(saved);
    }
    
    @Override
    public List<EntregaDTO> crearEntregas(List<EntregaDTO> entregas) {
        List<Entrega> saved = entregaRepository.saveAll(entregaMapper.toEntityList(entregas));
        return entregaMapper.toDtoList(saved);
    }
    
    @Override
    public EntregaDTO actualizarEntrega(EntregaDTO entrega) {
        Entrega existing = entregaRepository.findById(entrega.getId())
                .orElseThrow(() -> new RuntimeException("Entrega not found"));
        Entrega updated = entregaMapper.toEntity(entrega);
        updated.setId(existing.getId());
        return entregaMapper.toDto(entregaRepository.save(updated));
    }
}
