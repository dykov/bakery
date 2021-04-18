package com.dykov.bakery.dto;

import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClientDTO {
    @JsonProperty("client_id")
    private UUID id;

    @JsonProperty("client_phone")
    private String phone;

    @JsonProperty("client_name")
    private String name;

    @JsonProperty("client_address")
    private String address;
}
