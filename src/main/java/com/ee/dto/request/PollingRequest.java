package com.ee.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PollingRequest {


    public String categoryId;

    @NotBlank(message = "Required Artist Name")
    public String artistName;

    @NotBlank(message = "Required Your Vote")
    public String votes;

    @NotBlank(message = "Required Email Address")
    public String email;


    public LocalDateTime currentTS;
    public LocalDateTime updateTS;

//    @NotBlank(message = "Required State")
//    public String state;


}
