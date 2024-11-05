package com.gestorprestamos.services;

import com.gestorprestamos.entities.DocumentoEntity;
import com.gestorprestamos.repositories.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Service
public class DocumentoService {

    @Autowired
    DocumentoRepository documentoRepository;

    public ArrayList<DocumentoEntity> getDocumentos(){ return (ArrayList<DocumentoEntity>) documentoRepository.findAll();}

    public DocumentoEntity saveDocumento(DocumentoEntity documento){ return documentoRepository.save(documento);}

    public DocumentoEntity getDocumentoById(Long id) { return documentoRepository.findById(id).get();}


    public List<DocumentoEntity> getDocumentoByDate(Date date){ return (List<DocumentoEntity>) documentoRepository.findByFechaCarga(date);}
    public DocumentoEntity updateDocumento(DocumentoEntity documento) {return documentoRepository.save(documento);}

    public boolean deleteDocumento(Long id) throws Exception {
        try{
            documentoRepository.deleteById(id);
            return true;
        } catch(Exception error){
            throw new Exception(error.getMessage());
        }
    }
}
