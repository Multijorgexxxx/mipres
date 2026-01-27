package com.mipres.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.mipres.dto.EntregaDTO;
import com.mipres.entity.Entrega;
import com.mipres.response.MipresResponse;

@Component
public class EntregaMapper {


    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public List<Entrega> toEntityList(List<EntregaDTO> dtos) {
        if (dtos == null) {
            return null;
        }

        List<Entrega> saveds = dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());

        return saveds;
    }

    public List<EntregaDTO> toDtoList(List<Entrega> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Entrega toEntity(EntregaDTO dto) {
        if (dto == null) return null;

        Entrega entity = new Entrega();
        entity.setId(dto.getId());
        entity.setIdEntrega(dto.getIdEntrega());
        entity.setCodSerTecEntregado(dto.getCodSerTecEntregado());
        entity.setCantTotEntregada(dto.getCantTotEntregada());
        entity.setEntTotal(dto.getEntTotal());

        entity.setCausaNoEntrega(dto.getCausaNoEntrega());

        if (dto.getFecEntrega() != null && !dto.getFecEntrega().isEmpty()) {
            entity.setFecEntrega(LocalDateTime.parse(dto.getFecEntrega(), dateFormatter));
        }

        entity.setNoLote(dto.getNoLote());
        entity.setTipoIDRecibe(dto.getTipoIdRecibe());
        entity.setNoIDRecibe(dto.getNoIdRecibe());
        entity.setEstEntrega(dto.getEstEntrega());
        entity.setFecAnulacion(dto.getFecAnulacion());
        entity.setCodigosEntrega(dto.getCodigosEntrega());

        return entity;
    }

    public EntregaDTO toDto(Entrega entity) {
        if (entity == null) return null;

        EntregaDTO dto = new EntregaDTO();
        dto.setId(entity.getId());
        dto.setIdEntrega(entity.getIdEntrega());
        dto.setCodSerTecEntregado(entity.getCodSerTecEntregado());
        dto.setCantTotEntregada(entity.getCantTotEntregada());
        dto.setEntTotal(entity.getEntTotal());

        dto.setCausaNoEntrega(entity.getCausaNoEntrega());
        
        if (entity.getFecEntrega() != null) {
            dto.setFecEntrega(entity.getFecEntrega().format(dateFormatter));
        }

        dto.setNoLote(entity.getNoLote());
        dto.setTipoIdRecibe(entity.getTipoIDRecibe());
        dto.setNoIdRecibe(entity.getNoIDRecibe());
        dto.setEstEntrega(entity.getEstEntrega());
        dto.setFecAnulacion(entity.getFecAnulacion());
        dto.setCodigosEntrega(entity.getCodigosEntrega());

        return dto;
    }

    public MipresResponse toResponse(Entrega entity) {
        return MipresResponse.builder()
                .id(entity.getId())
                .idTransaccion(entity.getIdEntrega())
                .mensaje("Procesado exitosamente")
                .build();
    }

}
