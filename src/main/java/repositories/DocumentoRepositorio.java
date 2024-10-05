package repositories;

import entities.DocumentoEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface DocumentoRepositorio extends JpaRepository<DocumentoEntidad, Long> {

    public DocumentoEntidad findByRut(String rut);
    public DocumentoEntidad findByDate(Date date);
}
