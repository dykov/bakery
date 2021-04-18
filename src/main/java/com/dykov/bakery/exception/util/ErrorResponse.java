package com.dykov.bakery.exception.util;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@Builder
public class ErrorResponse {

    private Date timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

}
