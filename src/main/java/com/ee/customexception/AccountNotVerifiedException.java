package com.ee.customexception;

public class AccountNotVerifiedException extends RuntimeException{


        public AccountNotVerifiedException(String message) {
            super(message);
        }

        // You can add additional constructors or methods as needed
    }

