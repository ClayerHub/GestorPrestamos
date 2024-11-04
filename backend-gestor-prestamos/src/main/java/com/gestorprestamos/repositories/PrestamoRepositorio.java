package com.gestorprestamos.repositories;

import com.gestorprestamos.entities.PrestamoEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepositorio extends JpaRepository<PrestamoEntidad, Long> {


}
