package com.gestorprestamos.services;

import com.gestorprestamos.entities.DocumentoEntidad;
import com.gestorprestamos.repositories.DocumentoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Service
public class DocumentoServicio {

    @Autowired
    DocumentoRepositorio documentoRepositorio;

    public ArrayList<DocumentoEntidad> getDocumentos(){ return (ArrayList<DocumentoEntidad>) documentoRepositorio.findAll();}

    public DocumentoEntidad saveDocumento(DocumentoEntidad documento){ return documentoRepositorio.save(documento);}

    public DocumentoEntidad getDocumentoById(Long id) { return documentoRepositorio.findById(id).get();}

    public List<DocumentoEntidad> getDocumentoByRut(String rut){ return (List<DocumentoEntidad>)documentoRepositorio.findByRut(rut);}

    public List<DocumentoEntidad> getDocumentoByDate(Date date){ return (List<DocumentoEntidad>)documentoRepositorio.findByDate(date);}
    public DocumentoEntidad updateDocumento(DocumentoEntidad documento) {return documentoRepositorio.save(documento);}

    public boolean deleteDocumento(Long id) throws Exception {
        try{
            documentoRepositorio.deleteById(id);
            return true;
        } catch(Exception error){
            throw new Exception(error.getMessage());
        }
    }
}
