package com.ee.controller;

import com.ee.dto.request.VerifyOTPRequest;
import com.ee.helper.HttpHeader;
import com.ee.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class VerifyOTPController {

    private final UserService user;

    public VerifyOTPController(UserService user) {
        this.user = user;
    }


    @PostMapping("v1/verify")
    public ResponseEntity<String> verify(@Valid @RequestBody VerifyOTPRequest verifyOTPRequest){

        return ResponseEntity.ok().body(user.verifyOTP(verifyOTPRequest));
    }
}
