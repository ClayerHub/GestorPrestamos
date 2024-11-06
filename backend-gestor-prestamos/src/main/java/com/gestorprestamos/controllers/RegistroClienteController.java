package com.gestorprestamos.controllers;

import com.gestorprestamos.entities.RegistroClienteEntity;
import com.gestorprestamos.services.RegistroClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin("*")
public class RegistroClienteController {

    @Autowired
    RegistroClienteService clienteService;

    @GetMapping("/find-all")
    public ResponseEntity<List<RegistroClienteEntity>> listClientes(){
        List<RegistroClienteEntity> clientes = clienteService.getClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/find-{id}")
    public ResponseEntity<RegistroClienteEntity> getClienteById(@PathVariable Long id){
        RegistroClienteEntity cliente = clienteService.getClienteById(id);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping("/save")
    public ResponseEntity<RegistroClienteEntity> saveCliente(@RequestBody RegistroClienteEntity cliente){
        RegistroClienteEntity nuevoCliente = clienteService.saveCliente(cliente);
        return ResponseEntity.ok(nuevoCliente);
    }

    @PostMapping("/register")
    public RegistroClienteEntity registerCliente(@RequestBody RegistroClienteEntity cliente) {
        return clienteService.registrarCliente(cliente);
    }

    @PutMapping("/update")
    public ResponseEntity<RegistroClienteEntity> updateCliente(@RequestBody RegistroClienteEntity cliente){
        RegistroClienteEntity clienteActualizado = clienteService.updateCliente(cliente);
        return ResponseEntity.ok(clienteActualizado);
    }

    @DeleteMapping("/delete-{id}")
    public ResponseEntity<Boolean> deleteClienteById(@PathVariable Long id) throws Exception{
        var eliminado = clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }
}
