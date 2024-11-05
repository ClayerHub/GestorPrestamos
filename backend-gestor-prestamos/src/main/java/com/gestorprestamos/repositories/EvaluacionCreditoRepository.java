package com.gestorprestamos.repositories;

import com.gestorprestamos.entities.EvaluacionCreditoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluacionCreditoRepository extends JpaRepository<EvaluacionCreditoEntity, Long> {

}
