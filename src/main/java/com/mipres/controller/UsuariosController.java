package com.mipres.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mipres.entity.Usuarios;
import com.mipres.service.UsuarioService;

@RestController
@RequestMapping("/api/login")
public class UsuariosController {
    
    private final UsuarioService usuarioService;
    
    public UsuariosController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    @GetMapping
    public ResponseEntity<Optional<Usuarios>> getUsuario(@RequestParam(required = true) String usuario, @RequestParam(required = true) String contraseña) {
        Optional<Usuarios> usuarioEncontrado = usuarioService.findByUsuarioAndContraseña(usuario, contraseña);
        System.out.println("Usu encontrado: " + usuarioEncontrado);
        return ResponseEntity.ok(usuarioEncontrado);
    }
}