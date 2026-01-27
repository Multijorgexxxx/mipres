package com.mipres.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PutReporteEntregaRequest {

    @JsonProperty("ID")
    private Long id;

    @JsonProperty("EstadoEntrega")
    private Integer estadoEntrega;

    @JsonProperty("CausaNoEntrega")
    private Integer causaNoEntrega;

    @JsonProperty("ValorEntregado")
    private String valorEntregado;
}
