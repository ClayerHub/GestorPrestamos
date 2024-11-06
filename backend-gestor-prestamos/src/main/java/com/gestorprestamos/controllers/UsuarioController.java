package com.gestorprestamos.controllers;

import com.gestorprestamos.entities.UsuarioEntity;
import com.gestorprestamos.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/find-all")
    public ResponseEntity<List<UsuarioEntity>> listUsuarios(){
        List<UsuarioEntity> usuarios = usuarioService.getUsuarios();
        return ResponseEntity.ok(usuarios);
    }
    @GetMapping("id/{id}")
    public ResponseEntity<UsuarioEntity> getUsuarioById(@PathVariable Long id){
        UsuarioEntity usuario = usuarioService.getUsuarioById(id);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("rut/{rut}")
    public ResponseEntity<UsuarioEntity> getUsuarioByRut(@PathVariable String rut) {
        try {
            UsuarioEntity usuario = usuarioService.getUsuarioByRut(rut);
            if (usuario == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            // Registra el error y devuelve una respuesta con un código 500
            System.err.println("Error en la búsqueda del usuario: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Object> saveUsuario(@RequestBody UsuarioEntity usuario) {
        try {
            // Asigna el rol "cliente" automáticamente antes de guardar el usuario
            if (usuario.getRol() == null || usuario.getRol().isEmpty()) {
                usuario.setRol("cliente");  // Asignar rol por defecto
            }

            // Guarda el usuario con el rol asignado
            UsuarioEntity nuevoUsuario = usuarioService.saveUsuario(usuario);
            return ResponseEntity.ok(nuevoUsuario);
        } catch (RuntimeException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "El usuario con este RUT ya está registrado.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<UsuarioEntity> updateUsuario(@RequestBody UsuarioEntity usuario){
        UsuarioEntity usuarioActualizado = usuarioService.updateUsuario(usuario);
        return ResponseEntity.ok(usuarioActualizado);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteUsuarioById(@PathVariable Long id) throws Exception{
        var eliminado = usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
