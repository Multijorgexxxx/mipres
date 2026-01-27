package com.mipres.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.mipres.entity.Usuarios;

public interface UsuarioRepository extends JpaRepository<Usuarios, Integer> {
    Optional<Usuarios> findByUsuario(String usuario);

    Optional<Usuarios> findByContraseña(String contraseña);

    Optional<Usuarios> findByUsuarioAndContraseña(String usuario, String contraseña);
}