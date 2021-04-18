package com.dykov.bakery.exception;

import com.dykov.bakery.entity.OrderItemId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class OrderItemNotFound extends BaseException {
    private static final String errorMessage = "Item with ID=%s not found in Order with ID=%s.";

    public OrderItemNotFound(final OrderItemId id) {
        super(String.format(errorMessage, id.getItemId(), id.getOrderId()));
    }
}
