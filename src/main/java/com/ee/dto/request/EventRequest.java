package com.ee.dto.request;


import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
public class EventRequest {

    String eventId="";

    String customerID;

    @NotBlank(message = "Email must not be blank")
    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", message = "Invalid Email Format")
    String userEmail;

    @NotBlank(message = "Required Event Name")
    String eventName;

    @NotBlank(message = "Required Event Type")
    String eventType;

    @NotNull(message = "Required Event Date")
    @Future(message = "Event Date must be in the future")
    LocalDate eventDate;

    @NotNull(message = "Required Event Time")
    LocalTime eventTime;

    @NotBlank(message = "Required Venue Name")
    String venueName;

    @Pattern(regexp = "^[1-9]\\d*$", message = "Invalid Capacity.")
    String capacity;

    LocalDateTime createTS;
    LocalDateTime updateTS;

    @Pattern(regexp = "^\\d+(\\.\\d+)?$", message = "Invalid Price.")
    String regularPrice;

    @Pattern(regexp = "^\\d+(\\.\\d+)?$", message = "Invalid Price.")
    String vipPrice;

    @Pattern(regexp = "^\\d+(\\.\\d+)?$", message = "Invalid Price.")
    String disabledPrice;

    @NotBlank(message = "Required City")
    String city;

    @NotBlank(message = "Required State")
    String state;

    @Pattern(regexp = "^\\d{10}$", message = "Invalid Phone Number")
    String contactNumber;

    @NotBlank(message = "Required Venue Type")
    String venueType;

    @NotBlank(message = "Required Description")
    @Size(max = 10000, message = "Description should not exceed 500 characters")
    String description;

    @NotBlank(message = "Require Email Address")
    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", message = "Invalid Email Format")
    String eventEmailAddress;


}
