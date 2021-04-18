package com.dykov.bakery.dto;

import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderItemDTO {

    @JsonProperty("item_id")
    private UUID itemId;

    @JsonProperty("item_cost")
    private int itemCost;

    @JsonProperty("amount")
    private int amount;

}

