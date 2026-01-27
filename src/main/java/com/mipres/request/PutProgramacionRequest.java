package com.mipres.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PutProgramacionRequest {

    @JsonProperty("ID")
    private Long id;

    @JsonProperty("FecMaxEnt")
    private String fecMaxEnt;

    @JsonProperty("TipoIDSedeProv")
    private String tipoIdSedeProv;

    @JsonProperty("NoIDSedeProv")
    private String noIdSedeProv;

    @JsonProperty("CodSedeProv")
    private String codSedeProv;

    @JsonProperty("CodSerTecAEntregar")
    private String codSerTecAEntregar;

    @JsonProperty("CantTotAEntregar")
    private int cantTotAEntregar;
}
