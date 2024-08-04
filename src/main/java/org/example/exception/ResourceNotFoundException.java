package org.example.exception;

import java.util.List;

public class ResourceNotFoundException extends RuntimeException {

    private List<String> errors;

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getMessages() {
        return errors;
    }


}