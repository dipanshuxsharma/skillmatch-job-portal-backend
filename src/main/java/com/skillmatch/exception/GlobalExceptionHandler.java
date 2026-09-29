package com.skillmatch.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Duplicate application
    @ExceptionHandler(DuplicateApplicationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, Object> handleDuplicateApplication(
            DuplicateApplicationException ex) {

        Map<String, Object> response = new HashMap<>();

        response.put("status", 409);
        response.put("message", ex.getMessage());

        return response;
    }

    // Resource not found
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handleResourceNotFound(
            ResourceNotFoundException ex) {

        Map<String, Object> response = new HashMap<>();

        response.put("status", 404);
        response.put("message", ex.getMessage());

        return response;
    }

    // Other RuntimeExceptions
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleRuntimeException(
            RuntimeException ex) {

        Map<String, Object> response = new HashMap<>();

        response.put("status", 400);
        response.put("message", ex.getMessage());

        return response;
    }
}