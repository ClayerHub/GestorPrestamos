package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

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
    private float cuotaIngreso;
    private String historialCredito;
    private int antiguedadLaboral;
    private float deudaIngreso;
    private float montoFinanciamiento;
    private float capacidadAhorro;
    private String resultado;

}
