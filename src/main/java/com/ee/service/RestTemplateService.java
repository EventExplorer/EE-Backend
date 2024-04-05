package com.ee.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateService {

    private final RestTemplate restTemplate;


    @Autowired
    public RestTemplateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public void callExternalAPI() throws JsonProcessingException {


        String ticketURL = "https://app.ticketmaster.com/discovery/v2/events?apikey=JGuimADvJB6gzXP7jnjTGaFsm91fdQjV&locale=*";

        String apiKey = "G625VPSGXCWGJ4CPLP5R";
        String apiUrl = "https://www.eventbriteapi.com";
        //String apiUrl = "https://www.eventbriteapi.com/v3/users/me/?token=G625VPSGXCWGJ4CPLP5R";

        String catgoriesURL = "https://private-anon-7dadb30f5a-eventbriteapiv3public.apiary-mock.com/v3/categories/";

        // Set API key in header
        HttpHeaders headers = new HttpHeaders();
        //headers.set(HttpHeaders.AUTHORIZATION, apiKey);
       // headers.setBearerAuth(apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);


       /* Host: www.eventbriteapi.com
        Authorization: Bearer G625VPSGXCWGJ4CPLP5R*/

        // Make API call
        ResponseEntity<String> response = restTemplate.exchange(ticketURL, HttpMethod.GET, entity, String.class);

        // Process the response
        if (response.getStatusCode() == HttpStatus.OK) {

            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(response.getBody());

            for(JsonNode n: node){

                //n.get()
            }
            String responseBody = response.getBody();
            //System.out.println("REST Client - "+responseBody);
            // Process the response body
        }
    }


}

