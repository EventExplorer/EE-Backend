package com.ee.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
public class UpdateEventRequest {

    @NotBlank(message = "The provided Event ID is not valid. Please check and try again.")
    String eventId;

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
    String VenueName;

    @Pattern(regexp = "^[1-9]\\d*$", message = "Invalid Capacity.")
    String capacity;

    LocalDateTime updateTS;

    @Pattern(regexp = "^\\d+(\\.\\d+)?$", message = "Invalid Price.")
    String price;

    @NotBlank(message = "Required City")
    String city;

    @NotBlank(message = "Required State")
    String state;

    @Pattern(regexp = "^\\d{10}$", message = "Invalid Phone Number")
    String contactNumber;

    @NotBlank(message = "Required Venue Type")
    String venueType;

    @NotBlank(message = "Required Description")
    @Size(max = 500, message = "Description should not exceed 500 characters")
    String description;

    @NotBlank(message = "Require Email Address")
    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", message = "Invalid Email Format")
    String emailAddress;


}