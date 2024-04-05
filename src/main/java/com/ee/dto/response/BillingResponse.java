package com.ee.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillingResponse {

    private String cardNumber;
    private String expiryDate;
    private String bankName;
    private String cvv;
    private String cardHolderName;

}
