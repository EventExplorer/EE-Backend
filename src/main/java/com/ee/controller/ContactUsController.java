package com.ee.controller;

import com.ee.dto.request.ContactUsRequest;
import com.ee.service.BookingService;
import com.ee.service.ContactUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

@RestController
public class ContactUsController {

    private ContactUsService contactUsService;
    @Autowired
    public ContactUsController(ContactUsService contactUsService){
        this.contactUsService = contactUsService;
    }

    @PostMapping("v1/customer/contact-us")
    public ResponseEntity<String> contactUs(@Valid @RequestBody ContactUsRequest contactUsRequest) throws IOException {

        return ResponseEntity.ok().body(contactUsService.contactInformation(contactUsRequest));
    }
}
