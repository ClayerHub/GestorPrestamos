package services;

import entities.ClienteEntidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.ClienteRepositorio;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class ClienteServicio {

    @Autowired
    ClienteRepositorio clienteRepositorio;

    public ArrayList<ClienteEntidad> getClientes(){ return (ArrayList<ClienteEntidad>) clienteRepositorio.findAll();}

    public ClienteEntidad saveCliente(ClienteEntidad cliente) { return clienteRepositorio.save(cliente);}
    public ClienteEntidad registrarCliente(ClienteEntidad cliente){cliente.setFechaRegistro(LocalDate.now()); return saveCliente(cliente);}

    public ClienteEntidad getClienteById(Long id) {return clienteRepositorio.findById(id).get();}

    public ClienteEntidad getClienteByRut(String rut) {return clienteRepositorio.findByRut(rut);}

    public ClienteEntidad updateCliente(ClienteEntidad cliente) { return clienteRepositorio.save(cliente);}

    public boolean deleteCliente(Long id) throws Exception {
        try{
            clienteRepositorio.deleteById(id);
            return true;
        }
        catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

}
