package com.upthemuscle.service;

import com.upthemuscle.model.Person;

public interface IPersonService {

    Person savePerson(Person person);

    void deletePerson(String documentNumber);

    Person updatePerson(Person person);

    Person getPerson(String documentNumber);

    Boolean existsByDocumentNumber(String documentNumber);

}
