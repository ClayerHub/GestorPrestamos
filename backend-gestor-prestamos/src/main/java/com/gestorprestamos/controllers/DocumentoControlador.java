package com.gestorprestamos.controllers;

import com.gestorprestamos.entities.DocumentoEntidad;
import com.gestorprestamos.services.DocumentoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pb/documentos")
@CrossOrigin("*")
public class DocumentoControlador {

    @Autowired
    DocumentoServicio documentoServicio;

    @GetMapping("/FindAll")
    public ResponseEntity<List<DocumentoEntidad>> listDocumentos(){
        List<DocumentoEntidad> documentos = documentoServicio.getDocumentos();
        return ResponseEntity.ok(documentos);
    }
    @GetMapping("Find-{id}")
    public ResponseEntity<DocumentoEntidad> getDocumentoById(@PathVariable Long id){
        DocumentoEntidad documento = documentoServicio.getDocumentoById(id);
        return ResponseEntity.ok(documento);
    }
    @PostMapping("/Save")
    public ResponseEntity<DocumentoEntidad> saveDocumento(@RequestBody DocumentoEntidad documento){
        DocumentoEntidad nuevoDocumento = documentoServicio.saveDocumento(documento);
        return ResponseEntity.ok(nuevoDocumento);
    }
    @PutMapping("/Update")
    public ResponseEntity<DocumentoEntidad> updateDocumento(@RequestBody DocumentoEntidad documento){
        DocumentoEntidad documentoActualizado = documentoServicio.updateDocumento(documento);
        return ResponseEntity.ok(documentoActualizado);
    }
    @DeleteMapping("/Delete-{id}")
    public ResponseEntity<Boolean> deleteDocumentoById(@PathVariable Long id) throws Exception{
        var eliminado = documentoServicio.deleteDocumento(id);
        return ResponseEntity.noContent().build();
    }
}
