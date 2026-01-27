package com.mipres.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PutEntregaRequest {

@JsonProperty("ID")
    private Long id;

    @JsonProperty("CodSerTecEntregado")
    private String codSerTecEntregado;

    @JsonProperty("CantTotEntregada")
    private String cantTotEntregada;

    @JsonProperty("EntTotal")
    private Integer entTotal;

    @JsonProperty("CausaNoEntrega")
    private Integer causaNoEntrega;

    /**
     * Se usa String para recibir la fecha exactamente como viene en el JSON
     * y evitar errores de parseo antes de llegar al Service.
     */
    @JsonProperty("FecEntrega")
    private String fecEntrega;

    @JsonProperty("NoLote")
    private String noLote;

    @JsonProperty("TipoIDRecibe")
    private String tipoIdRecibe;

    @JsonProperty("NoIDRecibe")
    private String noIdRecibe;

}
