package com.upthemuscle.controller;

import com.upthemuscle.dto.ClientDtoRequest;
import com.upthemuscle.dto.ClientDtoResponse;
import com.upthemuscle.dto.CoachDto;
import com.upthemuscle.dto.Mensaje;
import com.upthemuscle.service.ICoachService;
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
@RequestMapping("/entrenadores")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CoachController {

    private final ICoachService coachService;

    @ApiOperation("Devuelve una lista de Entrenadores")
    @GetMapping
    public ResponseEntity<List<CoachDto>> list() {
        List<CoachDto> list = coachService.listCoach();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @ApiOperation("Crea un nuevo Entrenador")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CoachDto CoachDto) {
        if (StringUtils.isBlank(CoachDto.getName()))
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if (coachService.existsByDocumentNumber(CoachDto.getDocumentNumber()))
            return new ResponseEntity<>(new Mensaje("Este Entrenador ya se encuentra registrado"), HttpStatus.BAD_REQUEST);
        //Cliente cliente = new Cliente (clienteDto.getNombre(), clienteDto.getCedula(), clienteDto.getNumeroTelefono(), clienteDto.getEmail(), clienteDto.getEstado());

        coachService.saveCoach(CoachDto);
        return new ResponseEntity<>(new Mensaje("Entrenador creado"), HttpStatus.OK);
    }

    @ApiOperation("Actualiza un Entrenador")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{documentNumber}")
    public ResponseEntity<?> update(@PathVariable("documentNumber") String documentNumber, @RequestBody CoachDto CoachDto) {
        if (!coachService.existsByDocumentNumber(documentNumber))
            return new ResponseEntity<>(new Mensaje("El Entrenador no existe"), HttpStatus.NOT_FOUND);
        //if(clienteService.existsByNombre(clienteDto.getNombre()) && clienteService.getByNombre(clienteDto.getNombre()).get().getId() != id)
        //return new ResponseEntity<>(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        //if(StringUtils.isBlank(clienteDto.getNombre()))
        //return new ResponseEntity<>(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);

        //Cliente cliente = clienteService.getOne(id).get();
        //cliente.setNombre(clienteDto.getNombre());
        //cliente.setEstado(clienteDto.getEstado());
        coachService.saveCoach(CoachDto);
        return new ResponseEntity<>(new Mensaje("Entrenador actualizado"), HttpStatus.OK);
    }

    @ApiOperation("Elimina un Entrenador por numero de cedula")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{documentNumber}")
    public ResponseEntity<?> delete(@PathVariable("documentNumber") String documentNumber){
        if(!coachService.existsByDocumentNumber(documentNumber))
            return new ResponseEntity<>(new Mensaje("No existe el entrenador"), HttpStatus.NOT_FOUND);
        coachService.deleteByDocumentNumber(documentNumber);
        return new ResponseEntity<>(new Mensaje("Cliente eliminado"), HttpStatus.OK);
    }

}
