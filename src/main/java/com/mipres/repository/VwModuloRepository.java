package com.mipres.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mipres.entity.VwModulo;

@Repository
public interface VwModuloRepository extends JpaRepository<VwModulo, Long> {
    
    List<VwModulo> findByNombreRol(String nombreRol);
    
    List<VwModulo> findByNombreRolAndActivoTrueOrderByOrdenAsc(String nombreRol);
    
    List<VwModulo> findByUsuario(String usuario);

    List<VwModulo> findByIdRol(Integer idRol);
}
