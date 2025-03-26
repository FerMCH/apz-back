package com.aplazo.customers.exception.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFoundException extends RuntimeException {

    private String message;

    public NotFoundException(String errorMessage, String message) {
        super(errorMessage);
        this.message = message;
    }

}
