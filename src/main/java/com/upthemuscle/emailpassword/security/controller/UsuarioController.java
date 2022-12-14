package com.upthemuscle.emailpassword.security.controller;


import com.upthemuscle.emailpassword.security.dto.NuevoUsuario;
import com.upthemuscle.emailpassword.security.entity.Usuario;
import com.upthemuscle.emailpassword.security.service.UsuarioService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @ApiOperation("Muestra una lista de usuarios")
    @GetMapping
    public ResponseEntity<List<Usuario>> list(){
        List<Usuario> list = usuarioService.listar();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @ApiOperation("Devuelve los usarios activos")
    @GetMapping("/activos")
    public ResponseEntity<List<Usuario>> listActivos(){
        List<Usuario> list = usuarioService.listarActivos();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @ApiOperation("Muestra un usuario")
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> get(@PathVariable("id")int id){
        Usuario usuario = usuarioService.getById(id).get();
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @ApiOperation("Borra un usuario por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id")int id){
        usuarioService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("Actualiza un usuario")
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable("id")int id, @RequestBody NuevoUsuario usuarioDTO){
        Usuario usuario = usuarioService.getById(id).get();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setNombreUsuario(usuarioDTO.getNombreUsuario());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setEstado(usuarioDTO.getEstado());
        usuarioService.save(usuario);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }



}
