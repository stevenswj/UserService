package com.userservice.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.http.HttpStatus;
import com.userservice.error.ErrorResponse;
import com.userservice.error.BadRequestException;

@ControllerAdvice
public class UserServiceErrorAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({BadRequestException.class})
    @ResponseBody
    public ErrorResponse handleClientException(BadRequestException e) {
        System.out.println("Type = " + e.getClass().getCanonicalName());
        System.out.println("Message = " + e.getMessage());
        return new ErrorResponse(e.getClass().getCanonicalName(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ErrorResponse handleOther(Exception e) {
        System.out.println("Type = " + e.getClass().getCanonicalName());
        System.out.println("Message = " + e.getMessage());
        return new ErrorResponse(e.getClass().getCanonicalName(), e.getMessage());
    }
}
