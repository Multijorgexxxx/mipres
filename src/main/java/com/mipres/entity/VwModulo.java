package com.mipres.entity;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "vw_modulos")
@Immutable
@Data
public class VwModulo {

    @Id
    @Column(name = "ID_MENU")
    private Long id;

    private String titulo;
    private String icono;
    private String ruta;
    private Integer orden;
    private Boolean activo;

    @Column(name = "menu_padre_id")
    private Long menuPadreId;

    @Column(name = "nombre_rol")
    private String nombreRol;

    @Column(name = "ID_ROL")
    private Long idRol;
    
    @Column(name = "USUARIO")
    private String usuario;
}