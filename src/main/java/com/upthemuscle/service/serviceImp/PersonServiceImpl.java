package com.upthemuscle.service.serviceImp;

import com.upthemuscle.model.Person;
import com.upthemuscle.repository.PersonRepository;
import com.upthemuscle.service.IPersonService;
import com.upthemuscle.service.IPhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PersonServiceImpl implements IPersonService {

    private final PersonRepository personRepository;

    private final IPhotoService photoService;

    @Override
    public Person savePerson(Person person) {

        return personRepository.save(person);
    }

    @Override
    public void deletePerson(String documentNumber) {
        personRepository.deleteByDocumentNumber(documentNumber);
    }

    @Override
    public Person updatePerson(Person person) {

        Person oldPerson = personRepository.findByDocumentNumber(person.getDocumentNumber());

        person.setId(oldPerson.getId());

        return personRepository.save(person);

    }

    @Override
    public Person getPerson(String documentNumber) {
        return personRepository.findByDocumentNumber(documentNumber);
    }

    @Override
    public Boolean existsByDocumentNumber(String documentNumber) {
        return personRepository.existsByDocumentNumber(documentNumber);
    }

}
