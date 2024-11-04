package com.gestorprestamos.controllers;

import com.gestorprestamos.entities.SimulacionCreditoEntidad;
import com.gestorprestamos.services.SimulacionCreditoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pb/simulacion-creditos")
@CrossOrigin("*")
public class SimulacionCreditoControlador {

    @Autowired
    SimulacionCreditoServicio simulacionCreditoServicio;

    @GetMapping("/FindAll")
    public ResponseEntity<List<SimulacionCreditoEntidad>> listSimulacionCreditos(){
        List<SimulacionCreditoEntidad> simulacionCreditos = simulacionCreditoServicio.getSimulacionCreditos();
        return ResponseEntity.ok(simulacionCreditos);
    }
    @GetMapping("Find-{id}")
    public ResponseEntity<SimulacionCreditoEntidad> getSimulacionCreditoById(@PathVariable Long id){
        SimulacionCreditoEntidad simulacionCredito = simulacionCreditoServicio.getSimulacionCreditoById(id);
        return ResponseEntity.ok(simulacionCredito);
    }
    @PostMapping("/Save")
    public ResponseEntity<SimulacionCreditoEntidad> saveSimulacionCredito(@RequestBody SimulacionCreditoEntidad simulacionCredito){
        SimulacionCreditoEntidad nuevaSimulacionCredito = simulacionCreditoServicio.saveSimulacionCredito(simulacionCredito);
        return ResponseEntity.ok(nuevaSimulacionCredito);
    }
    @PostMapping("/Simulation-Calculate")
    public SimulacionCreditoEntidad calculateSimulation(@RequestParam int idCliente, @RequestParam double monto,
            @RequestParam int plazo, @RequestParam double tasaInteres) {
        return simulacionCreditoServicio.calculateSimulation(idCliente, monto, plazo, tasaInteres);
    }
    @PutMapping("/Update")
    public ResponseEntity<SimulacionCreditoEntidad> updateSimulacionCredito(@RequestBody SimulacionCreditoEntidad simulacionCredito){
        SimulacionCreditoEntidad simulacionCreditoActualizado = simulacionCreditoServicio.updateSimulacionCredito(simulacionCredito);
        return ResponseEntity.ok(simulacionCreditoActualizado);
    }
    @DeleteMapping("/Delete-{id}")
    public ResponseEntity<SimulacionCreditoEntidad> deleteSimulacionCreditoById(@PathVariable Long id)throws Exception{
        var eliminado = simulacionCreditoServicio.deleteSimulacionCredito(id);
        return ResponseEntity.noContent().build();
    }

}