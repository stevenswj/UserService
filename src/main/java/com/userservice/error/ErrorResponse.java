package com.userservice.error;

import lombok.Getter;

// An ErrorResponse object is returned (as JSON) whenever there is an error.
@Getter
public class ErrorResponse {
    private String errorClass;
    private String errorMessage;

    public ErrorResponse(String errorClass, String errorMessage) {
        this.errorClass = errorClass;
        this.errorMessage = errorMessage;
    }
}