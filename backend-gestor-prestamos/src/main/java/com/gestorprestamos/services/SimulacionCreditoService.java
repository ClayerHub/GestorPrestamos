package com.gestorprestamos.services;

import com.gestorprestamos.entities.SimulacionCreditoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gestorprestamos.repositories.SimulacionCreditoRepository;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class SimulacionCreditoService {

    @Autowired
    SimulacionCreditoRepository simulacionCreditoRepository;

    public ArrayList<SimulacionCreditoEntity> getSimulacionCreditos(){
        return (ArrayList<SimulacionCreditoEntity>) simulacionCreditoRepository.findAll();
    }

    public SimulacionCreditoEntity saveSimulacionCredito(SimulacionCreditoEntity simulacionCredito){return simulacionCreditoRepository.save(simulacionCredito);}

    public SimulacionCreditoEntity getSimulacionCreditoById(Long id){return simulacionCreditoRepository.findById(id).get();}

    public SimulacionCreditoEntity updateSimulacionCredito(SimulacionCreditoEntity simulacionCredito){ return simulacionCreditoRepository.save(simulacionCredito);}

    public boolean deleteSimulacionCredito(Long id) throws Exception{
        try{
            simulacionCreditoRepository.deleteById(id);
            return true;
        } catch (Exception error){
            throw new Exception(error.getMessage());
        }
    }

    public SimulacionCreditoEntity calculateSimulation(int idCliente, double monto, int plazo, double tasaInteres) {
        double tasaMensual = tasaInteres / 12 / 100;
        int numeroPagos = plazo * 12;

        double cuotaMensual = monto * (tasaMensual * Math.pow(1 + tasaMensual, numeroPagos)) /
                (Math.pow(1 + tasaMensual, numeroPagos) - 1);

        SimulacionCreditoEntity simulacion = new SimulacionCreditoEntity();
        simulacion.setIdCliente(idCliente);
        simulacion.setMontoSolicitado(monto);
        simulacion.setPlazoSolicitado(plazo);
        simulacion.setTasaInteres(tasaInteres);
        simulacion.setCuotaMensual(cuotaMensual);
        simulacion.setFechaSimulacion(LocalDate.now());

        return simulacionCreditoRepository.save(simulacion);
    }
}
