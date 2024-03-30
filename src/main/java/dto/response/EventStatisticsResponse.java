package com.ee.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EventStatisticsResponse {


        String eventName;

        String eventDescription;

        String eventType;

        String eventCity;

        String eventState;

        String eventCapacity;

        Date eventDate;

        String soldCapacity;

        String remainingCapacity;

        String totalRevenue;




}
