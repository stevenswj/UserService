package com.userservice.error;

import lombok.Getter;

/*
 * A BadRequestException is a RuntimeException that, when thrown, ultimately triggers a 4XX error response.
 *
 * @author Weston Stevens
 */
@Getter
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}