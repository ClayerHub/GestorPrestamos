package com.gestorprestamos.controllers;

import com.gestorprestamos.entities.PrestamoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gestorprestamos.services.PrestamoService;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
@CrossOrigin("*")
public class PrestamoController {

    @Autowired
    PrestamoService prestamoService;

    @GetMapping("/find-all")
    public ResponseEntity<List<PrestamoEntity>> listPrestamos(){
        List<PrestamoEntity> prestamos = prestamoService.getPrestamos();
        return ResponseEntity.ok(prestamos);
    }
    @GetMapping("find-{id}")
    public ResponseEntity<PrestamoEntity> getPrestamoById(@PathVariable Long id){
        PrestamoEntity prestamo = prestamoService.getPrestamoById(id);
        return ResponseEntity.ok(prestamo);
    }
    @PostMapping("/save")
    public ResponseEntity<PrestamoEntity> savePrestamo(@RequestBody PrestamoEntity prestamo){
        PrestamoEntity nuevoPrestamo = prestamoService.savePrestamo(prestamo);
        return ResponseEntity.ok(nuevoPrestamo);
    }
    @PostMapping("/request")
    public PrestamoEntity loanRequest(@RequestBody PrestamoEntity prestamo) {
        return prestamoService.registerLoanApplication(prestamo);
    }
    @PutMapping("/update")
    public ResponseEntity<PrestamoEntity> updatePrestamo(@RequestBody PrestamoEntity prestamo){
        PrestamoEntity prestamoActualizado = prestamoService.updatePrestamo(prestamo);
        return ResponseEntity.ok(prestamoActualizado);
    }
    @DeleteMapping("/delete-{id}")
    public ResponseEntity<PrestamoEntity> deletePrestamoById(@PathVariable Long id) throws Exception{
        var eliminado = prestamoService.deletePrestamo(id);
        return ResponseEntity.noContent().build();
    }
}
