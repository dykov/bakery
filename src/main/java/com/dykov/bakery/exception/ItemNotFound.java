package com.dykov.bakery.exception;

import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ItemNotFound extends BaseException {
    private static final String errorMessage = "Item with ID=%s not found.";

    public ItemNotFound(final UUID id) {
        super(String.format(errorMessage, id.toString()));
    }
}
