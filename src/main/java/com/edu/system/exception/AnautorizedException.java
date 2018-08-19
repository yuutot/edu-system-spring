package com.edu.system.exception;

public class AnautorizedException extends ServiceException {
    public AnautorizedException() {
    }

    public AnautorizedException(String message) {
        super(message);
    }

    public AnautorizedException(String message, Throwable cause) {
        super(message, cause);
    }
}
