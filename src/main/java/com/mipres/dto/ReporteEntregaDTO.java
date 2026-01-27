package com.mipres.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReporteEntregaDTO {

    @JsonProperty("ID")
    private Long id;

    @JsonProperty("IDReporteEntrega")
    private Long idReporteEntrega;

    @JsonProperty("EstadoEntrega")
    private Integer estadoEntrega;

    @JsonProperty("ValorEntregado")
    private Integer valorEntregado;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "America/Bogota")
    @JsonProperty("FecEntrega")
    private LocalDateTime fecEntrega;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonProperty("FecRepEntrega")
    private LocalDateTime fecRepEntrega;

    @JsonProperty("EstRepEntrega")
    private Integer estRepEntrega;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonProperty("FecAnulacion")
    private LocalDateTime fecAnulacion;
}
