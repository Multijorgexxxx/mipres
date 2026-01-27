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
@Table(name = "facturacion")
@Data
public class Facturacion {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "id_facturacion")
    private Long idFacturacion;

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

    @Column(name = "no_factura", length = 255)
    private String noFactura;

    @Column(name = "no_id_eps", length = 20)
    private String noIDEPS;

    @Column(name = "cod_eps", length = 20)
    private String codEPS;

    @Column(name = "cod_ser_tec_a_entregado", length = 20)
    private String codSerTecAEntregado;

    @Column(name = "cant_un_min_dis")
    private String cantUnMinDis;

    @Column(name = "valor_unit_facturado")
    private String valorUnitFacturado;

    @Column(name = "valor_tot_facturado")
    private String valorTotFacturado;

    @Column(name = "cuota_moder")
    private String cuotaModer;

    @Column(name = "copago")
    private String copago;

    @Column(name = "fec_facturacion")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime fecFacturacion;

    @Column(name = "est_facturacion")
    private Integer estFacturacion;

    @Column(name = "fec_anulacion")
    private LocalDateTime fecAnulacion;

    @Column(name = "codigos_facturacion", length = 255)
    private String codigosFacturacion;

    
    @Column(name = "ESTADO", nullable = true)
    private String estado;

        
    @PrePersist
    protected void onCreate() {
        if (estFacturacion == null) {
            estFacturacion = 1;
        }
        estado = switch (estFacturacion) {
            case 1 -> "FACTURADO";
            case 2 -> "FACTURADO_EPS";
            default -> "FACTURACION_ANULADA";
        };
    }

    @PreUpdate
    protected void onUpdate() {
        onCreate();
    }

}