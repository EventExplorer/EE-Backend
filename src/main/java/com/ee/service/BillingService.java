package com.ee.service;

import com.ee.customexception.BankInfoNotFoundException;
import com.ee.customexception.CustomerNotFoundException;
import com.ee.dto.request.BillingRequest;
import com.ee.dto.response.BillingResponse;
import com.ee.entities.BillingInfoEntity;
import com.ee.entities.UserAccountEntity;
import com.ee.repository.BillingRepository;
import com.ee.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BillingService {

    private BillingRepository billingRepository;

    private UserAccountRepository userAccountRepository;
    @Autowired
    public BillingService(UserAccountRepository userAccountRepository, BillingRepository billingRepository){
        this.userAccountRepository = userAccountRepository;
        this.billingRepository = billingRepository;
    }


    public String deleteCard(String email){

        Optional<UserAccountEntity> userProfile = userAccountRepository.findByUserEmail(email);

        if(userProfile.isPresent()){

            Optional<BillingInfoEntity> billingInfo = billingRepository.findCreditCardByUserEmail(email);

            if(billingInfo.isPresent()){

                billingRepository.delete(billingInfo.get());
                System.out.println("deleted the card");
                return "deleted";
            }

            throw new BankInfoNotFoundException("Credit Card Not Found");
        }

        throw new UsernameNotFoundException("User Not Found");
    }
public BillingResponse hasACreditCardOnFile(String email){

    Optional<UserAccountEntity> userProfile = userAccountRepository.findByUserEmail(email);

    if(userProfile.isPresent()){

        Optional<BillingInfoEntity> billingInfo = billingRepository.findCreditCardByUserEmail(email);

        if(billingInfo.isPresent()){

            return billingResponse(billingInfo.get().getBillingUuid());
        }

        throw new BankInfoNotFoundException("No card on file");

    }

    throw new UsernameNotFoundException("user not found");
}



    public BillingResponse billingInfoVerification(BillingRequest billingRequest) throws CustomerNotFoundException{

        Optional<UserAccountEntity> userProfile = userAccountRepository.findByUserEmail(billingRequest.getEmail());

        if(userProfile.isPresent()){
            BillingInfoEntity billingInfoEntity = new BillingInfoEntity();
            String exBankName = cardNumberVerification(billingRequest.getCardNumber());

            List<BillingInfoEntity> userList = billingRepository.findByUserEmail(billingRequest.getEmail());
            for(BillingInfoEntity entity: userList){
                if(entity.getBankName().equals(exBankName)){
                    entity.setCardHolderName(billingRequest.getCardHolderName());
                    entity.setCardNumber(billingRequest.getCardNumber());
                    entity.setCvv(Integer.parseInt(billingRequest.getCvv()));
                    String expiryDate = expiryDateVerification(billingRequest.getExpiryDate());
                    entity.setExpiryDate(expiryDate);
                    entity.setAddress(billingRequest.getAddress());
                    entity.setCity(billingRequest.getCity());
                    entity.setState(billingRequest.getState());
                    entity.setPhoneNumber(billingRequest.getPhoneNumber());
                    entity.setZipcode(Integer.parseInt(billingRequest.getZipcode()));

                    billingRepository.save(entity);
                    return billingResponse(entity.getBillingUuid());
                }
            }

                billingInfoEntity.setEmail(billingRequest.getEmail());
                billingInfoEntity.setCardHolderName(billingRequest.getCardHolderName());
                billingInfoEntity.setCvv(Integer.parseInt(billingRequest.getCvv()));
                billingInfoEntity.setAddress(billingRequest.getAddress());
                billingInfoEntity.setCity(billingRequest.getCity());
                billingInfoEntity.setState(billingRequest.getState());
                billingInfoEntity.setZipcode(Integer.parseInt(billingRequest.getZipcode()));
                billingInfoEntity.setPhoneNumber(billingRequest.getPhoneNumber());
                billingInfoEntity.setCardNumber(billingRequest.getCardNumber());
                billingInfoEntity.setBankName(exBankName);
                String expiryDate = expiryDateVerification(billingRequest.getExpiryDate());
                billingInfoEntity.setExpiryDate(expiryDate);

                String billingUUID = UUID.randomUUID().toString();
                billingInfoEntity.setBillingUuid(billingUUID);
                billingRepository.save(billingInfoEntity);
                return billingResponse(billingUUID);


        }

        throw new CustomerNotFoundException("Account Not Found");
    }

    public String cardNumberVerification(String cardNumber){

        if(cardNumber.startsWith("4")){
            return "VISA";
        }else if((cardNumber.startsWith("34")) || (cardNumber.startsWith("37"))){
            return "AMEX";
        }else if((cardNumber.startsWith("51")) || (cardNumber.startsWith("55"))){
            return "MASTER";
        }else if(cardNumber.startsWith("6011")){
            return "DISCOVER";
        }else{
            return "Undefined";
        }

    }

    public String expiryDateVerification(String expiryDate){

       return  expiryDate.replace("/", "");
    }

    public String responseExpiryDate(String expiryDate){

        String expiry = expiryDate;

        String month;
        String year;

        month = expiry.substring(0,2);
        year = expiry.substring(2,4);



        return month+"/"+year;
    }

    public BillingResponse billingResponse(String uuid){


        Optional<BillingInfoEntity> billingInfoEntity =  billingRepository.findById(uuid);

        if(billingInfoEntity.isPresent()){
            BillingResponse billingResponse = new BillingResponse();

            billingResponse.setCardNumber(billingInfoEntity.get().getCardNumber());


            billingResponse.setBankName(billingInfoEntity.get().getBankName());

            billingResponse.setCvv(Integer.toString(billingInfoEntity.get().getCvv()));
            billingResponse.setCardHolderName(billingInfoEntity.get().getCardHolderName());

            billingResponse.setExpiryDate(responseExpiryDate(billingInfoEntity.get().getExpiryDate()));
            return billingResponse;
        }
        return new BillingResponse();
    }

}
