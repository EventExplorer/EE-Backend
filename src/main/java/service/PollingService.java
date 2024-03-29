package com.ee.service;

import com.ee.customexception.ArtistNotFoundException;
import com.ee.customexception.CustomerNotFoundException;
import com.ee.dto.request.PollingRequest;
import com.ee.dto.response.PollingStatResponse;
import com.ee.entities.*;
import com.ee.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PollingService {

    private final ArtistRepository artistRepository;
    private final CategoryRepository categoryRepository;

    private final RegionRepository regionRepository;

    private final CountiesRepository countiesRepository;

    private final UserAccountRepository userAccountRepository;

    private final PollingRepository pollingRepository;

    @Autowired
    public PollingService(PollingRepository pollingRepository, ArtistRepository artistRepository, CategoryRepository categoryRepository, RegionRepository regionRepository, CountiesRepository countiesRepository, UserAccountRepository userAccountRepository) {
        this.artistRepository = artistRepository;
        this.categoryRepository = categoryRepository;
        this.regionRepository = regionRepository;
        this.countiesRepository = countiesRepository;
        this.userAccountRepository = userAccountRepository;
        this.pollingRepository = pollingRepository;
    }


    public List<CategoryEntity> findAllCategories(){

        return categoryRepository.findAll();
    }

    public List<ArtistEntity> findAllArtists(){

        return artistRepository.findAll();
    }

    public List<ArtistEntity> findAllArtistsByCategory(String categoryId){

        return artistRepository.findArtistByCategoryId(categoryId);
    }
    public List<ArtistEntity> findArtistId(String artistId){

       Optional<List<ArtistEntity>> artistById = artistRepository.findArtistById(artistId);

        return artistById.orElse(null);
    }

    public List<RegionEntity> findAllRegions(){

        return regionRepository.findAll();
    }

    public List<CountiesEntity> findAllCounties(){

        return countiesRepository.findAll();
    }

    public String createPoll(PollingRequest pollingRequest){
        Optional<UserAccountEntity> userProfile = userAccountRepository.findByUserEmail(pollingRequest.getEmail());
        PollingEntity pe = new PollingEntity();

        if(userProfile.isPresent()) {
            Optional<ArtistEntity> categoryId = artistRepository.findArtistByName(pollingRequest.artistName);

            if (categoryId.isPresent()) {
                    pe.setCategoryId(String.valueOf(categoryId.get().getCategoryId()));
                    pe.setArtistName(pollingRequest.getArtistName());
                    pe.setVotes(pollingRequest.getVotes());
                    pe.setEmail(pollingRequest.email);
                    LocalDateTime currentTimeStamp = LocalDateTime.now();
                    pe.setCurrentTs(Timestamp.valueOf(currentTimeStamp));
                    pe.setUpdateTs(Timestamp.valueOf(currentTimeStamp));
                    //pe.setState(pollingRequest.getState());
                    pollingRepository.save(pe);
            } else {
                throw new ArtistNotFoundException("Artist Not Found");
            }
        }else{
            throw new CustomerNotFoundException("Account Not Found");
        }
        return "Successfully submitted the poll";
    }

    public List<PollingStatResponse> pollingStats(){

        List<Object[]> pollingProfile = pollingRepository.findDistinctCategoryStateArtistCount();

        List<PollingStatResponse> pollingResultList = new ArrayList<>();

        for(Object[] pollingObject: pollingProfile){
            PollingStatResponse response = new PollingStatResponse();

            response.setArtistName((String) pollingObject[2]);
            response.setState((String) pollingObject[1]);
            response.setVotes((Long) pollingObject[3]);


            pollingResultList.add(response);

        }

        return pollingResultList;

    }


    public boolean checkArtistPresence(String email, String artistName) {

        List<PollingEntity> artist = pollingRepository.findByUserEmailAndaAndArtistName(email, artistName);

        return !artist.isEmpty();
    }


}
