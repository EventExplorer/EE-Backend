package com.ee.controller;

import com.ee.dto.request.BillingRequest;
import com.ee.dto.response.BillingResponse;
import com.ee.service.BillingService;
import com.ee.service.EventService;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class BillingController {

   private BillingService billingService;
   @Autowired
   public BillingController(BillingService billingService){
       this.billingService = billingService;
   }


    @PostMapping("v1/customer/billing-info")
    public ResponseEntity<BillingResponse> billing(@Valid @RequestBody BillingRequest billingRequest){

       return  ResponseEntity.ok().body(billingService.billingInfoVerification(billingRequest));


    }

    @GetMapping("v1/customers/{email}/billing-info/has-credit-card")
    public ResponseEntity<BillingResponse> hasACard(@PathVariable String email){

       return ResponseEntity.ok().body(billingService.hasACreditCardOnFile(email));
    }

    @DeleteMapping("v1/customers/{email}/credit-card")
    public ResponseEntity<String> deleteCard(@PathVariable String email){

        System.out.println("delete start");
       return ResponseEntity.ok().body(billingService.deleteCard(email));
    }
}
