package com.dykov.bakery.exception.util;

import java.util.Date;
import com.dykov.bakery.exception.ClientNotFound;
import com.dykov.bakery.exception.ItemNotFound;
import com.dykov.bakery.exception.OrderItemNotFound;
import com.dykov.bakery.exception.OrderNotFound;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AdviceController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Something went wrong";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(),
            HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({ClientNotFound.class, ItemNotFound.class, OrderItemNotFound.class,
        OrderNotFound.class})
    protected ResponseEntity<Object> handleNotFoundException(RuntimeException ex, WebRequest request) {
        return this.fillResponse(ex, request, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<Object> fillResponse(Exception ex, WebRequest request,
                                                HttpStatus status) {
        String error = ex.getLocalizedMessage();
        if (ex.getCause() != null) {
            error = String.format("%s : %s", error, ex.getCause().getMessage());
        } else if (StringUtils.isEmpty(error)) {
            error = "[message not available]";
        }
        final ErrorResponse response = ErrorResponse.builder()
            .timestamp(new Date())
            .status(status.value())
            .error(error)
            .message(ex.getMessage())
            .path(this.getPath(request))
            .build();
        return new ResponseEntity<>(response, new HttpHeaders(), status);
    }

    private String getPath(WebRequest request) {
        return ((ServletWebRequest) request)
            .getRequest()
            .getRequestURI();
    }
}
