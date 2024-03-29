package com.ee.controller;

import com.ee.dto.request.UpdateUserRequest;
import com.ee.helper.HttpHeader;
import com.ee.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserUpdateController {

    private final UserService user;

    public UserUpdateController(UserService user) {
        this.user = user;
    }

    @PostMapping("v1/update")
    public ResponseEntity<String>  UpdateUserInfo(@RequestBody UpdateUserRequest updateUserProfile){


        return ResponseEntity.ok().body(user.updateUserInfo(updateUserProfile));

    }

}
