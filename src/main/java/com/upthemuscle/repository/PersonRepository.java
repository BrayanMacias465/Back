package com.upthemuscle.repository;

import com.upthemuscle.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByDocumentNumber(String documentNumber);
    void deleteByDocumentNumber(String documentNumber);

    boolean existsByDocumentNumber(String documentNumber);

}
