package com.mipres.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mipres.entity.Programacion;

@Repository
public interface ProgramacionRepository extends JpaRepository<Programacion, Long> {
}
