package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.PrestamoRepositorio;

@Service
public class PrestamoServicio {

    @Autowired
    PrestamoRepositorio prestamoRepositorio;
}
