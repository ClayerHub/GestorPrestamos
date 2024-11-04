package com.gestorprestamos.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "evaluacionCredito")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvaluacionCreditoEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private int idPrestamo;
    private double cuotaIngreso;
    private String historialCredito;
    private int antiguedadLaboral;
    private double deudaIngreso;
    private double montoFinanciamiento;
    private double capacidadAhorro;
    private String resultado;

}