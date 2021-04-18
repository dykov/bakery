package com.dykov.bakery.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import com.dykov.bakery.converter.ClientConverter;
import com.dykov.bakery.dto.ClientDTO;
import com.dykov.bakery.entity.Client;
import com.dykov.bakery.exception.ClientNotFound;
import com.dykov.bakery.exception.PhoneAlreadyExistsException;
import com.dykov.bakery.repository.ClientRepository;
import com.dykov.bakery.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientConverter converter;

    public ClientDTO getClient(UUID id) throws ClientNotFound {
        Client client = this.getOrThrow(id);
        return converter.convert(client);
    }

    public ClientDTO getClientByPhone(String phone) throws ClientNotFound {
        Client client = clientRepository.findByPhone(phone)
            .orElseThrow(() -> new ClientNotFound(phone));
        return converter.convert(client);
    }

    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll().stream()
            .map(converter::convert).collect(Collectors.toList());
    }

    public Page<ClientDTO> getAllClients(Pageable pageable) {
        Page<Client> clients = clientRepository.findAll(pageable);
        List<ClientDTO> clientDTOs = clients.stream()
            .map(converter::convert)
            .collect(Collectors.toList());
        return new PageImpl<>(clientDTOs, pageable, clients.getTotalElements());
    }

    public ClientDTO createClient(ClientDTO clientDTO) throws PhoneAlreadyExistsException {
        if (clientRepository.existsByPhone(clientDTO.getPhone())) {
            throw new PhoneAlreadyExistsException(clientDTO.getPhone());
        }
        Client client = converter.parse(clientDTO);
        return converter.convert(clientRepository.saveAndFlush(client));
    }

    public ClientDTO editClient(UUID id, ClientDTO clientDTO) throws ClientNotFound,
        PhoneAlreadyExistsException {
        Client stored = this.getOrThrow(id);
        Client edited = converter.parse(clientDTO);
        if (!stored.getPhone().equals(edited.getPhone())) {
            this.checkIfPhoneExist(edited.getPhone());
        }
        edited.setId(stored.getId());
        return converter.convert(clientRepository.saveAndFlush(edited));
    }

    public void deleteClient(UUID id) {
        if (!clientRepository.existsById(id)) {
            throw new ClientNotFound(id);
        }
        clientRepository.deleteById(id);
    }

    private Client getOrThrow(UUID id) {
        return clientRepository.findById(id)
            .orElseThrow(() -> new ClientNotFound(id));
    }

    private void checkIfPhoneExist(String newPhone) {
        if (clientRepository.existsByPhone(newPhone)) {
            throw new PhoneAlreadyExistsException(newPhone);
        }
    }
}
