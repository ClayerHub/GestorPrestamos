package com.gestorprestamos.repositories;

import com.gestorprestamos.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    public UsuarioEntity findByRut(String rut);

    public UsuarioEntity deleteByRut(String rut);

    boolean existsByRut(String rut);
}
