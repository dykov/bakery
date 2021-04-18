package com.dykov.bakery.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class ItemAlreadyExistsException extends BaseException {
    private static final String errorMessage = "Item with name %s already exists";

    public ItemAlreadyExistsException(final String phone) {
        super(String.format(errorMessage, phone));
    }
}
