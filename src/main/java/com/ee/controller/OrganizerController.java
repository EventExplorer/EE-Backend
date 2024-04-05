package com.ee.controller;

import com.ee.dto.request.OrganizerRequest;
import com.ee.service.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class OrganizerController {


    private OrganizerService organizerService;

    @Autowired
    public OrganizerController(OrganizerService organizerService){

        this.organizerService = organizerService;
    }

    @PostMapping("v1/organizer/create")
    public ResponseEntity<String> createOrganizer(@Valid @RequestBody OrganizerRequest organizerRequest){

        return ResponseEntity.ok().body(organizerService.createOrganizer(organizerRequest));
    }

    @GetMapping("v1/isOrganizer/{email}")
    public ResponseEntity<Boolean> isOrganizer(@PathVariable String email){

        return ResponseEntity.ok().body(organizerService.isOrganizer(email));
    }
}
