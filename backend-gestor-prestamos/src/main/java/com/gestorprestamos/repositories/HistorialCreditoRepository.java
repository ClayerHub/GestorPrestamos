package com.gestorprestamos.repositories;

import com.gestorprestamos.entities.HistorialCreditoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorialCreditoRepository extends JpaRepository<HistorialCreditoEntity, Long> {
}
