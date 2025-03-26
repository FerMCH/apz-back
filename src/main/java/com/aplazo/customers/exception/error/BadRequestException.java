package com.aplazo.customers.exception.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BadRequestException extends RuntimeException {

    private String message;

    public BadRequestException(String errorMessage, String message) {
        super(errorMessage);
        this.message = message;
    }

}
