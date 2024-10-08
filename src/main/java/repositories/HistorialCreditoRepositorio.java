package repositories;

import entities.HistorialCreditoEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorialCreditoRepositorio extends JpaRepository<HistorialCreditoEntidad, Long> {
}
