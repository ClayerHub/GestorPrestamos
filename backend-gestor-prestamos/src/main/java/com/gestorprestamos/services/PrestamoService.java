package com.gestorprestamos.services;

import com.gestorprestamos.entities.PrestamoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gestorprestamos.repositories.PrestamoRepository;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class PrestamoService {

    @Autowired
    PrestamoRepository prestamoRepository;

    public ArrayList<PrestamoEntity> getPrestamos(){
        return(ArrayList<PrestamoEntity>) prestamoRepository.findAll();
    }

    public PrestamoEntity savePrestamo(PrestamoEntity prestamo) {return prestamoRepository.save(prestamo);}

    public PrestamoEntity registerLoanApplication(PrestamoEntity prestamo) {
        prestamo.setFechaSolicitud(LocalDate.now());
        prestamo.setEstado("En Revisión");

        return savePrestamo(prestamo);
    }

    public PrestamoEntity getPrestamoById(Long id){ return prestamoRepository.findById(id).get();}

    public PrestamoEntity updatePrestamo(PrestamoEntity prestamo){return prestamoRepository.save(prestamo);}

    public boolean deletePrestamo(Long id)throws Exception{
        try{
            prestamoRepository.deleteById(id);
            return true;
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }
}
