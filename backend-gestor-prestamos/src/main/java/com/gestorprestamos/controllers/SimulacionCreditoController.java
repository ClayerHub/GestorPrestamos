package com.gestorprestamos.controllers;

import com.gestorprestamos.entities.SimulacionCreditoEntity;
import com.gestorprestamos.services.SimulacionCreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<SimulacionCreditoEntity> getSimulacionCreditoById(@PathVariable Long id){
        SimulacionCreditoEntity simulacionCredito = simulacionCreditoService.getSimulacionCreditoById(id);
        return ResponseEntity.ok(simulacionCredito);
    }
    @PostMapping("/save")
    public ResponseEntity<SimulacionCreditoEntity> saveSimulacionCredito(@RequestBody SimulacionCreditoEntity simulacionCredito){
        SimulacionCreditoEntity nuevaSimulacionCredito = simulacionCreditoService.saveSimulacionCredito(simulacionCredito);
        return ResponseEntity.ok(nuevaSimulacionCredito);
    }
    @PostMapping("/simulation-calculate")
    public SimulacionCreditoEntity calculateSimulation(@RequestParam int idCliente, @RequestParam double monto,
                                                       @RequestParam int plazo, @RequestParam double tasaInteres) {
        return simulacionCreditoService.calculateSimulation(idCliente, monto, plazo, tasaInteres);
    }
    @PutMapping("/update")
    public ResponseEntity<SimulacionCreditoEntity> updateSimulacionCredito(@RequestBody SimulacionCreditoEntity simulacionCredito){
        SimulacionCreditoEntity simulacionCreditoActualizado = simulacionCreditoService.updateSimulacionCredito(simulacionCredito);
        return ResponseEntity.ok(simulacionCreditoActualizado);
    }
    @DeleteMapping("/delete-{id}")
    public ResponseEntity<SimulacionCreditoEntity> deleteSimulacionCreditoById(@PathVariable Long id)throws Exception{
        var eliminado = simulacionCreditoService.deleteSimulacionCredito(id);
        return ResponseEntity.noContent().build();
    }

}
