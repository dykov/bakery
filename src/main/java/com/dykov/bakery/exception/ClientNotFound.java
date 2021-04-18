package com.dykov.bakery.exception;

import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ClientNotFound extends BaseException {
    private static final String errorMessage = "Client with %s=%s not found.";
    private static final String PHONE = "phone";
    private static final String ID = "ID";

    public ClientNotFound(final String phone) {
        super(String.format(errorMessage, PHONE, phone));
    }

    public ClientNotFound(final UUID id) {
        super(String.format(errorMessage, ID, id));
    }
}
