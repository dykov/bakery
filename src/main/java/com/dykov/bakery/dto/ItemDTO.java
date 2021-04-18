package com.dykov.bakery.dto;

import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemDTO {

    @JsonProperty("item_id")
    private UUID id;

    @JsonProperty("item_name")
    private String name;

    @JsonProperty("item_cost")
    private int cost;
}

