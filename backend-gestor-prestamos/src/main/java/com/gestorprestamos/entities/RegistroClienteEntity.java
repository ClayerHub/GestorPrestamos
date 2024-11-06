package com.gestorprestamos.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "registroCliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistroClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private String rut;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private LocalDate fechaRegistro;
    private String tipoDocumento;
    private String numeroDocumento;
    private String direccion;

}
