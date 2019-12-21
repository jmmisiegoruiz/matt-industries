package com.mattindustries.users.controllers;

import org.springframework.hateoas.mediatype.vnderrors.VndErrors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@ControllerAdvice(annotations = RestController.class)
public class UsersControllerAdvice {

    private final MediaType vndErrorMediaType = MediaType.parseMediaType("application/vnd.error+json");

    @ExceptionHandler(UserNotFoundException.class)
    ResponseEntity<VndErrors> notFoundException(UserNotFoundException exception) {
        String logRef = (exception.getId() != null) ? exception.getId() + "" : exception.getPrincipal();
        return this.error(exception, HttpStatus.NOT_FOUND, logRef);
    }

    private <E extends Exception> ResponseEntity<VndErrors> error(E exception, HttpStatus httpStatus, String logRef) {
        String message = Optional.ofNullable(exception.getMessage()).orElse(exception.getClass().getSimpleName());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(this.vndErrorMediaType);
        return new ResponseEntity<>(new VndErrors(logRef, message), httpHeaders, httpStatus);
    }
}
