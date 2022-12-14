package com.upthemuscle.converter;

import com.upthemuscle.dto.ClientDtoRequest;
import com.upthemuscle.dto.ClientDtoResponse;
import com.upthemuscle.model.Client;
import com.upthemuscle.model.Person;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component("clientConverter")
public class ClientConverter {

    // Dto --> Entity

    public Client toEntity(ClientDtoRequest clientDtoRequest){
        Client client = new Client();
        Person person = new Person();

        person.setName(clientDtoRequest.getName());
        person.setLastname(clientDtoRequest.getLastname());
        person.setTelephone(clientDtoRequest.getTelephone());
        person.setAddress(clientDtoRequest.getAddress());
        person.setDocumentType(clientDtoRequest.getDocumentType());
        person.setDocumentNumber(clientDtoRequest.getDocumentNumber());

        client.setPerson(person);
        client.setEmergencyNumber(clientDtoRequest.getEmergencyNumber());
        client.setStartDate(this.converterDate(clientDtoRequest.getStartDate()));
        client.setFinishDate(this.converterDate(clientDtoRequest.getFinishDate()));
        client.setMedicalHistory(clientDtoRequest.getMedicalHistory());

        return client;
    }

    public ClientDtoResponse toDtoResponse(Client client){
        ClientDtoResponse clientDtoResponse = new ClientDtoResponse();
        Person person = client.getPerson();

        clientDtoResponse.setName(person.getName());
        clientDtoResponse.setLastname(person.getLastname());
        clientDtoResponse.setTelephone(person.getTelephone());
        clientDtoResponse.setAddress(person.getAddress());
        clientDtoResponse.setDocumentType(person.getDocumentType());
        clientDtoResponse.setDocumentNumber(person.getDocumentNumber());

        clientDtoResponse.setEmergencyNumber(client.getEmergencyNumber());
        clientDtoResponse.setStartDate(client.getStartDate());
        clientDtoResponse.setFinishDate(client.getFinishDate());
        clientDtoResponse.setMedicalHistory(client.getMedicalHistory());
        clientDtoResponse.getMissingDays();

        //clientDtoResponse.setDateStart(converterString(client.getStartDate()));
        //clientDtoResponse.setDateFinish(converterString(client.getFinishDate()));

        return clientDtoResponse;
    }

    private LocalDate converterDate(String date){
        //System.out.println(date + " ++++++++++++++++++---------------------------------------------------");
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate d = LocalDate.parse(date, DateTimeFormatter.ofPattern("d-M-yyyy"));
        return d;
    }

    private String converterString(LocalDate date){
        String d = "";
        if (date != null) {
            d = date.toString();
        }

        return d;
    }
}
