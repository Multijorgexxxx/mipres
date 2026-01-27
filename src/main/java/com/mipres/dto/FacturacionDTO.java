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
public class FacturacionDTO {

    @JsonProperty("ID")
    private Long id;

    @JsonProperty("IDFacturacion")
    private Long idFacturacion;

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

    @JsonProperty("NoFactura")
    private String noFactura;

    @JsonProperty("NoIDEPS")
    private String noIdEPS;

    @JsonProperty("CodEPS")
    private String codEPS;

    @JsonProperty("CodSerTecAEntregado")
    private String codSerTecAEntregado;

    @JsonProperty("CantUnMinDis")
    private String cantUnMinDis;

    @JsonProperty("ValorUnitFacturado")
    private String valorUnitFacturado;

    @JsonProperty("ValorTotFacturado")
    private String valorTotFacturado;

    @JsonProperty("CuotaModer")
    private String cuotaModer;

    @JsonProperty("Copago")
    private String copago;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonProperty("FecFacturacion")
    private LocalDateTime fecFacturacion;

    @JsonProperty("EstFacturacion")
    private Integer estFacturacion;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonProperty("FecAnulacion")
    private LocalDateTime fecAnulacion;

    @JsonProperty("CodigosFacturacion")
    private String codigosFacturacion;
}