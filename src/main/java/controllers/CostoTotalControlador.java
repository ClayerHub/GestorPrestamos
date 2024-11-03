package controllers;

import services.CostoTotalServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pb/costo-total")
@CrossOrigin("*")
public class CostoTotalControlador {

    @Autowired
    CostoTotalServicio costoTotalServicio;

    @GetMapping("/calculate")
    public ResponseEntity<Double> totalCostCalculate(@RequestParam double monto, @RequestParam int plazo,
                                                     @RequestParam double tasaInteres){
        try{
            double costoTotal = costoTotalServicio.totalCostCalculate(monto, plazo, tasaInteres);
            return ResponseEntity.ok(costoTotal);
        } catch (Exception error){
            return ResponseEntity.badRequest().body(Double.valueOf("Error al calcular costo Total"));
        }
    }
}
