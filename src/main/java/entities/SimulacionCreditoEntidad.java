package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "simulacionCredito")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimulacionCreditoEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private int idCliente;
    private double montoSolicitado;
    private int plazoSolicitado;
    private double tasaInteres;
    private double cuotaMensual;
    private LocalDate fechaSimulacion;

}
