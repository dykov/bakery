package com.dykov.bakery.converter;

import com.dykov.bakery.dto.OrderItemDTO;
import com.dykov.bakery.entity.OrderItem;
import com.dykov.bakery.entity.OrderItemId;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderItemConverter implements Converter<OrderItem, OrderItemDTO> {

    @Override
    public OrderItemDTO convert(@NonNull final OrderItem orderItem) {
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setItemId(orderItem.getId().getItemId());
        orderItemDTO.setItemCost(orderItem.getItem().getCost());
        orderItemDTO.setAmount(orderItem.getAmount());
        return orderItemDTO;
    }

    public OrderItem parse(@NonNull final OrderItemDTO orderItemDTO) {
        OrderItemId id = new OrderItemId();
        id.setItemId(orderItemDTO.getItemId());

        OrderItem orderItem = new OrderItem();
        orderItem.setId(id);
        orderItem.setAmount(orderItemDTO.getAmount());
        return orderItem;
    }
}
