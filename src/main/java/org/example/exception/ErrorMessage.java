package org.example.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ErrorMessage {
    private int statusCode;
    private Date timestampssss;
    private List<String> message;
    private String description;
}