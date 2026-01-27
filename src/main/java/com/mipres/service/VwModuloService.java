package com.mipres.service;

import java.util.List;

import com.mipres.entity.VwModulo;

public interface VwModuloService {

    public List<VwModulo> getMenusbyRole(String role);

    public List<VwModulo> getMenusbyUsuario(String usuario);

    public List<VwModulo> getMenusByIdRol(Integer idRol);

}
