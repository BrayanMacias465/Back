package com.upthemuscle.service.serviceImp;

import com.upthemuscle.converter.ClientConverter;
import com.upthemuscle.converter.PhotoConverter;
import com.upthemuscle.dto.ClientDtoRequest;
import com.upthemuscle.dto.ClientDtoResponse;
import com.upthemuscle.model.Client;
import com.upthemuscle.model.Person;
import com.upthemuscle.model.Photo;
import com.upthemuscle.repository.ClientRepository;
import com.upthemuscle.service.IClientService;
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
public class ClientServiceImpl implements IClientService {

    private final ClientRepository clientRepository;

    private final IPersonService personService;

    private final IPhotoService photoService;

    private final PhotoConverter photoConverter;

    private final ClientConverter clientConverter;

    @Override
    public ClientDtoResponse saveClient(ClientDtoRequest clientDtoRequest) {

        ClientDtoResponse oldClientDtoResponse = this.getClient(clientDtoRequest.getDocumentNumber());

        if (oldClientDtoResponse.getDocumentNumber() != null) {

            Client clientNew = clientConverter.toEntity(clientDtoRequest);
            Person newPerson = clientNew.getPerson();
            Person oldPerson = personService.getPerson(clientDtoRequest.getDocumentNumber());
            Photo photo = new Photo();

            photo.setPhoto(photoConverter.base64ToByArray(clientDtoRequest.getPhoto()));
            photo.setId(oldPerson.getPhotoId());

            newPerson.setPhotoId(oldPerson.getPhotoId());

            photoService.updatePhoto(photo);
            personService.updatePerson(newPerson);
            Client oldClient = clientRepository.findByPerson_id(newPerson.getId());
            clientNew.setId(oldClient.getId());

            return clientConverter.toDtoResponse(clientRepository.save(clientNew));

        }

        Photo photo = new Photo();
        photo.setPhoto(photoConverter.base64ToByArray(clientDtoRequest.getPhoto()));
        Photo photoSave = photoService.savePhoto(photo);
        Client client = clientConverter.toEntity(clientDtoRequest);
        Person newPerson = client.getPerson();
        newPerson.setPhotoId(photoSave.getId());

        personService.savePerson(newPerson);

        clientRepository.save(client);

        return clientConverter.toDtoResponse(client);

    }

    @Override
    public List<ClientDtoResponse> listClients() {
        List<Client> clients =  clientRepository.findAll();
        List<ClientDtoResponse> clientsDto = new ArrayList<ClientDtoResponse>();

        for (Client c : clients) {
            Person person = c.getPerson();
            ClientDtoResponse clientDtoResponse = clientConverter.toDtoResponse(c);
            Photo photo = photoService.getPhoto(person.getPhotoId());
            clientDtoResponse.setPhoto(photoConverter.byteArrayToBase64(photo.getPhoto()));

            clientsDto.add(clientDtoResponse);
        }

        return clientsDto;
    }

    @Override
    public ClientDtoResponse getClient(String documentNumber) {

        Person person = personService.getPerson(documentNumber);
        Client client = new Client();
        ClientDtoResponse clientDtoResponse = new ClientDtoResponse();

        if (person != null) {
            Photo photo = new Photo();
            photo = photoService.getPhoto(person.getPhotoId());
            client = clientRepository.findByPerson_id(person.getId());
            clientDtoResponse.setPhoto(photoConverter.byteArrayToBase64(photo.getPhoto()));
            clientDtoResponse = clientConverter.toDtoResponse(client);

            return clientDtoResponse;
        }

        return clientDtoResponse;
    }

    @Override
    public void deleteByDocumentNumber(String documentNumber) {

        Person person = personService.getPerson(documentNumber);

        if (person != null) {
            photoService.deletePhoto(person.getPhotoId());
            personService.deletePerson(documentNumber);
        }
    }

   public Boolean existsByDocumentNumber(String documentNumber) {
       return personService.existsByDocumentNumber(documentNumber);
   }

}
