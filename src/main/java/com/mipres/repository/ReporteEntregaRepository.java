package com.mipres.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mipres.entity.ReporteEntrega;

@Repository
public interface ReporteEntregaRepository extends JpaRepository<ReporteEntrega, Long> {
}
