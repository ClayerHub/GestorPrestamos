package com.gestorprestamos.controllers;

import com.gestorprestamos.entities.EvaluacionCreditoEntity;
import com.gestorprestamos.services.EvaluacionCreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluacion-creditos")
@CrossOrigin("*")
public class EvaluacionCreditoController {

    @Autowired
    EvaluacionCreditoService evaluacionCreditoService;

    @GetMapping("/findAll")
    public ResponseEntity<List<EvaluacionCreditoEntity>> listEvaluacionCreditos(){
        List<EvaluacionCreditoEntity> evaluacionCreditos = evaluacionCreditoService.getEvaluacionCreditos();
        return ResponseEntity.ok(evaluacionCreditos);
    }
    @GetMapping("find-{id}")
    public ResponseEntity<EvaluacionCreditoEntity> getEvaluacionCreditoById(@PathVariable Long id){
        EvaluacionCreditoEntity evaluacionCredito = evaluacionCreditoService.getEvaluacionCreditoById(id);
        return ResponseEntity.ok(evaluacionCredito);
    }
    @PostMapping("/save")
    public ResponseEntity<EvaluacionCreditoEntity> saveEvaluacionCredito(@RequestBody EvaluacionCreditoEntity evaluacionCredito){
        EvaluacionCreditoEntity nuevaEvaluacionCredito = evaluacionCreditoService.saveEvaluacionCredito(evaluacionCredito);
        return ResponseEntity.ok(nuevaEvaluacionCredito);
    }
    @PostMapping("/evaluate")
    public EvaluacionCreditoEntity creditEvaluate(@RequestBody EvaluacionCreditoEntity evaluacion) {
        return evaluacionCreditoService.requestEvaluation(evaluacion);
    }
    @PutMapping("/update")
    public ResponseEntity<EvaluacionCreditoEntity> updateEvaluacionCredito(@RequestBody EvaluacionCreditoEntity evaluacionCredito){
        EvaluacionCreditoEntity evaluacionCreditoActualizado = evaluacionCreditoService.updateEvaluacionCredito(evaluacionCredito);
        return ResponseEntity.ok(evaluacionCreditoActualizado);
    }
    @DeleteMapping("/delete-{id}")
    public ResponseEntity<Boolean> deleteEvaluacionCreditoById(@PathVariable Long id) throws Exception{
        var eliminado = evaluacionCreditoService.deleteEvaluacionCredito(id);
        return ResponseEntity.noContent().build();
    }
}
