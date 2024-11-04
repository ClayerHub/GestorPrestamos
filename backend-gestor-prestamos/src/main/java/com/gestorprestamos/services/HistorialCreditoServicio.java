package com.gestorprestamos.services;

import com.gestorprestamos.entities.HistorialCreditoEntidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gestorprestamos.repositories.HistorialCreditoRepositorio;

import java.util.ArrayList;

@Service
public class HistorialCreditoServicio {

    @Autowired
    HistorialCreditoRepositorio historialCreditoRepositorio;

    public ArrayList<HistorialCreditoEntidad> getHistorialCreditos(){
        return(ArrayList<HistorialCreditoEntidad>) historialCreditoRepositorio.findAll();
    }

    public HistorialCreditoEntidad saveHistorialCredito(HistorialCreditoEntidad historialCredito){ return historialCreditoRepositorio.save(historialCredito);}

    public HistorialCreditoEntidad getHistorialCreditoById(Long id) { return historialCreditoRepositorio.findById(id).get();}

    public HistorialCreditoEntidad updateHistorialCredito(HistorialCreditoEntidad historialCredito){ return historialCreditoRepositorio.save(historialCredito);}

    public boolean deleteHistorialCredito(Long id) throws Exception{
        try{
            historialCreditoRepositorio.deleteById(id);
            return true;
        }catch(Exception error){
            throw new Exception(error.getMessage());
        }
    }
}
