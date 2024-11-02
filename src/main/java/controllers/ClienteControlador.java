package controllers;

import entities.ClienteEntidad;
import services.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pb/clientes")
@CrossOrigin("*")
public class ClienteControlador {

    @Autowired
    ClienteServicio clienteServicio;

    @GetMapping("/FindAll")
    public ResponseEntity<List<ClienteEntidad>> listClientes(){
        List<ClienteEntidad> clientes = clienteServicio.getClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/Find-{id}")
    public ResponseEntity<ClienteEntidad> getClienteById(@PathVariable Long id){
        ClienteEntidad cliente = clienteServicio.getClienteById(id);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping("/Save")
    public ResponseEntity<ClienteEntidad> saveCliente(@RequestBody ClienteEntidad cliente){
        ClienteEntidad nuevoCliente = clienteServicio.saveCliente(cliente);
        return ResponseEntity.ok(nuevoCliente);
    }

    @PostMapping("/Register")
    public ClienteEntidad registerCliente(@RequestBody ClienteEntidad cliente) {
        return clienteServicio.registrarCliente(cliente);
    }

    @PutMapping("/Update")
    public ResponseEntity<ClienteEntidad> updateCliente(@RequestBody ClienteEntidad cliente){
        ClienteEntidad clienteActualizado = clienteServicio.updateCliente(cliente);
        return ResponseEntity.ok(clienteActualizado);
    }

    @DeleteMapping("/Delete-{id}")
    public ResponseEntity<Boolean> deleteClienteById(@PathVariable Long id) throws Exception{
        var eliminado = clienteServicio.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }
}
