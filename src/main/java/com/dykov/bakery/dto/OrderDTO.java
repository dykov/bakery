package com.dykov.bakery.dto;

import java.util.Set;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderDTO {

    @JsonProperty("order_id")
    private UUID id;

    @JsonProperty("client")
    private ClientDTO client;

    @JsonProperty("price")
    private int price;

    @JsonProperty("order_items")
    private Set<OrderItemDTO> orderItems;
}
