package com.mipres.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mipres.entity.Facturacion;

@Repository
public interface FacturacionRepository extends JpaRepository<Facturacion, Long> {
}
