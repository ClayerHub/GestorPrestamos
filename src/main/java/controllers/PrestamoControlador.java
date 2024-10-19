package controllers;

import entities.PrestamoEntidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.PrestamoRepositorio;
import services.PrestamoServicio;

import java.util.List;

@RestController
@RequestMapping("/pb/prestamos")
@CrossOrigin("*")
public class PrestamoControlador {

    @Autowired
    PrestamoServicio prestamoServicio;

    @GetMapping("/FindAll")
    public ResponseEntity<List<PrestamoEntidad>> listPrestamos(){
        List<PrestamoEntidad> prestamos = prestamoServicio.getPrestamos();
        return ResponseEntity.ok(prestamos);
    }
    @GetMapping("Find-{id}")
    public ResponseEntity<PrestamoEntidad> getPrestamoById(@PathVariable Long id){
        PrestamoEntidad prestamo = prestamoServicio.getPrestamoById(id);
        return ResponseEntity.ok(prestamo);
    }
    @PostMapping("/Save")
    public ResponseEntity<PrestamoEntidad> savePrestamo(@RequestBody PrestamoEntidad prestamo){
        PrestamoEntidad nuevoPrestamo = prestamoServicio.savePrestamo(prestamo);
        return ResponseEntity.ok(nuevoPrestamo);
    }
    @PutMapping("/Update")
    public ResponseEntity<PrestamoEntidad> updatePrestamo(@RequestBody PrestamoEntidad prestamo){
        PrestamoEntidad prestamoActualizado = prestamoServicio.updatePrestamo(prestamo);
        return ResponseEntity.ok(prestamoActualizado);
    }
    @DeleteMapping("/Delete-{id}")
    public ResponseEntity<PrestamoEntidad> deletePrestamoById(@PathVariable Long id) throws Exception{
        var eliminado = prestamoServicio.deletePrestamo(id);
        return ResponseEntity.noContent().build();
    }
}
