package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.Date;

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
    private float montoSolicitado;
    private int plazoSolicitado;
    private float tasaInteres;
    private float cuotaMensual;
    private Date fechaSimulacion;

}
