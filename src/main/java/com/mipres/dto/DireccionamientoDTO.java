package com.mipres.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DireccionamientoDTO implements Serializable {

    @JsonProperty("ID")
    private Long id;

    @JsonProperty("IDDireccionamiento")
    private Long idDireccionamiento;

    @JsonProperty("NoPrescripcion")
    private String noPrescripcion;

    @JsonProperty("TipoTec")
    private String tipoTec;

    @JsonProperty("ConTec")
    private Integer conTec;

    @JsonProperty("TipoIDPaciente")
    private String tipoIdPaciente;

    @JsonProperty("NoIDPaciente")
    private String noIdPaciente;

    @JsonProperty("NoEntrega")
    private Integer noEntrega;

    @JsonProperty("NoSubEntrega")
    private Integer noSubEntrega;

    @JsonProperty("TipoIDProv")
    private String tipoIdProv;

    @JsonProperty("NoIDProv")
    private String noIdProv;

    @JsonProperty("CodMunEnt")
    private String codMunEnt;

    @JsonProperty("FecMaxEnt")
    private String fecMaxEnt;

    @JsonProperty("CantTotAEntregar")
    private Integer cantTotAEntregar;

    @JsonProperty("DirPaciente")
    private String dirPaciente;

    @JsonProperty("CodSerTecAEntregar")
    private String codSerTecAEntregar;

    @JsonProperty("NoIDEPS")
    private String noIdEPS;

    @JsonProperty("CodEPS")
    private String codEPS;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonProperty("FecDireccionamiento")
    private LocalDateTime fecDireccionamiento;

    @JsonProperty("EstDireccionamiento")
    private Integer estDireccionamiento;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonProperty("FecAnulacion")
    private LocalDateTime fecAnulacion;
}
