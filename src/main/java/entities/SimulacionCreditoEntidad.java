package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.math.BigDecimal;
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
    private BigDecimal montoSolicitado;
    private int plazoSolicitado;
    private BigDecimal tasaInteres;
    private BigDecimal cuotaMensual;
    private Date fechaSimulacion;

}
