package com.ee.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillingRequest {

    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", message = "Invalid Email Format")
    @NotBlank(message = "Required Email Address")
    String email;

    @NotBlank(message = "Required Card Holder Name")
    String cardHolderName;

    @Pattern(regexp = "^[1-9]\\d{11}$", message="Invalid Card Number")
    @NotBlank(message = "Required Card Number")
    String cardNumber;

    @NotBlank(message = "Required CVV Number")
    @Pattern(regexp="^[1-9]\\d{2,3}$", message = "Invalid CVV Number")
    String cvv;

    @Pattern(regexp = "^(0[1-9]|1[0-2])\\/\\d{2}$", message = "Invalid Expiry Date")
    @NotBlank(message = "Required Expiry Date[MM/YY]")
    String expiryDate;

    @NotBlank(message = "Required Address")
    String address;

    @NotBlank(message = "Required City")
    String city;

    @NotBlank(message = "Required State")
    String state;

    @Pattern(regexp = "^\\d{10}$", message = "Invalid Phone Number")
    @NotBlank(message = "Required Phone Number")
    String phoneNumber;

    @Pattern(regexp = "^\\d{5}$", message = "Invalid Zipcode")
    @NotBlank(message = "Required Zipcode")
    String zipcode;

}
