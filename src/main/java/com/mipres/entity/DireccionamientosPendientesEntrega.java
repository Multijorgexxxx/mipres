package com.mipres.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "vw_direccionamientos_pendiente_entrega")
@Immutable
@Getter
@Setter
public class DireccionamientosPendientesEntrega {

    @Id // Usamos el ID de la fila como llave primaria
    private Long id;

    @Column(name = "id_direccionamiento")
    private Long idDireccionamiento;

    @Column(name = "no_prescripcion")
    private String noPrescripcion;

    @Column(name = "tipo_tec")
    private String tipoTec;

    @Column(name = "con_tec")
    private Integer conTec;

    @Column(name = "tipo_id_paciente")
    private String tipoIdPaciente;

    @Column(name = "no_id_paciente")
    private String noIdPaciente;

    @Column(name = "no_entrega")
    private Integer noEntrega;

    @Column(name = "no_sub_entrega")
    private Integer noSubEntrega;

    @Column(name = "tipo_id_prov")
    private String tipoIdProv;

    @Column(name = "no_id_prov")
    private String noIdProv;

    @Column(name = "cod_mun_ent")
    private String codMunEnt;

    @Column(name = "dir_paciente")
    private String dirPaciente;

    @Column(name = "no_id_eps")
    private String noIdEps;

    @Column(name = "cod_eps")
    private String codEps;

    @Column(name = "fec_direccionamiento")
    private LocalDateTime fecDireccionamiento;

    @Column(name = "est_direccionamiento")
    private Integer estDireccionamiento;

    @Column(name = "fec_anulacion")
    private LocalDateTime fecAnulacion;

    // Campos provenientes del JOIN con Programación
    @Column(name = "id_programacion")
    private Long idProgramacion;

    @Column(name = "tipo_id_sede_prov")
    private String tipoIdSedeProv;

    @Column(name = "no_id_sede_prov")
    private String noIdSedeProv;

    @Column(name = "cod_sede_prov")
    private String codSedeProv;

    @Column(name = "cod_ser_tec_a_entregar")
    private String codSerTecAEntregar;

    @Column(name = "cant_tot_a_entregar")
    private Double cantTotAEntregar;

    @Column(name = "fec_max_ent")
    private LocalDateTime fecMaxEnt;

    @Column(name = "estado")
    private String estado;
}