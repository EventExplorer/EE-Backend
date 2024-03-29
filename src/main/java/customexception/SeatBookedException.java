package com.ee.customexception;

public class SeatBookedException extends RuntimeException{

    public SeatBookedException(String message){
        super(message);
    }
}
