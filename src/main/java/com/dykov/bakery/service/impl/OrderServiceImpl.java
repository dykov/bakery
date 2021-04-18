package com.dykov.bakery.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import com.dykov.bakery.converter.OrderConverter;
import com.dykov.bakery.dto.OrderDTO;
import com.dykov.bakery.entity.Order;
import com.dykov.bakery.exception.OrderNotFound;
import com.dykov.bakery.repository.OrderRepository;
import com.dykov.bakery.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderConverter orderConverter;

    public OrderDTO getOrder(final UUID id) throws OrderNotFound {
        Order order = this.getOrThrow(id);
        return orderConverter.convert(order);
    }

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
            .map(orderConverter::convert).collect(Collectors.toList());
    }

    public Page<OrderDTO> getAllOrders(Pageable pageable) {
        Page<Order> orders = orderRepository.findAll(pageable);
        List<OrderDTO> orderDTOs = orders.stream()
            .map(orderConverter::convert)
            .collect(Collectors.toList());
        return new PageImpl<>(orderDTOs, pageable, orders.getTotalElements());
    }

    public OrderDTO createOrder(final OrderDTO orderDTO) {
        Order order = orderConverter.parse(orderDTO);
        return orderConverter.convert(orderRepository.saveAndFlush(order));
    }

    public void deleteOrder(final UUID id) {
        if (!orderRepository.existsById(id)) {
            throw new OrderNotFound(id);
        }
        orderRepository.deleteById(id);
    }

    private Order getOrThrow(UUID id) {
        return orderRepository.findById(id)
            .orElseThrow(() -> new OrderNotFound(id));
    }
}
