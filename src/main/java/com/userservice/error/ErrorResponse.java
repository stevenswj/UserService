package com.userservice.error;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private String errorClass;
    private String errorMessage;

    public ErrorResponse(String errorClass, String errorMessage) {
        this.errorClass = errorClass;
        this.errorMessage = errorMessage;
    }
}