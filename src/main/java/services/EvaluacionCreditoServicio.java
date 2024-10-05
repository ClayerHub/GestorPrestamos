package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.EvaluacionCreditoRepositorio;

@Service
public class EvaluacionCreditoServicio {

    @Autowired
    EvaluacionCreditoRepositorio evaluacionCreditoRepositorio;
}
