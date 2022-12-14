package com.upthemuscle.service.serviceImp;

import com.upthemuscle.converter.CoachConverter;
import com.upthemuscle.converter.PhotoConverter;
import com.upthemuscle.dto.ClientDtoResponse;
import com.upthemuscle.dto.CoachDto;
import com.upthemuscle.model.Client;
import com.upthemuscle.model.Coach;
import com.upthemuscle.model.Person;
import com.upthemuscle.model.Photo;
import com.upthemuscle.repository.CoachRepository;
import com.upthemuscle.service.ICoachService;
import com.upthemuscle.service.IPersonService;
import com.upthemuscle.service.IPhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CoachServiceImpl implements ICoachService {

    private final CoachRepository coachRepository;

    private final IPersonService personService;

    private final IPhotoService photoService;

    private final PhotoConverter photoConverter;

    private final CoachConverter coachConverter;

    @Override
    public CoachDto saveCoach(CoachDto coachDto) {
        CoachDto oldCoachDto = this.getCoach(coachDto.getDocumentNumber());

        if (oldCoachDto.getDocumentNumber() != null) {

            Coach coachNew = coachConverter.toEntity(coachDto);
            Person newPerson = coachNew.getPerson();
            Person oldPerson = personService.getPerson(coachDto.getDocumentNumber());
            Photo photo = new Photo();

            photo.setPhoto(photoConverter.base64ToByArray(coachDto.getPhoto()));
            photo.setId(oldPerson.getPhotoId());

            newPerson.setPhotoId(oldPerson.getPhotoId());

            photoService.updatePhoto(photo);
            personService.updatePerson(newPerson);
            Coach oldCoach = coachRepository.findByPerson_id(newPerson.getId());
            coachNew.setId(oldCoach.getId());

            return coachConverter.toDtoResponse(coachRepository.save(coachNew));

        }

        Photo photo = new Photo();
        photo.setPhoto(photoConverter.base64ToByArray(coachDto.getPhoto()));
        Photo photoSave = photoService.savePhoto(photo);
        Coach coach = coachConverter.toEntity(coachDto);

        Person newPerson = coach.getPerson();
        newPerson.setPhotoId(photoSave.getId());

        personService.savePerson(newPerson);

        coachRepository.save(coach);

        return coachConverter.toDtoResponse(coach);
    }

    @Override
    public List<CoachDto> listCoach() {
        List<Coach> coachList =  coachRepository.findAll();
        List<CoachDto> coachDtoList = new ArrayList<CoachDto>();

        for (Coach c : coachList) {
            Person person = c.getPerson();
            CoachDto coachDtoResponse = coachConverter.toDtoResponse(c);
            Photo photo = photoService.getPhoto(person.getPhotoId());

            coachDtoResponse.setPhoto(photoConverter.byteArrayToBase64(photo.getPhoto()));

            coachDtoList.add(coachDtoResponse);
        }

        return coachDtoList;
    }

    @Override
    public CoachDto getCoach(String documentNumber) {
        Person person = personService.getPerson(documentNumber);
        Coach coach = new Coach();
        CoachDto coachDto = new CoachDto();

        if (person != null) {
            Photo photo = new Photo();
            photo = photoService.getPhoto(person.getPhotoId());
            coach = coachRepository.findByPerson_id(person.getId());
            coachDto.setPhoto(photoConverter.byteArrayToBase64(photo.getPhoto()));
            coachDto = coachConverter.toDtoResponse(coach);

            return coachDto;
        }

        return coachDto;
    }

    @Override
    public void deleteByDocumentNumber(String documentNumber) {
        Person person = personService.getPerson(documentNumber);

        if (person != null) {
            photoService.deletePhoto(person.getPhotoId());
            personService.deletePerson(documentNumber);
        }
    }

    @Override
    public Boolean existsByDocumentNumber(String documentNumber) {
        return personService.existsByDocumentNumber(documentNumber);
    }
}
