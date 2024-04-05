package com.ee.helper;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class EventCreationEmailHelper {
    String userEmail;
    String eventName;
    String eventType;
    LocalDate eventDate;
    LocalTime eventTime;
    String venueName;
    String capacity;
    String regularPrice;
    String vipPrice;
    String disabledPrice;
    String city;
    String state;
    String contactNumber;
    String venueType;
    String description;
    String eventEmailAddress;
}
