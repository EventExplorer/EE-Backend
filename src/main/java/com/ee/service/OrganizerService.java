package com.ee.service;

import com.ee.dto.request.OrganizerRequest;
import com.ee.entities.OrganizerEntity;
import com.ee.entities.UserAccountEntity;
import com.ee.repository.OrganizerRepository;
import com.ee.repository.UserAccountRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OrganizerService {

    private OrganizerRepository organizerRepository;

    private UserAccountRepository userAccountRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    public OrganizerService(UserAccountRepository userAccountRepository, OrganizerRepository organizerRepository){
        this.userAccountRepository = userAccountRepository;
        this.organizerRepository = organizerRepository;
    }


    @SneakyThrows
    public String createOrganizer(OrganizerRequest request)  {

        Optional<UserAccountEntity> userProfile = userAccountRepository.findByUserEmail(request.getEmail());

        if(userProfile.isPresent()){

            OrganizerEntity oe = new OrganizerEntity();
            oe.setEmail(request.getEmail());
            oe.setPhoneNumber(request.getPhoneNumber());
            oe.setName(request.getName());
            oe.setStateId(request.getStateId());
            oe.setMessage(request.getMessage());
            oe.setCurrentTs(LocalDateTime.now());
            oe.setStatus("Active"); // needs to run the DMV check before active


            organizerRepository.save(oe);

            //Email Service
            emailService.sendOrganiserConfirmationEmail(request.getEmail());


            return "Congratulations, you're now officially an Explorer Event orchestrator! Welcome to the team where we turn ideas into unforgettable experiences.";
        }

        throw new AccountNotFoundException("User Not Found");
    }

    public Boolean isOrganizer(String email){
        Optional<UserAccountEntity> userProfile = userAccountRepository.findByUserEmail(email);

        if(userProfile.isPresent()){


            Optional<OrganizerEntity> oe = organizerRepository.findById(email);
            return oe.isPresent();
        }

        return false;
    }
}
