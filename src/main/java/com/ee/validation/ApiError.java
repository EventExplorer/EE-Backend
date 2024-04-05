package com.ee.validation;

import java.util.List;

public class ApiError {
    private String status;
    private String message;
    private List<ErrorResponse> errors;

    // Constructors, getters, and setters

    public ApiError(String status, String message, List<ErrorResponse> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }
}

