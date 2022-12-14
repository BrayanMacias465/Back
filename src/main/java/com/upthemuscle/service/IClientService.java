package com.upthemuscle.service;

import com.upthemuscle.dto.ClientDtoRequest;
import com.upthemuscle.dto.ClientDtoResponse;

import java.util.List;

public interface IClientService {

    ClientDtoResponse saveClient(ClientDtoRequest clientDtoRequest);

    List<ClientDtoResponse> listClients();

    ClientDtoResponse getClient(String documentNumber);

    void deleteByDocumentNumber(String documentNumber);

    Boolean existsByDocumentNumber(String documentNumber);
}
