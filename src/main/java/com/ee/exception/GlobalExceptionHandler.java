package com.ee.exception;

import com.ee.customexception.*;
import com.ee.helper.HttpHeader;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import javax.validation.ConstraintViolationException;
import java.time.Duration;
import java.time.format.DateTimeParseException;

@RestControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDuplicateEntry(DataIntegrityViolationException e){

        //System.out.println("Error :"+ e.getRootCause().getMessage());

        if(e.getRootCause().getMessage().contains("first_name")){

            return ResponseEntity.badRequest().body("Please enter valid first name");

        }

        if(e.getRootCause().getMessage().contains("last_name")){

            return ResponseEntity.badRequest().body("Please enter valid last name");

        }

        if(e.getRootCause().getMessage().contains("email")){
            return ResponseEntity.badRequest().body("Email address is already existed in our system, Please use sign in");
        }
        if(e.getRootCause().getMessage().contains("password")){
            return ResponseEntity.badRequest().body("Password is not valid");

        }
        if(e.getRootCause().getMessage().contains("date_of_birth")){
            return ResponseEntity.badRequest().body("Please enter date of birth in YYYY-MM-DD Format");

        }

        return ResponseEntity.badRequest().body("An Error Occured wrong" + e.getMessage());

    }
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> handleUserLoginError(CustomerNotFoundException e){

        if(e.getMessage().isEmpty()){
            return ResponseEntity.notFound().build();
        }

        if(e.getMessage().equals("password-reset")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.badRequest().body(e.getMessage());
    }


    @ExceptionHandler(AccountNotVerifiedException.class)
    public ResponseEntity<String> handleUserLoginError(AccountNotVerifiedException e){

        if(e.getMessage().isEmpty()){
            return ResponseEntity.notFound().build();

        }
        return ResponseEntity.badRequest().body(e.getMessage());

    }

    @ExceptionHandler(BankInfoNotFoundException.class)
    public ResponseEntity<String> bankInfoRequiredError(BankInfoNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(SeatBookedException.class)
    public ResponseEntity<String> seatBookedError(SeatBookedException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleMethodValidation(ConstraintViolationException e){

        return ResponseEntity.badRequest().body(e.getMessage());

    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handOtherExceptions(RuntimeException e){
        return ResponseEntity.badRequest().body(e.getMessage());

    }




    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<String> handleDateValidation(DateTimeParseException dateTimeParseException) {

        String customDateMessage = "InValid Format. Use this for Date[YYYY-MM-DD] & Time[HH:MM:SS] ";
        return ResponseEntity.badRequest().body(customDateMessage);
    }

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<String> handleEventNotFoundException(EventNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }



}

