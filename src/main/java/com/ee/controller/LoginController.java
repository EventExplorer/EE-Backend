package com.ee.controller;

import com.ee.dto.request.LoginRequest;
import com.ee.helper.HttpHeader;
import com.ee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class LoginController {

    private final UserService user;

    @Autowired
    public LoginController(UserService userService) {
        user = userService;
    }


    @PostMapping(value = "v1/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest loginProfile) {

        return ResponseEntity.ok()
                .body(user.findUser(loginProfile));


    }

}

