package com.gestorprestamos.services;

import com.gestorprestamos.entities.RegistroClienteEntity;
import com.gestorprestamos.repositories.RegistroClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class RegistroClienteService {

    @Autowired
    RegistroClienteRepository clienteRepository;

    public ArrayList<RegistroClienteEntity> getClientes(){ return (ArrayList<RegistroClienteEntity>) clienteRepository.findAll();}

    public RegistroClienteEntity saveCliente(RegistroClienteEntity cliente) { return clienteRepository.save(cliente);}
    public RegistroClienteEntity registrarCliente(RegistroClienteEntity cliente){cliente.setFechaRegistro(LocalDate.now()); return saveCliente(cliente);}

    public RegistroClienteEntity getClienteById(Long id) {return clienteRepository.findById(id).get();}

    public RegistroClienteEntity getClienteByRut(String rut) {return clienteRepository.findByRut(rut);}

    public RegistroClienteEntity updateCliente(RegistroClienteEntity cliente) { return clienteRepository.save(cliente);}

    public boolean deleteCliente(Long id) throws Exception {
        try{
            clienteRepository.deleteById(id);
            return true;
        }
        catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

}
