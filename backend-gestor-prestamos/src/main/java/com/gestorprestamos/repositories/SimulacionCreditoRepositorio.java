package com.gestorprestamos.repositories;

import com.gestorprestamos.entities.SimulacionCreditoEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimulacionCreditoRepositorio extends JpaRepository<SimulacionCreditoEntidad, Long> {
}
