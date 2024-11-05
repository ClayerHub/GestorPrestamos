package com.gestorprestamos.repositories;

import com.gestorprestamos.entities.DocumentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface DocumentoRepository extends JpaRepository<DocumentoEntity, Long> {

    public DocumentoEntity findByFechaCarga(Date fechaCarga);
}
