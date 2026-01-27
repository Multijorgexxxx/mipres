package com.mipres.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mipres.entity.VwModulo;
import com.mipres.service.VwModuloService;

@RestController
@RequestMapping("/api/menus")
public class MenuController {
    
    private final VwModuloService vwModuloService;
    
    public MenuController(VwModuloService vwModuloService) {
        this.vwModuloService = vwModuloService;
    }
    
    @GetMapping
    public ResponseEntity<List<VwModulo>> getMenus(@RequestParam(required = true) String role) {
        List<VwModulo> menus = vwModuloService.getMenusbyRole(role);
        System.out.println("Menus: " + menus);
        return ResponseEntity.ok(menus);
    }

    @GetMapping("/usuario")
    public ResponseEntity<List<VwModulo>> getMethodName(@RequestParam String usuario) {
        List<VwModulo> menus = vwModuloService.getMenusbyUsuario(usuario);
        System.out.println("Menus: " + menus);
        return ResponseEntity.ok(menus);
    }
    
    @GetMapping("/role-id")
    public ResponseEntity<List<VwModulo>> getMenusByRoleId(@RequestParam Integer idRol) {
        List<VwModulo> menus = vwModuloService.getMenusByIdRol(idRol);
        System.out.println("Menus: " + menus);
        return ResponseEntity.ok(menus);
    }
    
}