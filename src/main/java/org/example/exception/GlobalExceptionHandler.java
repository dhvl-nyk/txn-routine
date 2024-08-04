package org.example.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;
import java.util.stream.Collectors;

import static org.example.constants.TxnConstant.REQUEST_FAILED;
import static org.example.constants.TxnConstant.RESOURCE_NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage resourceNotFoundException(ResourceNotFoundException ex) {
        return new ErrorMessage(HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessages(),
                RESOURCE_NOT_FOUND);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    protected ErrorMessage handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {
        Map<String, List<String>> body = new HashMap<>();

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("errors", errors);
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(),
                new Date(),
                errors,
                REQUEST_FAILED);
    }


    @ExceptionHandler(value = {Exception.class})
    public ErrorMessage handleAnyException(Exception ex) {
        return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                Collections.singletonList(ex.getMessage()),
                REQUEST_FAILED);
    }



}
