package com.aplazo.customers.exception.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InternalErrorException extends RuntimeException {

    private String message;

    public InternalErrorException(String errorMessage, String message) {
        super(errorMessage);
        this.message = message;
    }

}
