package com.mipres.service;

import java.util.Optional;
import com.mipres.entity.Usuarios;

public interface UsuarioService {

    public Optional<Usuarios> findByUsuario(String usuario);

    public Optional<Usuarios> findByContraseña(String contraseña);

    public Optional<Usuarios> findByUsuarioAndContraseña(String usuario, String contraseña);
}
