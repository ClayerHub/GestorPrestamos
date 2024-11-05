package com.gestorprestamos.services;

import com.gestorprestamos.entities.EvaluacionCreditoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gestorprestamos.repositories.EvaluacionCreditoRepository;

import java.util.ArrayList;

@Service
public class EvaluacionCreditoService {

    @Autowired
    EvaluacionCreditoRepository evaluacionCreditoRepository;

    public ArrayList<EvaluacionCreditoEntity> getEvaluacionCreditos(){
        return (ArrayList<EvaluacionCreditoEntity>) evaluacionCreditoRepository.findAll();
    }

    public EvaluacionCreditoEntity saveEvaluacionCredito(EvaluacionCreditoEntity evaluacionCredito) {return evaluacionCreditoRepository.save(evaluacionCredito); }

    public EvaluacionCreditoEntity getEvaluacionCreditoById(Long id) { return evaluacionCreditoRepository.findById(id).get();}

    public EvaluacionCreditoEntity updateEvaluacionCredito(EvaluacionCreditoEntity evaluacionCredito) {return evaluacionCreditoRepository.save(evaluacionCredito);}

    public boolean deleteEvaluacionCredito(Long id) throws Exception{
        try{
            evaluacionCreditoRepository.deleteById(id);
            return true;
        } catch (Exception error){
            throw new Exception(error.getMessage());
        }
    }

    public EvaluacionCreditoEntity requestEvaluation(EvaluacionCreditoEntity evaluacion) {
        if (evaluacion.getCuotaIngreso() > 0.35) {
            evaluacion.setResultado("Rechazado");
        }
        else if (!evaluacion.getHistorialCredito().equals("Bueno")) {
            evaluacion.setResultado("Rechazado");
        }
        else if (evaluacion.getAntiguedadLaboral() < 1) {
            evaluacion.setResultado("Rechazado");
        }
        else if (evaluacion.getDeudaIngreso() > 0.50) {
            evaluacion.setResultado("Rechazado");
        }
        else if (evaluacion.getMontoFinanciamiento() > 80) {
            evaluacion.setResultado("Rechazado");
        }
        else {
            evaluacion.setResultado("Aprobado");
        }

        return evaluacionCreditoRepository.save(evaluacion);
    }
}