package com.ee.controller;

import com.ee.dto.request.RegistrationRequest;
import com.ee.helper.HttpHeader;
import com.ee.service.RestTemplateService;
import com.ee.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.time.Duration;

@RestController
@Validated
@CrossOrigin
public class SignUpController {

    private final UserService user;

    private final RestTemplateService restTemplate;

    @Autowired
    public SignUpController(UserService userService, RestTemplateService restTemplate) {
        user = userService;
        this.restTemplate = restTemplate;
    }

    @PostMapping("v1/signup")
    public ResponseEntity<String> register(@Valid @RequestBody RegistrationRequest registrationRequest) throws IOException {


       return ResponseEntity.ok().body(user.createUser(registrationRequest));

    }


    @GetMapping("delete")
    public String delete(){

        user.deleteAll();
        return "done";
    }

}
