package com.ee.controller;

import com.ee.dto.request.BookingRequest;
import com.ee.service.BillingService;
import com.ee.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

@RestController
public class BookingController {
    private BookingService bookingService;
    @Autowired
    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }


    @PostMapping("v1/customer/event/booking")
    public ResponseEntity<String> booking(@Valid @RequestBody BookingRequest bookingRequest) throws IOException{

            return ResponseEntity.ok().body(bookingService.bookingInformation(bookingRequest));


    }
}
