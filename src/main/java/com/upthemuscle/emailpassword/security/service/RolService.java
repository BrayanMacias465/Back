package com.upthemuscle.emailpassword.security.service;


import com.upthemuscle.emailpassword.security.entity.Rol;
import com.upthemuscle.emailpassword.security.enums.RolNombre;
import com.upthemuscle.emailpassword.security.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RolService {

    @Autowired
    RolRepository rolRepository;

    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return rolRepository.findByRolNombre(rolNombre);
    }

    public void save(Rol rol){
        rolRepository.save(rol);
    }
}
