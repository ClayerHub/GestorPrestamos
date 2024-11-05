package com.gestorprestamos.services;

import com.gestorprestamos.entities.ClienteEntity;
import com.gestorprestamos.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public ArrayList<ClienteEntity> getClientes(){ return (ArrayList<ClienteEntity>) clienteRepository.findAll();}

    public ClienteEntity saveCliente(ClienteEntity cliente) { return clienteRepository.save(cliente);}
    public ClienteEntity registrarCliente(ClienteEntity cliente){cliente.setFechaRegistro(LocalDate.now()); return saveCliente(cliente);}

    public ClienteEntity getClienteById(Long id) {return clienteRepository.findById(id).get();}

    public ClienteEntity getClienteByRut(String rut) {return clienteRepository.findByRut(rut);}

    public ClienteEntity updateCliente(ClienteEntity cliente) { return clienteRepository.save(cliente);}

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
