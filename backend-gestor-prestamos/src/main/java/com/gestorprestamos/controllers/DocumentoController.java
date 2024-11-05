package com.gestorprestamos.controllers;

import com.gestorprestamos.entities.DocumentoEntity;
import com.gestorprestamos.services.DocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pb/documentos")
@CrossOrigin("*")
public class DocumentoController {

    @Autowired
    DocumentoService documentoService;

    @GetMapping("/FindAll")
    public ResponseEntity<List<DocumentoEntity>> listDocumentos(){
        List<DocumentoEntity> documentos = documentoService.getDocumentos();
        return ResponseEntity.ok(documentos);
    }
    @GetMapping("Find-{id}")
    public ResponseEntity<DocumentoEntity> getDocumentoById(@PathVariable Long id){
        DocumentoEntity documento = documentoService.getDocumentoById(id);
        return ResponseEntity.ok(documento);
    }
    @PostMapping("/Save")
    public ResponseEntity<DocumentoEntity> saveDocumento(@RequestBody DocumentoEntity documento){
        DocumentoEntity nuevoDocumento = documentoService.saveDocumento(documento);
        return ResponseEntity.ok(nuevoDocumento);
    }
    @PutMapping("/Update")
    public ResponseEntity<DocumentoEntity> updateDocumento(@RequestBody DocumentoEntity documento){
        DocumentoEntity documentoActualizado = documentoService.updateDocumento(documento);
        return ResponseEntity.ok(documentoActualizado);
    }
    @DeleteMapping("/Delete-{id}")
    public ResponseEntity<Boolean> deleteDocumentoById(@PathVariable Long id) throws Exception{
        var eliminado = documentoService.deleteDocumento(id);
        return ResponseEntity.noContent().build();
    }
}
