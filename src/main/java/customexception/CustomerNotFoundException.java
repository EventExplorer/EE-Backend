package com.ee.customexception;



    public class CustomerNotFoundException extends RuntimeException {

        public CustomerNotFoundException(String message) {
            super(message);
        }

        // You can add additional constructors or methods as needed
    }


