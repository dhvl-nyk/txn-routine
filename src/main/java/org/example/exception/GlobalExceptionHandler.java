package org.example.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;
import java.util.stream.Collectors;

import static org.example.constants.TxnConstant.REQUEST_FAILED;
import static org.example.constants.TxnConstant.RESOURCE_NOT_FOUND;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorMessage> resourceNotFoundException(ResourceNotFoundException ex) {
        ErrorMessage errorResponse = new ErrorMessage(HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessages(),
                RESOURCE_NOT_FOUND);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        ErrorMessage errorResponse = new ErrorMessage(HttpStatus.BAD_REQUEST.value(),
                new Date(),
                errors,
                REQUEST_FAILED);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value =  DataIntegrityViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ErrorMessage> handleDataIntegrityViolationException(
            DataIntegrityViolationException ex) {

        ErrorMessage errorResponse = new ErrorMessage(INTERNAL_SERVER_ERROR.value(),
                new Date(),
                Collections.singletonList(ex.getMessage()),
                INTERNAL_SERVER_ERROR.getReasonPhrase());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {GeneralException.class})
    @ResponseStatus(value = INTERNAL_SERVER_ERROR)
    public ErrorMessage handleGeneralException(GeneralException ex) {
        return new ErrorMessage(INTERNAL_SERVER_ERROR.value(),
                new Date(),
                Collections.singletonList(ex.getMessage()),
                REQUEST_FAILED);
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(value = INTERNAL_SERVER_ERROR)
    public ErrorMessage handleAnyException(Exception ex) {
        return new ErrorMessage(INTERNAL_SERVER_ERROR.value(),
                new Date(),
                Collections.singletonList(ex.getMessage()),
                REQUEST_FAILED);
    }



}
