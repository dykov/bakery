package com.dykov.bakery.controller;


import java.util.List;
import java.util.UUID;
import com.dykov.bakery.dto.OrderDTO;
import com.dykov.bakery.service.OrderService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/order")
public class OrderController {
    private OrderService orderService;

    @GetMapping(path = "/{id}")
    public OrderDTO getOrder(@PathVariable(name = "id") UUID id) {
        return orderService.getOrder(id);
    }

//    @GetMapping
//    public List<OrderDTO> getAllOrders() {
//        return orderService.getAllOrders();
//    }

    @GetMapping
    public Page<OrderDTO> getAllOrders(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return orderService.getAllOrders(pageable);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public OrderDTO createOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.createOrder(orderDTO);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable(name = "id") UUID id) {
        orderService.deleteOrder(id);
    }

}
