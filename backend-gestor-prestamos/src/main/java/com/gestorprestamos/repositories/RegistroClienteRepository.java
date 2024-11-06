package com.gestorprestamos.repositories;

import com.gestorprestamos.entities.RegistroClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroClienteRepository extends JpaRepository<RegistroClienteEntity, Long> {

    public RegistroClienteEntity findByRut(String rut);
}
