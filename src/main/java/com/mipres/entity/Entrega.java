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
@Table(name = "entrega")
@Data
public class Entrega {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "id_entrega")
    private Long idEntrega;

    @Column(name = "cod_ser_tec_entregado", length = 20)
    private String codSerTecEntregado;

    @Column(name = "cant_tot_entregada")
    private String cantTotEntregada;

    @Column(name = "ent_total")
    private Integer entTotal;

    @Column(name = "causa_no_entrega")
    private Integer causaNoEntrega;

    @Column(name = "fec_entrega")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime fecEntrega;

    @Column(name = "no_lote", length = 50)
    private String noLote;

    @Column(name = "tipo_id_recibe", length = 5)
    private String tipoIDRecibe;

    @Column(name = "no_id_recibe", length = 20)
    private String noIDRecibe;

    @Column(name = "est_entrega")
    private Integer estEntrega;

    @Column(name = "fec_anulacion")
    private LocalDateTime fecAnulacion;

    @Column(name = "codigos_entrega", length = 255)
    private String codigosEntrega;

    @Column(name = "ESTADO", nullable = true)
    private String estado;

    
    @PrePersist
    protected void onCreate() {
        if (estEntrega == null) {
            estEntrega = 1;
        }
        estado = switch (estEntrega) {
            case 1 -> "PROGRAMADO";
            case 2 -> "REP_ENTREGA";
            default -> "ENTREGA_ANULADA";
        };
    }

    @PreUpdate
    protected void onUpdate() {
        onCreate();
    }

}