package com.ee.exception;

import com.ee.helper.HttpHeader;
import com.ee.validation.ApiError;
import com.ee.validation.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidation(MethodArgumentNotValidException e){

       /* List<String> errors = e.getBindingResult().getFieldErrors()
                            .stream()
                            .map(error -> error.getField() + ": " + error.getDefaultMessage())
                            .toList();*/




        return ResponseEntity.badRequest()
                             //.headers(HttpHeader.httpHeaders())
                             .body(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }
}
