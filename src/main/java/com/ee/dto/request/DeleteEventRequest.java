package com.ee.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class DeleteEventRequest {

    @NotBlank(message = "The provided Event ID is not valid. Please check and try again.")
    String eventId;
}