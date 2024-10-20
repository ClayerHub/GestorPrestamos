package controllers;

import entities.EvaluacionCreditoEntidad;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.EvaluacionCreditoServicio;

import java.util.List;

@RestController
@RequestMapping("/pb/evaluacion-creditos")
@CrossOrigin("*")
public class EvaluacionCreditoControlador {

    @Autowired
    EvaluacionCreditoServicio evaluacionCreditoServicio;

    @GetMapping("/FindAll")
    public ResponseEntity<List<EvaluacionCreditoEntidad>> listEvaluacionCreditos(){
        List<EvaluacionCreditoEntidad> evaluacionCreditos = evaluacionCreditoServicio.getEvaluacionCreditos();
        return ResponseEntity.ok(evaluacionCreditos);
    }
    @GetMapping("Find-{id}")
    public ResponseEntity<EvaluacionCreditoEntidad> getEvaluacionCreditoById(@PathVariable Long id){
        EvaluacionCreditoEntidad evaluacionCredito = evaluacionCreditoServicio.getEvaluacionCreditoById(id);
        return ResponseEntity.ok(evaluacionCredito);
    }
    @PostMapping("/Save")
    public ResponseEntity<EvaluacionCreditoEntidad> saveEvaluacionCredito(@RequestBody EvaluacionCreditoEntidad evaluacionCredito){
        EvaluacionCreditoEntidad nuevaEvaluacionCredito = evaluacionCreditoServicio.saveEvaluacionCredito(evaluacionCredito);
        return ResponseEntity.ok(nuevaEvaluacionCredito);
    }
    @PutMapping("/Update")
    public ResponseEntity<EvaluacionCreditoEntidad> updateEvaluacionCredito(@RequestBody EvaluacionCreditoEntidad evaluacionCredito){
        EvaluacionCreditoEntidad evaluacionCreditoActualizado = evaluacionCreditoServicio.updateEvaluacionCredito(evaluacionCredito);
        return ResponseEntity.ok(evaluacionCreditoActualizado);
    }
    @DeleteMapping("/Delete-{id}")
    public ResponseEntity<Boolean> deleteEvaluacionCreditoById(@PathVariable Long id) throws Exception{
        var eliminado = evaluacionCreditoServicio.deleteEvaluacionCredito(id);
        return ResponseEntity.noContent().build();
    }
}
