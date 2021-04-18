package com.dykov.bakery.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class PhoneAlreadyExistsException extends BaseException {
    private static final String errorMessage = "Client with phone %s already exists";

    public PhoneAlreadyExistsException(final String phone) {
        super(String.format(errorMessage, phone));
    }
}
