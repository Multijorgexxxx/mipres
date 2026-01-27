package com.mipres.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "direccionamiento")
@Data
public class Direccionamiento implements Serializable {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "id_direccionamiento")
    private Long idDireccionamiento;

    @Column(name = "no_prescripcion", length = 20)
    private String noPrescripcion;

    @Column(name = "tipo_tec", length = 2)
    private String tipoTec;

    @Column(name = "con_tec")
    private Integer conTec;

    @Column(name = "tipo_id_paciente", length = 5)
    private String tipoIDPaciente;

    @Column(name = "no_id_paciente", length = 20)
    private String noIDPaciente;

    @Column(name = "no_entrega")
    private Integer noEntrega;

    @Column(name = "no_sub_entrega")
    private Integer noSubEntrega;

    @Column(name = "tipo_id_prov", length = 5)
    private String tipoIDProv;

    @Column(name = "no_id_prov", length = 20)
    private String noIDProv;

    @Column(name = "cod_mun_ent", length = 10)
    private String codMunEnt;

    @Column(name = "fec_max_ent")
    private LocalDate fecMaxEnt;

    @Column(name = "cant_tot_a_entregar")
    private String cantTotAEntregar;

    @Column(name = "dir_paciente", length = 255)
    private String dirPaciente;

    @Column(name = "cod_ser_tec_a_entregar", length = 20)
    private String codSerTecAEntregar;

    @Column(name = "no_id_eps", length = 20)
    private String noIDEPS;

    @Column(name = "cod_eps", length = 20)
    private String codEPS;

    @Column(name = "fec_direccionamiento")
    private LocalDateTime fecDireccionamiento;

    @Column(name = "est_direccionamiento")
    private Integer estDireccionamiento;

    @Column(name = "fec_anulacion")
    private LocalDateTime fecAnulacion;

    @Column(name = "ESTADO", nullable = true)
    private String estado;

    @PrePersist
    protected void onCreate() {
        if (estDireccionamiento == null) {
            estDireccionamiento = 1;
        }
        estado = switch (estDireccionamiento) {
            case 1 -> "DIRECCIONADO";
            case 2 -> "PROGRAMADO";
            default -> "DIRECCIONAMIENTO_ANULADO";
        };
    }

    @PreUpdate
    protected void onUpdate() {
        onCreate();
    }

}