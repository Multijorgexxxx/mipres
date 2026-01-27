package com.mipres.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.mipres.dto.FacturacionDTO;
import com.mipres.entity.Facturacion;
import com.mipres.response.MipresResponse;

@Component
public class FacturacionMapper {

        public List<Facturacion> toEntityList(List<FacturacionDTO> dtos) {
        if (dtos == null) {
            return Collections.emptyList();
        }

        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    public List<FacturacionDTO> toDtoList(List<Facturacion> entities) {
        if (entities == null) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Facturacion toEntity(FacturacionDTO dto) {
        if (dto == null) return null;

        Facturacion entity = new Facturacion();
        
        entity.setId(dto.getId());
        entity.setIdFacturacion(dto.getIdFacturacion());
        entity.setNoPrescripcion(dto.getNoPrescripcion());
        entity.setTipoTec(dto.getTipoTec());
        entity.setConTec(dto.getConTec());
        entity.setTipoIDPaciente(dto.getTipoIdPaciente());
        entity.setNoIDPaciente(dto.getNoIdPaciente());
        entity.setNoEntrega(dto.getNoEntrega());
        entity.setNoSubEntrega(dto.getNoSubEntrega());
        entity.setNoFactura(dto.getNoFactura());
        entity.setNoIDEPS(dto.getNoIdEPS());
        entity.setCodEPS(dto.getCodEPS());
        entity.setCodSerTecAEntregado(dto.getCodSerTecAEntregado());

        entity.setCantUnMinDis(dto.getCantUnMinDis());
        entity.setValorUnitFacturado(dto.getValorUnitFacturado());
        entity.setValorTotFacturado(dto.getValorTotFacturado());
        entity.setCuotaModer(dto.getCuotaModer());
        entity.setCopago(dto.getCopago());

        entity.setFecFacturacion(dto.getFecFacturacion());
        entity.setEstFacturacion(dto.getEstFacturacion());
        entity.setFecAnulacion(dto.getFecAnulacion());
        entity.setCodigosFacturacion(dto.getCodigosFacturacion());

        return entity;
    }

    public FacturacionDTO toDto(Facturacion entity) {
        if (entity == null) return null;

        FacturacionDTO dto = new FacturacionDTO();
        
        dto.setId(entity.getId());
        dto.setIdFacturacion(entity.getIdFacturacion());
        dto.setNoPrescripcion(entity.getNoPrescripcion());
        dto.setTipoTec(entity.getTipoTec());
        dto.setConTec(entity.getConTec());
        dto.setTipoIdPaciente(entity.getTipoIDPaciente());
        dto.setNoIdPaciente(entity.getNoIDPaciente());
        dto.setNoEntrega(entity.getNoEntrega());
        dto.setNoSubEntrega(entity.getNoSubEntrega());
        dto.setNoFactura(entity.getNoFactura());
        dto.setNoIdEPS(entity.getNoIDEPS());
        dto.setCodEPS(entity.getCodEPS());
        dto.setCodSerTecAEntregado(entity.getCodSerTecAEntregado());

        dto.setCantUnMinDis(entity.getCantUnMinDis());
        dto.setValorUnitFacturado(entity.getValorUnitFacturado());
        dto.setValorTotFacturado(entity.getValorTotFacturado());
        dto.setCuotaModer(entity.getCuotaModer());
        dto.setCopago(entity.getCopago());

        dto.setFecFacturacion(entity.getFecFacturacion());
        dto.setEstFacturacion(entity.getEstFacturacion());
        dto.setFecAnulacion(entity.getFecAnulacion());
        dto.setCodigosFacturacion(entity.getCodigosFacturacion());

        return dto;
    }

    public MipresResponse toResponse(Facturacion entity) {
        return MipresResponse.builder()
                .id(entity.getId())
                .idTransaccion(entity.getIdFacturacion())
                .mensaje("Procesado exitosamente")
                .build();
    }
}