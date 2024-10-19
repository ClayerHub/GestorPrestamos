package services;

import entities.EvaluacionCreditoEntidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.EvaluacionCreditoRepositorio;

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
}