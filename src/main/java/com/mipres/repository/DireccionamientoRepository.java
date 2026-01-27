package com.mipres.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mipres.entity.Direccionamiento;

@Repository
public interface DireccionamientoRepository extends JpaRepository<Direccionamiento, Long> {
}
