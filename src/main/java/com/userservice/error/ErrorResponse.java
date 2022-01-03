package com.userservice.error;

import lombok.Getter;

/*
 * An ErrorResponse object is returned (as JSON) whenever there is an error directly by the UserServiceErrorAdvice
 * controller. Whenever an uncaught exception is thrown, the application will notify the user via an ErrorResponse.
 */
@Getter
public class ErrorResponse {
    private String errorClass;
    private String errorMessage;

    /*
     * ErrorResponse constructor.
     *
     * @param errorClass - Class of the caught exception
     * @param errorMessage - The error message
     */
    public ErrorResponse(String errorClass, String errorMessage) {
        this.errorClass = errorClass;
        this.errorMessage = errorMessage;
    }
}