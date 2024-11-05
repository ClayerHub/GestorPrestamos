package com.gestorprestamos.controllers;

import com.gestorprestamos.entities.ClienteEntity;
import com.gestorprestamos.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin("*")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping("/find-all")
    public ResponseEntity<List<ClienteEntity>> listClientes(){
        List<ClienteEntity> clientes = clienteService.getClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/find-{id}")
    public ResponseEntity<ClienteEntity> getClienteById(@PathVariable Long id){
        ClienteEntity cliente = clienteService.getClienteById(id);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping("/save")
    public ResponseEntity<ClienteEntity> saveCliente(@RequestBody ClienteEntity cliente){
        ClienteEntity nuevoCliente = clienteService.saveCliente(cliente);
        return ResponseEntity.ok(nuevoCliente);
    }

    @PostMapping("/register")
    public ClienteEntity registerCliente(@RequestBody ClienteEntity cliente) {
        return clienteService.registrarCliente(cliente);
    }

    @PutMapping("/update")
    public ResponseEntity<ClienteEntity> updateCliente(@RequestBody ClienteEntity cliente){
        ClienteEntity clienteActualizado = clienteService.updateCliente(cliente);
        return ResponseEntity.ok(clienteActualizado);
    }

    @DeleteMapping("/delete-{id}")
    public ResponseEntity<Boolean> deleteClienteById(@PathVariable Long id) throws Exception{
        var eliminado = clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }
}
