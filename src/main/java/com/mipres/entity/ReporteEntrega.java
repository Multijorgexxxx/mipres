package com.mipres.entity;

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
@Table(name = "reporte_entrega")
@Data
public class ReporteEntrega {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "id_reporte_entrega")
    private Long idReporteEntrega;

    @Column(name = "estado_entrega")
    private Integer estadoEntrega;

    @Column(name = "valor_entregado")
    private Integer valorEntregado;

    @Column(name = "fec_entrega")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Bogota")
    private LocalDateTime fecEntrega;

    @Column(name = "fec_rep_entrega")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime fecRepEntrega;

    @Column(name = "est_rep_entrega")
    private Integer estRepEntrega;

    @Column(name = "fec_anulacion")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime fecAnulacion;

    @Column(name = "ESTADO", nullable = true)
    private String estado;

        
    @PrePersist
    protected void onCreate() {
        if (estRepEntrega == null) {
            estRepEntrega = 1;
        }
        estado = switch (estRepEntrega) {
            case 1 -> "ENTREGADO";
            case 2 -> "SUMINISTRADO";
            default -> "REP_ENTREGA_ANULADA";
        };
    }

    @PreUpdate
    protected void onUpdate() {
        onCreate();
    }

}