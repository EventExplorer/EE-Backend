package com.ee.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RegistrationRequest {

    String userUUID;

    @NotBlank(message = "Email must not be blank")
    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", message = "Invalid Email Format")
    String email;

    @NotBlank(message = "Password must not be blank")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$" , message="Invalid Password")
    String password;

    @NotNull
    @Past(message = "Date Of Birth must be in past")
    LocalDate dateOfBirth;

    @NotBlank(message = "First Name must not be blank")
    String firstName;

    @NotBlank(message = "Last Name must not be blank")
    String lastName;






}
