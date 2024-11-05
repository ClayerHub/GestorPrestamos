package com.gestorprestamos.controllers;

import com.gestorprestamos.entities.SimulacionCreditoEntity;
import com.gestorprestamos.services.SimulacionCreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/simulacion-creditos")
@CrossOrigin("*")
public class SimulacionCreditoController {

    @Autowired
    SimulacionCreditoService simulacionCreditoService;

    @GetMapping("/find-all")
    public ResponseEntity<List<SimulacionCreditoEntity>> listSimulacionCreditos(){
        List<SimulacionCreditoEntity> simulacionCreditos = simulacionCreditoService.getSimulacionCreditos();
        return ResponseEntity.ok(simulacionCreditos);
    }
    @GetMapping("find-{id}")
    public ResponseEntity<?> getSimulacionCreditoById(@PathVariable Long id){
        try {
            SimulacionCreditoEntity simulacionCredito = simulacionCreditoService.getSimulacionCreditoById(id);
            return ResponseEntity.ok(simulacionCredito);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Simulación de crédito no encontrada.");
        }
    }

    @PostMapping("/save")
    public ResponseEntity<SimulacionCreditoEntity> saveSimulacionCredito(@RequestBody SimulacionCreditoEntity simulacionCredito){
        SimulacionCreditoEntity nuevaSimulacionCredito = simulacionCreditoService.saveSimulacionCredito(simulacionCredito);
        return ResponseEntity.ok(nuevaSimulacionCredito);
    }
    @PostMapping("/calculate")
    public ResponseEntity<?> calculateSimulation(@RequestBody SimulacionCreditoEntity simulacionCredito) {
        try {
            SimulacionCreditoEntity simulacionCalculada = simulacionCreditoService.calculateSimulation(
                    simulacionCredito.getIdCliente(),
                    simulacionCredito.getMontoSolicitado(),
                    simulacionCredito.getPlazoSolicitado(),
                    simulacionCredito.getTasaInteres()
            );
            return ResponseEntity.ok(simulacionCalculada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en el cálculo.");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<SimulacionCreditoEntity> updateSimulacionCredito(@RequestBody SimulacionCreditoEntity simulacionCredito){
        SimulacionCreditoEntity simulacionCreditoActualizado = simulacionCreditoService.updateSimulacionCredito(simulacionCredito);
        return ResponseEntity.ok(simulacionCreditoActualizado);
    }
    @DeleteMapping("/delete-{id}")
    public ResponseEntity<String> deleteSimulacionCreditoById(@PathVariable Long id) throws Exception {
        var eliminado = simulacionCreditoService.deleteSimulacionCredito(id);
        return ResponseEntity.ok("Simulación de crédito eliminada exitosamente.");
    }

}
