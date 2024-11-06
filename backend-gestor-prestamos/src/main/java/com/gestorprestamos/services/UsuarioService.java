package com.gestorprestamos.services;

import com.gestorprestamos.entities.UsuarioEntity;
import com.gestorprestamos.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public ArrayList<UsuarioEntity> getUsuarios(){ return (ArrayList<UsuarioEntity>) usuarioRepository.findAll();}

    public boolean existsByRut(String rut) {
        return usuarioRepository.existsByRut(rut);
    }

    public UsuarioEntity saveUsuario(UsuarioEntity usuario){
        usuario.setRol("cliente");
        if (existsByRut(usuario.getRut())) {
            throw new RuntimeException("El usuario con este RUT ya está registrado.");
        }
        return usuarioRepository.save(usuario);}

    public UsuarioEntity getUsuarioById(Long id) { return usuarioRepository.findById(id).get();}

    public UsuarioEntity getUsuarioByRut(String rut) {return usuarioRepository.findByRut(rut);}
    public UsuarioEntity updateUsuario(UsuarioEntity documento) {return usuarioRepository.save(documento);}

    public boolean deleteUsuario(Long id) throws Exception {
        try{
            usuarioRepository.deleteById(id);
            return true;
        } catch(Exception error){
            throw new Exception(error.getMessage());
        }
    }

}
