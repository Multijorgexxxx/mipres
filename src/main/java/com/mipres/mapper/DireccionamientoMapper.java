package com.mipres.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.mipres.dto.DireccionamientoDTO;
import com.mipres.entity.Direccionamiento;
import com.mipres.response.MipresResponse;

@Component
public class DireccionamientoMapper {

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public List<Direccionamiento> toEntityList(List<DireccionamientoDTO> dtos) {
        if (dtos == null) {
            return null;
        }

        List<Direccionamiento> saveds = dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());

        return saveds;
    }

    public List<DireccionamientoDTO> toDtoList(List<Direccionamiento> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Direccionamiento toEntity(DireccionamientoDTO dto) {
        if (dto == null) return null;

        Direccionamiento entity = new Direccionamiento();
        
        entity.setId(dto.getId());
        entity.setIdDireccionamiento(dto.getIdDireccionamiento());
        entity.setNoPrescripcion(dto.getNoPrescripcion());
        entity.setTipoTec(dto.getTipoTec());
        entity.setConTec(dto.getConTec());
        entity.setTipoIDPaciente(dto.getTipoIdPaciente());
        entity.setNoIDPaciente(dto.getNoIdPaciente());
        entity.setNoEntrega(dto.getNoEntrega());
        entity.setNoSubEntrega(dto.getNoSubEntrega());
        entity.setTipoIDProv(dto.getTipoIdProv());
        entity.setNoIDProv(dto.getNoIdProv());
        entity.setCodMunEnt(dto.getCodMunEnt());
        
        if (dto.getFecMaxEnt() != null) {
            entity.setFecMaxEnt(LocalDate.parse(dto.getFecMaxEnt()));
        }

        if (dto.getCantTotAEntregar() != null) {
            entity.setCantTotAEntregar(String.valueOf(dto.getCantTotAEntregar()));
        }

        entity.setDirPaciente(dto.getDirPaciente());
        entity.setCodSerTecAEntregar(dto.getCodSerTecAEntregar());
        entity.setNoIDEPS(dto.getNoIdEPS());
        entity.setCodEPS(dto.getCodEPS());
        entity.setFecDireccionamiento(dto.getFecDireccionamiento());
        entity.setEstDireccionamiento(dto.getEstDireccionamiento());
        entity.setFecAnulacion(dto.getFecAnulacion());

        return entity;
    }

    public DireccionamientoDTO toDto(Direccionamiento entity) {
        if (entity == null) return null;

        DireccionamientoDTO dto = new DireccionamientoDTO();

        dto.setId(entity.getId());
        dto.setIdDireccionamiento(entity.getIdDireccionamiento());
        dto.setNoPrescripcion(entity.getNoPrescripcion());
        dto.setTipoTec(entity.getTipoTec());
        dto.setConTec(entity.getConTec());
        
        dto.setTipoIdPaciente(entity.getTipoIDPaciente());
        dto.setNoIdPaciente(entity.getNoIDPaciente());
        
        dto.setNoEntrega(entity.getNoEntrega());
        dto.setNoSubEntrega(entity.getNoSubEntrega());
        
        dto.setTipoIdProv(entity.getTipoIDProv());
        dto.setNoIdProv(entity.getNoIDProv());
        
        dto.setCodMunEnt(entity.getCodMunEnt());

        if (entity.getFecMaxEnt() != null) {
            dto.setFecMaxEnt(entity.getFecMaxEnt().format(dateFormatter));
        }

        if (entity.getCantTotAEntregar() != null) {
            try {
                dto.setCantTotAEntregar(Integer.valueOf(entity.getCantTotAEntregar()));
            } catch (NumberFormatException e) {
                dto.setCantTotAEntregar(null);
            }
        }

        dto.setDirPaciente(entity.getDirPaciente());
        dto.setCodSerTecAEntregar(entity.getCodSerTecAEntregar());
        
        dto.setNoIdEPS(entity.getNoIDEPS());
        dto.setCodEPS(entity.getCodEPS());
        
        dto.setFecDireccionamiento(entity.getFecDireccionamiento());
        dto.setEstDireccionamiento(entity.getEstDireccionamiento());
        dto.setFecAnulacion(entity.getFecAnulacion());

        return dto;
    }

    public MipresResponse toResponse(Direccionamiento entity) {
        return MipresResponse.builder()
                .id(entity.getId())
                .idTransaccion(entity.getIdDireccionamiento())
                .mensaje("Procesado exitosamente")
                .build();
    }
}
