package com.mipres.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mipres.entity.VwModulo;
import com.mipres.repository.VwModuloRepository;
import com.mipres.service.VwModuloService;

@Service
public class VwModuloServiceImpl implements VwModuloService{
    
    private final VwModuloRepository vwModuloRepository;

    public VwModuloServiceImpl(VwModuloRepository vwModuloRepository) {
        this.vwModuloRepository = vwModuloRepository;
    }

    @Override
    public List<VwModulo> getMenusbyRole(String role) {
        return vwModuloRepository.findByNombreRol(role);
    }
    
    @Override
    public List<VwModulo> getMenusbyUsuario(String usuario) {
        return vwModuloRepository.findByUsuario(usuario);
    }

    @Override
    public List<VwModulo> getMenusByIdRol(Integer idRol) {
        return vwModuloRepository.findByIdRol(idRol);
    }

}
