package com.gestorprestamos.controllers;

import com.gestorprestamos.entities.HistorialCreditoEntidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gestorprestamos.services.HistorialCreditoServicio;

import java.util.List;

@RestController
@RequestMapping("/pb/historial-creditos")
@CrossOrigin("*")
public class HistorialCreditoControlador {

    @Autowired
    HistorialCreditoServicio historialCreditoServicio;

    @GetMapping("/FindAll")
    public ResponseEntity<List<HistorialCreditoEntidad>> listHistorialCreditos(){
        List<HistorialCreditoEntidad> historialCreditos = historialCreditoServicio.getHistorialCreditos();
        return ResponseEntity.ok(historialCreditos);
    }
    @GetMapping("Find-{id}")
    public ResponseEntity<HistorialCreditoEntidad> getHistorialCreditoById(@PathVariable Long id){
        HistorialCreditoEntidad historialCredito = historialCreditoServicio.getHistorialCreditoById(id);
        return ResponseEntity.ok(historialCredito);
    }
    @PostMapping("/Save")
    public ResponseEntity<HistorialCreditoEntidad> saveHistorialCredito(@RequestBody HistorialCreditoEntidad historialCredito){
        HistorialCreditoEntidad nuevoHistorialCredito = historialCreditoServicio.saveHistorialCredito(historialCredito);
        return ResponseEntity.ok(nuevoHistorialCredito);
    }
    @PutMapping("/Update")
    public ResponseEntity<HistorialCreditoEntidad> updateHistorialCredito(@RequestBody HistorialCreditoEntidad historialCredito){
        HistorialCreditoEntidad historialCreditoActualizado = historialCreditoServicio.updateHistorialCredito(historialCredito);
        return ResponseEntity.ok(historialCreditoActualizado);
    }
    @DeleteMapping("/Delete-{id}")
    public ResponseEntity<Boolean> deleteHistorialCreditoById(@PathVariable Long id) throws Exception{
        var eliminado = historialCreditoServicio.deleteHistorialCredito(id);
        return ResponseEntity.noContent().build();
    }
}
