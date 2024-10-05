package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.SimulacionCreditoRepositorio;

@Service
public class SimulacionCreditoServicio {

    @Autowired
    SimulacionCreditoRepositorio simulacionCreditoRepositorio;
}
