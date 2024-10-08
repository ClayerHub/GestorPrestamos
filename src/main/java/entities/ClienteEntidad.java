package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String rut;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private Date fechaRegistro;
    private String tipoDocumento;
    private String numeroDocumento;
    private String direccion;

}
