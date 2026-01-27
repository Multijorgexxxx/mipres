package com.mipres.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mipres.dto.ReporteEntregaDTO;
import com.mipres.entity.ReporteEntrega;
import com.mipres.response.MipresResponse;

@Component
public class ReporteEntregaMapper {

    public List<ReporteEntrega> toEntityList(List<ReporteEntregaDTO> dtos) {
        if (dtos == null) {
            return List.of();    
        }

        return dtos.stream()
                .map(this::toEntity)
                .toList();
    }

    public List<ReporteEntregaDTO> toDtoList(List<ReporteEntrega> entities) {
        if (entities == null) {
            return List.of();
        }
        return entities.stream()
                .map(this::toDto)
                .toList();
    }

    public ReporteEntrega toEntity(ReporteEntregaDTO dto) {
        if (dto == null) return null;

        ReporteEntrega entity = new ReporteEntrega();
        
        entity.setId(dto.getId());
        entity.setIdReporteEntrega(dto.getIdReporteEntrega());
        entity.setEstadoEntrega(dto.getEstadoEntrega());
        entity.setValorEntregado(dto.getValorEntregado());
        entity.setFecEntrega(dto.getFecEntrega());
        entity.setFecRepEntrega(dto.getFecRepEntrega());
        entity.setEstRepEntrega(dto.getEstRepEntrega());
        entity.setFecAnulacion(dto.getFecAnulacion());

        return entity;
    }

    public ReporteEntregaDTO toDto(ReporteEntrega entity) {
        if (entity == null) return null;

        ReporteEntregaDTO dto = new ReporteEntregaDTO();
        dto.setId(entity.getId());
        dto.setIdReporteEntrega(entity.getIdReporteEntrega());
        dto.setEstadoEntrega(entity.getEstadoEntrega());
        dto.setValorEntregado(entity.getValorEntregado());
        dto.setFecEntrega(entity.getFecEntrega());
        dto.setFecRepEntrega(entity.getFecRepEntrega());
        dto.setEstRepEntrega(entity.getEstRepEntrega());
        dto.setFecAnulacion(entity.getFecAnulacion());
        
        return dto;
    }

    public MipresResponse toResponse(ReporteEntrega entity) {
        return MipresResponse.builder()
                .id(entity.getId())
                .idTransaccion(entity.getIdReporteEntrega())
                .mensaje("Procesado exitosamente")
                .build();
    }

}
