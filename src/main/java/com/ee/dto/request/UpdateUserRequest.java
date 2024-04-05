package com.ee.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Getter
@Setter
public class UpdateUserRequest {

    @NotBlank(message = "The provided email address is not valid. Please check and try again.")
    public String email;
    public String address;
    public String city;
    public String state;
    public String zipcode;
    public String gender = "OTHER";
    @NotBlank(message = "Phone Number is required")
    public String phoneNumber;

}
