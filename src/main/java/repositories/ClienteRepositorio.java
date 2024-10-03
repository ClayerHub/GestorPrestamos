package repositories;

import entities.ClienteEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<ClienteEntidad, Long> {

    public ClienteEntidad findByRut(String rut);
    public ClienteEntidad findByNombre(String nombre);
    public ClienteEntidad findByApellido(String apellido);

}
