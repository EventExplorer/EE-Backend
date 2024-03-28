package com.ee.customexception;

public class BankInfoNotFoundException extends RuntimeException{

    public BankInfoNotFoundException(String message){
        super(message);
    }
}
