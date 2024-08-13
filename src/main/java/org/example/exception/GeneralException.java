package org.example.exception;


import java.util.List;

public class GeneralException extends RuntimeException {
    private String error;
    public GeneralException(String error) {
        this.error = error;
    }

    public String getMessage() {
        return error;
    }

}
