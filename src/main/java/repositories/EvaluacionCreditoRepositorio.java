package repositories;

import entities.EvaluacionCreditoEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluacionCreditoRepositorio extends JpaRepository<EvaluacionCreditoEntidad, Long> {
}
