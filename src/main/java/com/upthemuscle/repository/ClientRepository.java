package com.upthemuscle.repository;

import com.upthemuscle.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByPerson_id(Long id);

}
