package com.ee.controller;

import com.ee.dto.request.DeleteEventRequest;
import com.ee.dto.request.EventRequest;
import com.ee.dto.response.EventResponse;
import com.ee.dto.response.EventStatisticsResponse;
import com.ee.dto.response.PastAndUpcomingEventsResponse;
import com.ee.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("v1/organiser/events/create")
    public ResponseEntity<String> createEvent(@Valid @RequestBody EventRequest eventRequest) throws IOException {

        return ResponseEntity.ok().body(eventService.createEventFor(eventRequest));
    }

    @GetMapping("v1/events")
    public List<EventResponse> getAllEvents() {

        return eventService.getAllEvents();

    }

    @GetMapping("v1/events/{email}")
    public List<EventResponse> getEventsById(@PathVariable String email) {
        return eventService.getAllEventsById(email);

    }


    @DeleteMapping("v1/organiser/delete")
    public String deleteEvent(@RequestBody DeleteEventRequest deleteEventRequest) {

        return eventService.deleteEvent(deleteEventRequest);

    }

    @GetMapping("v1/customer/events/past/{email}")
    public List<PastAndUpcomingEventsResponse> pastEvents(@PathVariable String email){

        return eventService.pastEvents(email);
    }

    @GetMapping("v1/customer/events/upcoming/{email}")
    public List<PastAndUpcomingEventsResponse> upcomingEvents(@PathVariable String email){

        return eventService.futureEvents(email);
    }


    @GetMapping("v1/event-statistics/{email}")
    public List<EventStatisticsResponse> eventStatistics(@PathVariable String email){

        return eventService.eventStatistics(email);

    }
}