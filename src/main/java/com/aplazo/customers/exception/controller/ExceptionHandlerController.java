package com.aplazo.customers.exception.controller;

import com.aplazo.customers.constants.LogConstants;
import com.aplazo.customers.exception.error.BadRequestException;
import com.aplazo.customers.exception.error.InternalErrorException;
import com.aplazo.customers.exception.error.NotFoundException;
import com.aplazo.customers.exception.model.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        
        log.error(LogConstants.ERROR_LOG, ex.getMessage(), request.getRequestURI());
        ErrorResponse response = new ErrorResponse();
        response.setPath(request.getRequestURI());
        response.setMensaje(ex.getMessage());
        response.setTimestamp(Instant.now().getEpochSecond());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> resourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        
        log.error(LogConstants.ERROR_LOG, ex.getMessage(), request.getRequestURI());
        ErrorResponse response = new ErrorResponse();
        response.setPath(request.getRequestURI());
        response.setMensaje(ex.getMessage());
        response.setError(ex.getMessage());
        response.setTimestamp(Instant.now().getEpochSecond());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> badRequestException(BadRequestException ex, HttpServletRequest request) {
        
        log.error(LogConstants.ERROR_LOG, ex.getMessage(), request.getRequestURI());
        ErrorResponse response = new ErrorResponse();
        response.setPath(request.getRequestURI());
        response.setMensaje(ex.getMessage());
        response.setError(ex.getMessage());
        response.setTimestamp(Instant.now().getEpochSecond());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> notFoundException(NotFoundException ex, HttpServletRequest request) {
        
        log.error(LogConstants.ERROR_LOG, ex.getMessage(), request.getRequestURI());
        ErrorResponse response = new ErrorResponse();
        response.setPath(request.getRequestURI());
        response.setMensaje(ex.getMessage());
        response.setError(ex.getMessage());
        response.setTimestamp(Instant.now().getEpochSecond());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InternalErrorException.class)
    public ResponseEntity<ErrorResponse> internalErrorException(InternalErrorException ex, HttpServletRequest request) {
        
        log.error(LogConstants.ERROR_LOG, ex.getMessage(), request.getRequestURI());
        ErrorResponse response = new ErrorResponse();
        response.setPath(request.getRequestURI());
        response.setMensaje(ex.getMessage());
        response.setError(ex.getMessage());
        response.setTimestamp(Instant.now().getEpochSecond());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> noResourceFoundException(NoResourceFoundException ex, HttpServletRequest request) {
        
        log.error(LogConstants.ERROR_LOG, ex.getMessage(), request.getRequestURI());
        ErrorResponse response = new ErrorResponse();
        response.setPath(request.getRequestURI());
        response.setMensaje(ex.getMessage());
        response.setError(ex.getMessage());
        response.setTimestamp(Instant.now().getEpochSecond());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
