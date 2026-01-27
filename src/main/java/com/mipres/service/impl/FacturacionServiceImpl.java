package com.mipres.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.mipres.dto.FacturacionDTO;
import com.mipres.entity.Facturacion;
import com.mipres.mapper.FacturacionMapper;
import com.mipres.repository.FacturacionRepository;
import com.mipres.service.FacturacionService;
import com.mipres.service.GenerarTokenService;
    
@Service
public class FacturacionServiceImpl implements FacturacionService {

    @Value("${spring.sispro.sispro-nit}")
    private String nit;

    private final WebClient webClient;

    private final GenerarTokenService generarTokenService;
    
    private final FacturacionRepository facturacionRepository;
    private final FacturacionMapper facturacionMapper;

    public FacturacionServiceImpl(@Qualifier("facWebClient") WebClient webClient, GenerarTokenService generarTokenService, FacturacionRepository facturacionRepository, FacturacionMapper facturacionMapper) {
        this.webClient = webClient;
        this.generarTokenService = generarTokenService;
        this.facturacionRepository = facturacionRepository;
        this.facturacionMapper = facturacionMapper;
    }

    @Override
    public List<FacturacionDTO> getFacturacionByFecha(String fecha) {

        String token = generarTokenService.getToken();

        return webClient.get()
                .uri("/FacturacionXFecha/{nit}/{token}/{fecha}", nit, token, fecha)
                .retrieve()
                .bodyToFlux(FacturacionDTO.class)
                .collectList()
                .block();
    }

    @Override
    public List<FacturacionDTO> getFacturacionByPrescripcion(String numPrescripcion) {

        String token = generarTokenService.getToken();

        return webClient.get()
                .uri("/FacturacionXPrescripcion/{nit}/{token}/{numPrescription}", nit, token, numPrescripcion)
                .retrieve()
                .bodyToFlux(FacturacionDTO.class)
                .collectList()
                .block();
    }
    
    @Override
    public FacturacionDTO crearFacturacion(FacturacionDTO facturacion) {
        Facturacion saved = facturacionRepository.save(facturacionMapper.toEntity(facturacion));
        return facturacionMapper.toDto(saved);
    }
    
    @Override
    public List<FacturacionDTO> crearFacturaciones(List<FacturacionDTO> facturaciones) {
        List<Facturacion> saved = facturacionRepository.saveAll(facturacionMapper.toEntityList(facturaciones));
        return facturacionMapper.toDtoList(saved);
    }
    
    @Override
    public FacturacionDTO actualizarFacturacion(FacturacionDTO facturacion) {
        Facturacion existing = facturacionRepository.findById(facturacion.getId())
                .orElseThrow(() -> new RuntimeException("Facturacion not found"));
        Facturacion updated = facturacionMapper.toEntity(facturacion);
        updated.setId(existing.getId());
        return facturacionMapper.toDto(facturacionRepository.save(updated));
    }
}
