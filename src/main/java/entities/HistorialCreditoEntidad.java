package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "historialCredito")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistorialCreditoEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private int idCliente;
    private String estadoCredito;
    private String descripcion;
    private Date fechaActualizacion;

}
