package com.mipres.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mipres.entity.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    @Query("SELECT m FROM Menu m WHERE m.menuPadreid = 0 AND m.activo = true ORDER BY m.orden")
    List<Menu> findMenusPrincipales();
}
