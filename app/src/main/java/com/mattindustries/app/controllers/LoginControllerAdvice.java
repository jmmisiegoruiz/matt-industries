package com.mattindustries.app.controllers;

import com.mattindustries.app.api.client.exceptions.RestTemplateException;
import com.mattindustries.app.model.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class LoginControllerAdvice {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(value = RestTemplateException.class)
    ResponseEntity<ErrorResponse> handleMyRestTemplateException(RestTemplateException ex, HttpServletRequest request) {
        logger.error("An error happened while calling {} Downstream API: {}", ex.getApi(), ex.toString());
        return new ResponseEntity<>(new ErrorResponse(ex, request.getRequestURI()), ex.getStatusCode());
    }

}
