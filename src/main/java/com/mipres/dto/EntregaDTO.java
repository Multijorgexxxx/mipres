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
public class EntregaDTO {

    @JsonProperty("ID")
    private Long id;

    @JsonProperty("IDEntrega")
    private Long idEntrega;

    @JsonProperty("CodSerTecEntregado")
    private String codSerTecEntregado;

    @JsonProperty("CantTotEntregada")
    private String cantTotEntregada;

    @JsonProperty("EntTotal")
    private Integer entTotal;

    @JsonProperty("CausaNoEntrega")
    private Integer causaNoEntrega;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Bogota")
    @JsonProperty("FecEntrega")
    private String fecEntrega;

    @JsonProperty("NoLote")
    private String noLote;

    @JsonProperty("TipoIDRecibe")
    private String tipoIdRecibe;

    @JsonProperty("NoIDRecibe")
    private String noIdRecibe;

    @JsonProperty("EstEntrega")
    private Integer estEntrega;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "America/Bogota")
    @JsonProperty("FecAnulacion")
    private LocalDateTime fecAnulacion;

    @JsonProperty("CodigosEntrega")
    private String codigosEntrega;
}
