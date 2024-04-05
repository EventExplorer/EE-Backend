package com.ee.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrganizerRequest {

    String email;
    String name;
    String phoneNumber;

    @NotBlank(message="We need State ID to make you Organizer")
    String stateId;

    String message;
}
