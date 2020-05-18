package com.userservice.error;

import lombok.Getter;

// A BadRequestException is an exception that, when thrown, ultimately triggers a 4XX error response
@Getter
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}