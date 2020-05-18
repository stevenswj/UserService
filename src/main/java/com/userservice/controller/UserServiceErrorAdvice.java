package com.userservice.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.HttpStatus;
import com.userservice.error.ErrorResponse;
import com.userservice.error.BadRequestException;
import lombok.extern.slf4j.Slf4j;

// This controller advice handles 4XX and 5XX error codes thrown by the business logic
@ControllerAdvice
@Slf4j
public class UserServiceErrorAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({BadRequestException.class})
    @ResponseBody
    public ErrorResponse handleClientException(BadRequestException e) {
        log.error("Type = " + e.getClass().getCanonicalName());
        log.error("Message = " + e.getMessage());
        return new ErrorResponse(e.getClass().getCanonicalName(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ErrorResponse handleOther(Exception e) {
        log.error("Type = " + e.getClass().getCanonicalName());
        log.error("Message = " + e.getMessage());
        return new ErrorResponse(e.getClass().getCanonicalName(), e.getMessage());
    }
}
