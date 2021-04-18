package com.dykov.bakery.service;

import java.util.List;
import java.util.UUID;
import com.dykov.bakery.dto.ClientDTO;
import com.dykov.bakery.exception.ClientNotFound;
import com.dykov.bakery.exception.PhoneAlreadyExistsException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService {

    ClientDTO getClient(UUID id) throws ClientNotFound;

    ClientDTO getClientByPhone(String phone) throws ClientNotFound;

    List<ClientDTO> getAllClients();

    Page<ClientDTO> getAllClients(Pageable pageable);

    ClientDTO createClient(ClientDTO client) throws PhoneAlreadyExistsException;

    ClientDTO editClient(UUID id, ClientDTO client) throws ClientNotFound, PhoneAlreadyExistsException;

    void deleteClient(UUID id) throws ClientNotFound;
}
