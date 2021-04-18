package com.dykov.bakery.service;

import java.util.List;
import java.util.UUID;
import com.dykov.bakery.dto.OrderDTO;
import com.dykov.bakery.exception.OrderNotFound;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    OrderDTO getOrder(UUID id) throws OrderNotFound;

    List<OrderDTO> getAllOrders();

    Page<OrderDTO> getAllOrders(Pageable pageable);

    OrderDTO createOrder(OrderDTO orderDTO);

    void deleteOrder(UUID id);
}
