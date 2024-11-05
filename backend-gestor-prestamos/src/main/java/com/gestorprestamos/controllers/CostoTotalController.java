package com.gestorprestamos.controllers;

import com.gestorprestamos.services.CostoTotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pb/costo-total")
@CrossOrigin("*")
public class CostoTotalController {

    @Autowired
    CostoTotalService costoTotalService;

    @GetMapping("/calculate")
    public ResponseEntity<Double> totalCostCalculate(@RequestParam double monto, @RequestParam int plazo,
                                                     @RequestParam double tasaInteres){
        try{
            double costoTotal = costoTotalService.totalCostCalculate(monto, plazo, tasaInteres);
            return ResponseEntity.ok(costoTotal);
        } catch (Exception error){
            return ResponseEntity.badRequest().body(Double.valueOf("Error al calcular costo Total"));
        }
    }
}
