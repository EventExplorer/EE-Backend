package com.ee.customexception;

public class ArtistNotFoundException extends RuntimeException{

    public ArtistNotFoundException(String message){
        super(message);
    }
}
