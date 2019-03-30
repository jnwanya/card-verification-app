package com.jumia.jumiapay.web.exceptions;

/**
 * Created by jnwanya on
 * Sat, 30 Mar, 2019
 */
public class BadRequestException extends RuntimeException {
    public BadRequestException(String errorMessage) {
        super(errorMessage);
    }
}
