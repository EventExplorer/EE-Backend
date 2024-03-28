package com.ee.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingRequest {
    @NotBlank(message="Required Event ID")
    String eventId;

    @NotBlank(message="Required Customer Email ID")
    String emailId;

    @NotEmpty(message="Required Seat Number")
    String[] seatNumber;

    @NotBlank(message="Required Attendees")
    String attendees;

    String totalPrice;

    @NotBlank(message="Required Bank Name")
    String bankName;


}
