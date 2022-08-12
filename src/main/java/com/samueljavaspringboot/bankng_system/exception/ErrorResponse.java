package com.samueljavaspringboot.bankng_system.exception;

import java.util.List;

import com.samueljavaspringboot.bankng_system.exception.FieldError;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ErrorResponse {

    private Integer httpStatus;
    private String exception;
    private String message;
    private List<FieldError> fieldErrors;

}
