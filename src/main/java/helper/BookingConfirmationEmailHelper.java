package com.ee.helper;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Setter
@Getter
public class BookingConfirmationEmailHelper {

    private String customerName;
    private String eventName;
    private Date eventDate;
    private Time eventTime;
    private String eventVenue;
    private String numTickets;
    private String confirmationCode;
    private String email;
    private String seatNumber;


}
