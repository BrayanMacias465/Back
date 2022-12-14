package com.upthemuscle.emailpassword.security.repository;

import com.upthemuscle.emailpassword.security.entity.Rol;
import com.upthemuscle.emailpassword.security.enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
