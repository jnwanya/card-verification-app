package com.jumia.jumiapay.web.exceptions;

/**
 * Created by jnwanya on
 * Sat, 30 Mar, 2019
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String errorMessage){
        super(errorMessage);
    }
}
