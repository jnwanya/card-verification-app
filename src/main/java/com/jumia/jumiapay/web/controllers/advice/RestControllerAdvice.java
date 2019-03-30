package com.jumia.jumiapay.web.controllers.advice;

import com.jumia.jumiapay.web.exceptions.BadRequestException;
import com.jumia.jumiapay.web.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by jnwanya on
 * Sat, 30 Mar, 2019
 */
@Slf4j
@ControllerAdvice(basePackages = {"com.jumia.jumiapay.web.controllers.api"})
public class RestControllerAdvice {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity handleException(Exception exception) {
        log.error("Exception: ", exception.getMessage());
        exception.printStackTrace();
        return new ResponseEntity<>("Sorry, unable to process request at the moment.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity handleRuntimeException(RuntimeException exception) {
        log.error("RuntimeException: ", exception.getMessage());
        exception.printStackTrace();
        return new ResponseEntity<>("Sorry, unable to process request at the moment.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity handleBadRequestException(BadRequestException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleNotFoundException(NotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    } //

}
