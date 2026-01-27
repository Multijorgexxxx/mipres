package com.mipres.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.mipres.dto.ProgramacionDTO;
import com.mipres.entity.Programacion;
import com.mipres.response.MipresResponse;

@Component
public class ProgramacionMapper {

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public List<Programacion> toEntityList(List<ProgramacionDTO> dtos) {
        if (dtos == null) {
            return null;
        }

        List<Programacion> saveds = dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());

        return saveds;
    }

    public List<ProgramacionDTO> toDtoList(List<Programacion> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Programacion toEntity(ProgramacionDTO dto) {
        if (dto == null) return null;

        Programacion entity = new Programacion();
        
        entity.setId(dto.getId());
        entity.setIdProgramacion(dto.getIdProgramacion());
        entity.setTipoIDSedeProv(dto.getTipoIdSedeProv());
        entity.setNoIDSedeProv(dto.getNoIdSedeProv());
        entity.setCodSedeProv(dto.getCodSedeProv());
        entity.setCodSerTecAEntregar(dto.getCodSerTecAEntregar());
        
        if (dto.getFecMaxEnt() != null) {
            entity.setFecMaxEnt(LocalDate.parse(dto.getFecMaxEnt(), dateFormatter));
        }
        if (dto.getCantTotAEntregar() != null) {
            entity.setCantTotAEntregar(String.valueOf(dto.getCantTotAEntregar()));
        }

        entity.setFecProgramacion(dto.getFecProgramacion());
        entity.setEstProgramacion(dto.getEstProgramacion());
        entity.setFecAnulacion(dto.getFecAnulacion());

        return entity;
    }

    public ProgramacionDTO toDto(Programacion entity) {
        if (entity == null) return null;

        ProgramacionDTO dto = new ProgramacionDTO();

        dto.setId(entity.getId());
        dto.setIdProgramacion(entity.getIdProgramacion());
        
        dto.setTipoIdSedeProv(entity.getTipoIDSedeProv());
        dto.setNoIdSedeProv(entity.getNoIDSedeProv());
        dto.setCodSedeProv(entity.getCodSedeProv());
        
        dto.setFecMaxEnt(entity.getFecMaxEnt().format(dateFormatter));

        if (entity.getCantTotAEntregar() != null) {
            try {
                dto.setCantTotAEntregar(Integer.valueOf(entity.getCantTotAEntregar()));
            } catch (NumberFormatException e) {
                dto.setCantTotAEntregar(null);
            }
        }

        dto.setCodSerTecAEntregar(entity.getCodSerTecAEntregar());
        dto.setFecProgramacion(entity.getFecProgramacion());
        dto.setEstProgramacion(entity.getEstProgramacion());
        dto.setFecAnulacion(entity.getFecAnulacion());

        return dto;
    }
    
    public MipresResponse toResponse(Programacion entity) {
        return MipresResponse.builder()
                .id(entity.getId())
                .idTransaccion(entity.getIdProgramacion())
                .mensaje("Procesado exitosamente")
                .build();
    }
}
