package com.ee.controller;

import com.ee.dto.request.PollingRequest;
import com.ee.dto.response.PollingStatResponse;
import com.ee.entities.ArtistEntity;
import com.ee.entities.CategoryEntity;
import com.ee.entities.CountiesEntity;
import com.ee.entities.RegionEntity;
import com.ee.repository.PollingRepository;
import com.ee.service.PollingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PollingController {

    private final PollingService pollingService;
    private final PollingRepository pollingRepository;

    @Autowired
    public PollingController(PollingService pollingService, PollingRepository pollingRepository) {
        this.pollingService = pollingService;
        this.pollingRepository = pollingRepository;
    }

    @GetMapping("v1/categories")
    public ResponseEntity<List<CategoryEntity>> findAllCategories(){

        return ResponseEntity.ok().body(pollingService.findAllCategories());
    }

    @GetMapping("v1/artists")
    public ResponseEntity<List<ArtistEntity>> findAllArtists(){

        return ResponseEntity.ok().body(pollingService.findAllArtists());

    }

    @GetMapping("v1/artists-by-category/{categoryId}")
    public ResponseEntity<List<ArtistEntity>> findAllArtistsByCategory(@PathVariable String categoryId){

        return ResponseEntity.ok().body(pollingService.findAllArtistsByCategory(categoryId));

    }

    @GetMapping("v1/artists/{artistId}")
    public ResponseEntity<List<ArtistEntity>> findArtistId(@PathVariable String artistId){

        return ResponseEntity.ok().body(pollingService.findArtistId(artistId));

    }

    @GetMapping("v1/regions")
    public ResponseEntity<List<RegionEntity>> findAllRegions(){

        return ResponseEntity.ok().body(pollingService.findAllRegions());

    }

    @GetMapping("v1/counties")
    public ResponseEntity<List<CountiesEntity>> findAllCounties(){

        return ResponseEntity.ok().body(pollingService.findAllCounties());

    }
    @PostMapping("v1/poll")
    public ResponseEntity<String> createPoll(@RequestBody PollingRequest pollingRequest){

        return ResponseEntity.ok().body(pollingService.createPoll(pollingRequest));

    }

    @GetMapping("/v1/check-vote/{email}")
    public boolean checkVote(@PathVariable String email) {


        return pollingService.checkArtistPresence(email);
    }

    @GetMapping("v1/organiser/categories/polling-stats/{categoryId}")
    public ResponseEntity<List<PollingStatResponse>> pollingStats(@PathVariable int categoryId){

        return ResponseEntity.ok().body(pollingService.pollingStats(categoryId));
    }


}