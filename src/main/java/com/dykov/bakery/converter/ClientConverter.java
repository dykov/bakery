package com.dykov.bakery.converter;

import com.dykov.bakery.dto.ClientDTO;
import com.dykov.bakery.entity.Client;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class ClientConverter implements Converter<Client, ClientDTO> {
    @Override
    public ClientDTO convert(@NonNull final Client client) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(client.getId());
        clientDTO.setName(client.getName());
        clientDTO.setAddress(client.getAddress());
        clientDTO.setPhone(client.getPhone());
        return clientDTO;
    }

    public Client parse(@NonNull final ClientDTO clientDTO) {
        Client client = new Client();
        client.setId(clientDTO.getId());
        client.setName(clientDTO.getName());
        client.setAddress(clientDTO.getAddress());
        client.setPhone(clientDTO.getPhone());
        return client;
    }
}
