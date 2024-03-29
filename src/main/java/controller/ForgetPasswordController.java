package com.ee.controller;

import com.ee.helper.HttpHeader;
import com.ee.repository.UserAccountRepository;
import com.ee.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ForgetPasswordController {


    private  final UserService userService;
    @Autowired
    public ForgetPasswordController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("v1/forget-password")
    public ResponseEntity<String> forgetPassword(@RequestParam("email") String email) throws IOException {

        return ResponseEntity.ok().body(userService.sendPasswordResetEmail(email));

    }

    @PostMapping("v1/reset-password")
    public ResponseEntity<String> resetPassword(@NotBlank
                                                @RequestParam("email") String email,
                                                @RequestParam("token") String token,
                                                 @Validated
                                                 @RequestParam("password")
                                                 @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$" , message="Invalid Password")
                                                 String password){



        return ResponseEntity.ok().body(userService.resetPassword(email,token,password));

    }
}
