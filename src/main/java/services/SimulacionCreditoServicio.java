package services;

import entities.SimulacionCreditoEntidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.SimulacionCreditoRepositorio;

import java.time.LocalDate;
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

    public SimulacionCreditoEntidad calculateSimulation(int idCliente, double monto, int plazo, double tasaInteres) {
        double tasaMensual = tasaInteres / 12 / 100;
        int numeroPagos = plazo * 12;

        double cuotaMensual = monto * (tasaMensual * Math.pow(1 + tasaMensual, numeroPagos)) /
                (Math.pow(1 + tasaMensual, numeroPagos) - 1);

        SimulacionCreditoEntidad simulacion = new SimulacionCreditoEntidad();
        simulacion.setIdCliente(idCliente);
        simulacion.setMontoSolicitado(monto);
        simulacion.setPlazoSolicitado(plazo);
        simulacion.setTasaInteres(tasaInteres);
        simulacion.setCuotaMensual(cuotaMensual);
        simulacion.setFechaSimulacion(LocalDate.now());

        return simulacionCreditoRepositorio.save(simulacion);
    }
}
