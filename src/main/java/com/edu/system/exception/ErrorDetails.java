package com.edu.system.exception;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorDetails {

    private Date timestamp;
    private String message;
    private String path;

    public ErrorDetails(String message, String path) {
        this.timestamp = new Date();
        this.message = message;
        this.path = path;
    }
}
