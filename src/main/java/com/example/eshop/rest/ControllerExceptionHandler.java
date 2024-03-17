package com.example.eshop.rest;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice()
public class ControllerExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException e) {
    log.error("handleEntityNotFoundException():: " + e);
    return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(BindException.class)
  public ResponseEntity<Object> handleBindException(BindException e) {
    String msg = "Field validation error";
    if (e.hasFieldErrors()) {
      FieldError fe = e.getFieldError();
      if (fe != null) {
        String field = fe.getField();
        msg = switch (fe.getCode()) {
          case "NotNull" -> "Parameter '" + field + "' is required.";
          case "Size" -> "Parameter '" + field + "' doesn't meet size requirements.";
          case null -> msg;
          default -> msg;
        };
      }
    }
    log.error("handleBindException():: " + msg, e);
    return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(HttpMessageConversionException.class)
  public ResponseEntity<Object> handleHttpMessageConversionException(HttpMessageConversionException e) {
    log.error("handleHttpMessageConversionException():: ", e);
    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleGeneralFailure(Exception e) {
    String msg = "Unexpected error";
    log.error("handleGeneralFailure():: " + msg, e);
    return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
