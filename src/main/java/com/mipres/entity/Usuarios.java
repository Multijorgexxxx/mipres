package com.mipres.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
@Data
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "USUARIO", length = 20)
    private String usuario;

    @Column(name = "CONTRASEÑA", length = 20)
    private String contraseña;

    @Column(name = "FECHA_CREACION", insertable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "ID_ROL")
    private Integer idRol;
}