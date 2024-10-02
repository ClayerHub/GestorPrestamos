package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "documento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentoEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private int idPrestamo;
    private String tipoDocumento;
    private String rutaArchivo;
    private Date fechaCarga;

}
