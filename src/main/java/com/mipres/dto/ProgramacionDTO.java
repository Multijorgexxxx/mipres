package com.mipres.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProgramacionDTO implements Serializable{

    @JsonProperty("ID")
    private Long id;

    @JsonProperty("IDProgramacion")
    private Long idProgramacion;

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
    private Integer cantTotAEntregar;

    @JsonProperty("FecProgramacion")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime fecProgramacion;

    @JsonProperty("EstProgramacion")
    private Integer estProgramacion;

    @JsonProperty("FecAnulacion")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime fecAnulacion;
}

