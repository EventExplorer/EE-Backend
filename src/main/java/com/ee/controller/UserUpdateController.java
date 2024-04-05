package com.ee.controller;

import com.ee.dto.request.UpdateUserRequest;
import com.ee.helper.HttpHeader;
import com.ee.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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


    // GET mapping to retrieve user details
    @GetMapping("v1/details")
    public ResponseEntity<UpdateUserRequest> getUserDetails(@RequestParam("email") String email) {
        UpdateUserRequest userDetailsDTO = UserService.getUserDetails(email);
        return ResponseEntity.ok().body(userDetailsDTO);
    }

    // PUT mapping to update user details
    @PutMapping("v1/updateDetails")
    public ResponseEntity<String> updateUserDetails(@RequestBody UpdateUserRequest updateUserRequest) {
        String result = UserService.updateUserDetails(updateUserRequest);
        return ResponseEntity.ok().body(result);
    }


}
