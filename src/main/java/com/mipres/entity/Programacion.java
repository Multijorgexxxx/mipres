package com.mipres.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "programacion")
@Data
public class Programacion {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "id_programacion")
    private Long idProgramacion;

    @Column(name = "fec_max_ent")
    private LocalDate fecMaxEnt;

    @Column(name = "tipo_id_sede_prov", length = 5)
    private String tipoIDSedeProv;

    @Column(name = "no_id_sede_prov", length = 20)
    private String noIDSedeProv;

    @Column(name = "cod_sede_prov", length = 20)
    private String codSedeProv;

    @Column(name = "cod_ser_tec_a_entregar", length = 20)
    private String codSerTecAEntregar;

    @Column(name = "cant_tot_a_entregar")
    private String cantTotAEntregar;

    @Column(name = "fec_programacion")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime fecProgramacion;

    @Column(name = "est_programacion")
    private Integer estProgramacion;

    @Column(name = "fec_anulacion")
    private LocalDateTime fecAnulacion;
    
    @Column(name = "ESTADO", nullable = true)
    private String estado;
    
    
    @PrePersist
    protected void onCreate() {
        if (estProgramacion == null) {
            estProgramacion = 1;
        }
        estado = switch (estProgramacion) {
            case 1 -> "PROGRAMADO";
            case 2 -> "ENTREGADO";
            default -> "PROGRAMACION_ANULADA";
        };
    }

    @PreUpdate
    protected void onUpdate() {
        onCreate();
    }

}