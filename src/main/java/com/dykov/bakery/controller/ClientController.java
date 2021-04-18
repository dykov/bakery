package com.dykov.bakery.controller;


import java.util.List;
import java.util.UUID;
import com.dykov.bakery.dto.ClientDTO;
import com.dykov.bakery.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/client")
public class ClientController {
    private ClientService clientService;

    @GetMapping(path = "/{id}")
    public ClientDTO getClient(@PathVariable(name = "id") UUID id) {
        return clientService.getClient(id);
    }

//    @GetMapping
//    public List<ClientDTO> getAllClients() {
//        return clientService.getAllClients();
//    }

    @GetMapping
    public Page<ClientDTO> getAllClients(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return clientService.getAllClients(pageable);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ClientDTO createClient(@RequestBody ClientDTO clientDTO) {
        return clientService.createClient(clientDTO);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ClientDTO editClient(@PathVariable(name = "id") UUID id,
                                @RequestBody ClientDTO clientDTO) {
        return clientService.editClient(id, clientDTO);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable(name = "id") UUID id) {
        clientService.deleteClient(id);
    }

}
