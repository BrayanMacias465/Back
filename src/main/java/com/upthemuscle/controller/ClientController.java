package com.upthemuscle.controller;

import com.upthemuscle.dto.ClientDtoRequest;
import com.upthemuscle.dto.ClientDtoResponse;
import com.upthemuscle.dto.Mensaje;
import com.upthemuscle.service.IClientService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ClientController {

    private final IClientService clientService;

    @ApiOperation("Devuelve una lista de Clientes")
    @GetMapping
    public ResponseEntity<List<ClientDtoResponse>> list(){
        List<ClientDtoResponse> list = clientService.listClients();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @ApiOperation("Crea un nuevo cliente")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ClientDtoRequest clientDtoRequest){
        if(StringUtils.isBlank(clientDtoRequest.getName()))
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(clientService.existsByDocumentNumber(clientDtoRequest.getDocumentNumber()))
            return new ResponseEntity<>(new Mensaje("Este Cliente ya se encuentra registrado"), HttpStatus.BAD_REQUEST);
        //Cliente cliente = new Cliente (clienteDto.getNombre(), clienteDto.getCedula(), clienteDto.getNumeroTelefono(), clienteDto.getEmail(), clienteDto.getEstado());

        clientService.saveClient(clientDtoRequest);
        return new ResponseEntity<>(new Mensaje("Cliente creado"), HttpStatus.OK);
    }

    @ApiOperation("Actualiza un Cliente")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{documentNumber}")
    public ResponseEntity<?> update(@PathVariable("documentNumber") String documentNumber, @RequestBody ClientDtoRequest clienteDtoRequest){
        if(!clientService.existsByDocumentNumber(documentNumber))
            return new ResponseEntity<>(new Mensaje("El cliente no existe"), HttpStatus.NOT_FOUND);
        //if(clienteService.existsByNombre(clienteDto.getNombre()) && clienteService.getByNombre(clienteDto.getNombre()).get().getId() != id)
            //return new ResponseEntity<>(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        //if(StringUtils.isBlank(clienteDto.getNombre()))
            //return new ResponseEntity<>(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);

        //Cliente cliente = clienteService.getOne(id).get();
        //cliente.setNombre(clienteDto.getNombre());
        //cliente.setEstado(clienteDto.getEstado());
        clientService.saveClient(clienteDtoRequest);
        return new ResponseEntity<>(new Mensaje("cliente actualizado"), HttpStatus.OK);
    }

    @ApiOperation("Elimina un Cliente por numero de cedula")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{documentNumber}")
    public ResponseEntity<?> delete(@PathVariable("documentNumber") String documentNumber){
        if(!clientService.existsByDocumentNumber(documentNumber))
            return new ResponseEntity<>(new Mensaje("No existe el cliente"), HttpStatus.NOT_FOUND);
        clientService.deleteByDocumentNumber(documentNumber);
        return new ResponseEntity<>(new Mensaje("Cliente eliminado"), HttpStatus.OK);
    }

    @ApiOperation("Devuelve un cliente por su ID")
    @GetMapping("/{documentNumber}")
    public ResponseEntity<ClientDtoResponse> get(@PathVariable("documentNumber") String documentNumber){
        ClientDtoResponse clientDtoResponse = clientService.getClient(documentNumber);
        return new ResponseEntity<>(clientDtoResponse, HttpStatus.OK);
    }


}
