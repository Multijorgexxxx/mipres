package com.mipres.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mipres.entity.Usuarios;
import com.mipres.repository.UsuarioRepository;
import com.mipres.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Optional<Usuarios> findByUsuario(String usuario){
        return usuarioRepository.findByUsuario(usuario);
    }

    @Override
    public Optional<Usuarios> findByContraseña(String contraseña){
        return usuarioRepository.findByContraseña(contraseña);
    }

    @Override
    public Optional<Usuarios> findByUsuarioAndContraseña(String usuario, String contraseña){
        return usuarioRepository.findByUsuarioAndContraseña(usuario, contraseña);
    }
}
