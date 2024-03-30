package com.ee.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

@Getter
@Setter
public class EventResponse {

    private String eventId;
    private String eventName;
    private String eventType;
    private Date eventDate;
    private Time eventTime;
    private String venueName;
    private int capacity;
    private BigDecimal regularPrice;
    private BigDecimal vipPrice;
    private BigDecimal disabledPrice;
    private String city;
    private String state;
    private String contactNumber;
    private String venueType;
    private String description;
    private String eventEmailAddress;
    private String userEmail;
    private LocalDateTime createTimeStamp;
}