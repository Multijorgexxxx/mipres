package com.mipres.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "menus")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String titulo;
    private String icono;
    private String ruta;
    private Integer orden;
    
    @Column(name="menu_padre_id")
    private Integer menuPadreid;
    @Column(name="activo")
    private boolean activo = true;
    
}
