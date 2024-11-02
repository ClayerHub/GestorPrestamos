package services;

import entities.PrestamoEntidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.PrestamoRepositorio;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class PrestamoServicio {

    @Autowired
    PrestamoRepositorio prestamoRepositorio;

    public ArrayList<PrestamoEntidad> getPrestamos(){
        return(ArrayList<PrestamoEntidad>) prestamoRepositorio.findAll();
    }

    public PrestamoEntidad savePrestamo(PrestamoEntidad prestamo) {return prestamoRepositorio.save(prestamo);}

    public PrestamoEntidad registerLoanApplication(PrestamoEntidad prestamo) {
        prestamo.setFechaSolicitud(LocalDate.now());
        prestamo.setEstado("En Revisión");

        return savePrestamo(prestamo);
    }

    public PrestamoEntidad getPrestamoById(Long id){ return prestamoRepositorio.findById(id).get();}

    public PrestamoEntidad updatePrestamo(PrestamoEntidad prestamo){return prestamoRepositorio.save(prestamo);}

    public boolean deletePrestamo(Long id)throws Exception{
        try{
            prestamoRepositorio.deleteById(id);
            return true;
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }
}
