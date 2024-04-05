package com.ee.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactUsRequest {


    @NotBlank(message="Required Customer Email ID")
    String emailId;
    @NotBlank(message="Required Customer Name")
    String name;

    @NotBlank(message="Required Customer Phone Number")
    String phoneNumber;

    @NotBlank(message="Required Customer Message")
    String message;


}
