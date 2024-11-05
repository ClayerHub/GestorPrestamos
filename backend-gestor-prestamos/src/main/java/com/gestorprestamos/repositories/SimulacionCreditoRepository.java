package com.gestorprestamos.repositories;

import com.gestorprestamos.entities.SimulacionCreditoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimulacionCreditoRepository extends JpaRepository<SimulacionCreditoEntity, Long> {
}
