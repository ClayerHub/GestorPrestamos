package com.gestorprestamos.controllers;

import com.gestorprestamos.entities.HistorialCreditoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gestorprestamos.services.HistorialCreditoService;

import java.util.List;

@RestController
@RequestMapping("/api/historial-creditos")
@CrossOrigin("*")
public class HistorialCreditoController {

    @Autowired
    HistorialCreditoService historialCreditoService;

    @GetMapping("/find-all")
    public ResponseEntity<List<HistorialCreditoEntity>> listHistorialCreditos(){
        List<HistorialCreditoEntity> historialCreditos = historialCreditoService.getHistorialCreditos();
        return ResponseEntity.ok(historialCreditos);
    }
    @GetMapping("find-{id}")
    public ResponseEntity<HistorialCreditoEntity> getHistorialCreditoById(@PathVariable Long id){
        HistorialCreditoEntity historialCredito = historialCreditoService.getHistorialCreditoById(id);
        return ResponseEntity.ok(historialCredito);
    }
    @PostMapping("/save")
    public ResponseEntity<HistorialCreditoEntity> saveHistorialCredito(@RequestBody HistorialCreditoEntity historialCredito){
        HistorialCreditoEntity nuevoHistorialCredito = historialCreditoService.saveHistorialCredito(historialCredito);
        return ResponseEntity.ok(nuevoHistorialCredito);
    }
    @PutMapping("/update")
    public ResponseEntity<HistorialCreditoEntity> updateHistorialCredito(@RequestBody HistorialCreditoEntity historialCredito){
        HistorialCreditoEntity historialCreditoActualizado = historialCreditoService.updateHistorialCredito(historialCredito);
        return ResponseEntity.ok(historialCreditoActualizado);
    }
    @DeleteMapping("/delete-{id}")
    public ResponseEntity<Boolean> deleteHistorialCreditoById(@PathVariable Long id) throws Exception{
        var eliminado = historialCreditoService.deleteHistorialCredito(id);
        return ResponseEntity.noContent().build();
    }
}
