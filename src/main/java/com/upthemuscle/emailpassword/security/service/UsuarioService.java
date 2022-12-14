package com.upthemuscle.emailpassword.security.service;


import com.upthemuscle.emailpassword.security.entity.Usuario;
import com.upthemuscle.emailpassword.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Optional<List<Usuario>> getByEstado() {
        return usuarioRepository.findByEstado(true);
    }

    public List<Usuario> listarActivos() {
        return usuarioRepository.findByEstado(true).get();
    }

    public Optional<Usuario> getByNombreUsuario(String nombreUsuario){
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }

    public Optional<Usuario> getByNombreUsuarioOrEmail(String nombreOrEmail){
        return usuarioRepository.findByNombreUsuarioOrEmail(nombreOrEmail, nombreOrEmail);
    }

    public Optional<Usuario> getByTokenPassword(String tokenPassword){
        return usuarioRepository.findByTokenPassword(tokenPassword);
    }

    public boolean existsByNombreUsuario(String nombreUsuario){
        return usuarioRepository.existsByNombreUsuario(nombreUsuario);
    }

    public boolean existsByEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public Boolean changeStatus(int id, boolean status){
        Usuario usuario = usuarioRepository.getOne(id);
        usuario.setEstado(status);
        usuarioRepository.save(usuario);
        return usuario.getEstado();
    }

    public void save(Usuario usuario){
        usuarioRepository.save(usuario);
    }

    public void delete(int id){
        usuarioRepository.deleteById(id);
    }

    public Optional<Usuario> getById(int id){
        return usuarioRepository.findById(id);
    }

    public boolean existsById(int id){
        return usuarioRepository.existsById(id);
    }

}
