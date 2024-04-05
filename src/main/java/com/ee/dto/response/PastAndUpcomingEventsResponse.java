package com.ee.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class PastAndUpcomingEventsResponse {

    private Date eventDate;
    private String orderNumber;
    private String eventName;
    private String venueName;
    private String city;
    private String state;
    private String venueType;
}
