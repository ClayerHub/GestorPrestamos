package com.gestorprestamos.services;

import com.gestorprestamos.entities.HistorialCreditoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gestorprestamos.repositories.HistorialCreditoRepository;

import java.util.ArrayList;

@Service
public class HistorialCreditoService {

    @Autowired
    HistorialCreditoRepository historialCreditoRepository;

    public ArrayList<HistorialCreditoEntity> getHistorialCreditos(){
        return(ArrayList<HistorialCreditoEntity>) historialCreditoRepository.findAll();
    }

    public HistorialCreditoEntity saveHistorialCredito(HistorialCreditoEntity historialCredito){ return historialCreditoRepository.save(historialCredito);}

    public HistorialCreditoEntity getHistorialCreditoById(Long id) { return historialCreditoRepository.findById(id).get();}

    public HistorialCreditoEntity updateHistorialCredito(HistorialCreditoEntity historialCredito){ return historialCreditoRepository.save(historialCredito);}

    public boolean deleteHistorialCredito(Long id) throws Exception{
        try{
            historialCreditoRepository.deleteById(id);
            return true;
        }catch(Exception error){
            throw new Exception(error.getMessage());
        }
    }
}
