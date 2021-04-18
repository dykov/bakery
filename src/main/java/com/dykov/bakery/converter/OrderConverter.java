package com.dykov.bakery.converter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import com.dykov.bakery.dto.OrderDTO;
import com.dykov.bakery.dto.OrderItemDTO;
import com.dykov.bakery.entity.Order;
import com.dykov.bakery.entity.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderConverter implements Converter<Order, OrderDTO> {
    private final ClientConverter clientConverter;
    private final OrderItemConverter orderItemConverter;

    @Override
    public OrderDTO convert(@NonNull final Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setClient(clientConverter.convert(order.getClient()));
        orderDTO.setPrice(order.getPrice());
        Set<OrderItemDTO> orderItemDTOs = order.getOrderItems().stream()
            .map(orderItemConverter::convert)
            .collect(Collectors.toSet());
        orderDTO.setOrderItems(orderItemDTOs);
        return orderDTO;
    }

    public Order parse(@NonNull final OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setClient(clientConverter.parse(orderDTO.getClient()));
        order.setPrice(orderDTO.getPrice());
        Set<OrderItem> orderItems = orderDTO.getOrderItems().stream()
            .map(orderItemConverter::parse)
            .peek(item -> item.getId().setOrderId(orderDTO.getId()))
            .collect(Collectors.toSet());
        order.setOrderItems(orderItems);
        return order;
    }
}
