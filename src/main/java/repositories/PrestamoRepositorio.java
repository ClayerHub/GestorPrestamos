package repositories;

import entities.ClienteEntidad;
import entities.PrestamoEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PrestamoRepositorio extends JpaRepository<PrestamoEntidad, Long> {

    List<ClienteEntidad> findByIdCliente(int idCliente;

}
