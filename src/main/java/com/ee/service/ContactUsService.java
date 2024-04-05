package com.ee.service;

import com.ee.customexception.CustomerNotFoundException;
import com.ee.dto.request.ContactUsRequest;
import com.ee.entities.ContactUsEntity;
import com.ee.entities.UserAccountEntity;
import com.ee.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;
import java.util.Random;

@Service
public class ContactUsService {
    private UserAccountRepository userAccountRepository;
    private  ContactRepository contactRepository;

    @Autowired
    private  EmailService emailService;
    @Autowired
    public ContactUsService(UserAccountRepository userAccountRepository, ContactRepository contactRepository){
        this.userAccountRepository = userAccountRepository;
        this.contactRepository = contactRepository;
    }
    public String contactInformation(ContactUsRequest contactUsRequest) throws IOException {
        Optional<UserAccountEntity> userProfile = userAccountRepository.findByUserEmail(contactUsRequest.getEmailId());

        if (userProfile.isPresent()) {
            ContactUsEntity contactUsEntity = new ContactUsEntity();

            //contactUsEntity.setId(new Random().nextInt());
            contactUsEntity.setEmail(contactUsRequest.getEmailId());
            contactUsEntity.setName(contactUsRequest.getName());
            contactUsEntity.setPhoneNumber(contactUsRequest.getPhoneNumber());
            contactUsEntity.setMessage(contactUsRequest.getMessage());

            emailService.sendContactUsEmail(contactUsRequest.getEmailId(),"Your Inquiry Received - We'll Be in Touch Soon!", contactUsRequest.getName(), contactUsRequest.getMessage());

            contactRepository.save(contactUsEntity);
            return "Thank you! Your message has been received and we will get back to you soon ";
        }else{
            throw new CustomerNotFoundException("Customer Not Found");
        }


    }
}
