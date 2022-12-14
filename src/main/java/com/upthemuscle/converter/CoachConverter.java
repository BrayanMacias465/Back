package com.upthemuscle.converter;

import com.upthemuscle.dto.ClientDtoRequest;
import com.upthemuscle.dto.ClientDtoResponse;
import com.upthemuscle.dto.CoachDto;
import com.upthemuscle.model.Client;
import com.upthemuscle.model.Coach;
import com.upthemuscle.model.Person;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component("coachConverter")
public class CoachConverter {

    public Coach toEntity(CoachDto coachDto){
        Coach coach = new Coach();
        Person person = new Person();

        person.setName(coachDto.getName());
        person.setLastname(coachDto.getLastname());
        person.setTelephone(coachDto.getTelephone());
        person.setAddress(coachDto.getAddress());
        person.setDocumentType(coachDto.getDocumentType());
        person.setDocumentNumber(coachDto.getDocumentNumber());

        coach.setPerson(person);
        coach.setEmail(coachDto.getEmail());
        coach.setSalary(coachDto.getSalary());

        return coach;
    }

    public CoachDto toDtoResponse(Coach coach){
        CoachDto coachDtoResponse = new CoachDto();
        Person person = coach.getPerson();

        coachDtoResponse.setName(person.getName());
        coachDtoResponse.setLastname(person.getLastname());
        coachDtoResponse.setTelephone(person.getTelephone());
        coachDtoResponse.setAddress(person.getAddress());
        coachDtoResponse.setDocumentType(person.getDocumentType());
        coachDtoResponse.setDocumentNumber(person.getDocumentNumber());


        coachDtoResponse.setEmail(coach.getEmail());
        coachDtoResponse.setSalary(coach.getSalary());

        //clientDtoResponse.setDateStart(converterString(client.getStartDate()));
        //clientDtoResponse.setDateFinish(converterString(client.getFinishDate()));

        return coachDtoResponse;
    }

    private String byteArrayToBase64(byte[] byteArrayPhoto) {
        return Base64.getEncoder().encodeToString(byteArrayPhoto);
    }

    private byte[] base64ToByArray(String base64Photo) {
        return Base64.getDecoder().decode(base64Photo);
    }

}
