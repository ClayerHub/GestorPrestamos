package com.gestorprestamos.services;

import com.gestorprestamos.entities.EvaluacionCreditoEntidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gestorprestamos.repositories.EvaluacionCreditoRepositorio;

import java.util.ArrayList;

@Service
public class EvaluacionCreditoServicio {

    @Autowired
    EvaluacionCreditoRepositorio evaluacionCreditoRepositorio;

    public ArrayList<EvaluacionCreditoEntidad> getEvaluacionCreditos(){
        return (ArrayList<EvaluacionCreditoEntidad>) evaluacionCreditoRepositorio.findAll();
    }

    public EvaluacionCreditoEntidad saveEvaluacionCredito(EvaluacionCreditoEntidad evaluacionCredito) {return evaluacionCreditoRepositorio.save(evaluacionCredito); }

    public EvaluacionCreditoEntidad getEvaluacionCreditoById(Long id) { return evaluacionCreditoRepositorio.findById(id).get();}

    public EvaluacionCreditoEntidad updateEvaluacionCredito(EvaluacionCreditoEntidad evaluacionCredito) {return evaluacionCreditoRepositorio.save(evaluacionCredito);}

    public boolean deleteEvaluacionCredito(Long id) throws Exception{
        try{
            evaluacionCreditoRepositorio.deleteById(id);
            return true;
        } catch (Exception error){
            throw new Exception(error.getMessage());
        }
    }

    public EvaluacionCreditoEntidad requestEvaluation(EvaluacionCreditoEntidad evaluacion) {
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

        return evaluacionCreditoRepositorio.save(evaluacion);
    }
}