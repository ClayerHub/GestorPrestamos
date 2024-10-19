package services;

import entities.SimulacionCreditoEntidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.SimulacionCreditoRepositorio;

import java.util.ArrayList;

@Service
public class SimulacionCreditoServicio {

    @Autowired
    SimulacionCreditoRepositorio simulacionCreditoRepositorio;

    public ArrayList<SimulacionCreditoEntidad> getSimulacionCreditos(){
        return (ArrayList<SimulacionCreditoEntidad>) simulacionCreditoRepositorio.findAll();
    }

    public SimulacionCreditoEntidad saveSimulacionCredito(SimulacionCreditoEntidad simulacionCredito){return simulacionCreditoRepositorio.save(simulacionCredito);}

    public SimulacionCreditoEntidad getSimulacionCreditoById(Long id){return simulacionCreditoRepositorio.findById(id).get();}

    public SimulacionCreditoEntidad updateSimulacionCredito(SimulacionCreditoEntidad simulacionCredito){ return simulacionCreditoRepositorio.save(simulacionCredito);}

    public boolean deleteSimulacionCredito(Long id) throws Exception{
        try{
            simulacionCreditoRepositorio.deleteById(id);
            return true;
        } catch (Exception error){
            throw new Exception(error.getMessage());
        }
    }
}
