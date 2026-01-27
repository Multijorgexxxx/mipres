package com.mipres.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.mipres.dto.ReporteEntregaDTO;
import com.mipres.entity.ReporteEntrega;
import com.mipres.mapper.ReporteEntregaMapper;
import com.mipres.repository.ReporteEntregaRepository;
import com.mipres.service.GenerarTokenService;
import com.mipres.service.ReporteEntregaService;
    
@Service
public class ReporteEntregaServiceImpl implements ReporteEntregaService {

    @Value("${spring.sispro.sispro-nit}")
    private String nit;

    private final WebClient webClient;

    private final GenerarTokenService generarTokenService;
    
    private final ReporteEntregaRepository reporteEntregaRepository;
    private final ReporteEntregaMapper reporteEntregaMapper;

    public ReporteEntregaServiceImpl(@Qualifier("sisproWebClient") WebClient webClient, GenerarTokenService generarTokenService, ReporteEntregaRepository reporteEntregaRepository, ReporteEntregaMapper reporteEntregaMapper) {
        this.webClient = webClient;
        this.generarTokenService = generarTokenService;
        this.reporteEntregaRepository = reporteEntregaRepository;
        this.reporteEntregaMapper = reporteEntregaMapper;
    }

    @Override
    public List<ReporteEntregaDTO> getReporteEntregaByFecha(String fecha) {

        String token = generarTokenService.getToken();

        return webClient.get()
                .uri("/ReporteEntregaXFecha/{nit}/{token}/{fecha}", nit, token, fecha)
                .retrieve()
                .bodyToFlux(ReporteEntregaDTO.class)
                .collectList()
                .block();
    }

    @Override
    public List<ReporteEntregaDTO> getReporteEntregaByPrescripcion(String numPrescripcion) {

        String token = generarTokenService.getToken();

        return webClient.get()
                .uri("/ReporteEntregaXPrescripcion/{nit}/{token}/{numPrescription}", nit, token, numPrescripcion)
                .retrieve()
                .bodyToFlux(ReporteEntregaDTO.class)
                .collectList()
                .block();
    }
    
    @Override
    public ReporteEntregaDTO crearReporteEntrega(ReporteEntregaDTO entrega) {
        ReporteEntrega saved = reporteEntregaRepository.save(reporteEntregaMapper.toEntity(entrega));
        return reporteEntregaMapper.toDto(saved);
    }
    
    @Override
    public List<ReporteEntregaDTO> crearReporteEntregas(List<ReporteEntregaDTO> entregas) {
        List<ReporteEntrega> saved = reporteEntregaRepository.saveAll(reporteEntregaMapper.toEntityList(entregas));
        return reporteEntregaMapper.toDtoList(saved);
    }
    
    @Override
    public ReporteEntregaDTO actualizarReporteEntrega(ReporteEntregaDTO entrega) {
        ReporteEntrega existing = reporteEntregaRepository.findById(entrega.getId())
                .orElseThrow(() -> new RuntimeException("Entrega not found"));
        ReporteEntrega updated = reporteEntregaMapper.toEntity(entrega);
        updated.setId(existing.getId());
        return reporteEntregaMapper.toDto(reporteEntregaRepository.save(updated));
    }

}
