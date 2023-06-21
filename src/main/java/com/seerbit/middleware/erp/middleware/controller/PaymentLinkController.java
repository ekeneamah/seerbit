package com.seerbit.middleware.erp.middleware.controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seerbit.middleware.erp.middleware.model.Invoice;
import com.seerbit.middleware.erp.middleware.model.PaymentLink;



@RestController
@RequestMapping("/api/v1")
public class PaymentLinkController {

    @PostMapping("/sendPaymentlink")
    @ResponseBody
    public String sendPayload(@RequestBody PaymentLink payload) {
        // Set the URL of the API server
        String apiUrl = "https://paymentlink.seerbitapi.com/paymentlink/v2/payLinks/api";

        // Set the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String t = payload.getToken();
        headers.setBearerAuth(t);

        // Create the HTTP entity with the payload and headers
        HttpEntity<PaymentLink> requestEntity = new HttpEntity<>(payload, headers);

        // Create a RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Send the payload to the API server
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(apiUrl, requestEntity, String.class);

        // Return the response body
        return responseEntity.getBody();
    }





   

   
    private String convertToJson(Invoice orderRequest) {
        // Implement the logic to convert OrderRequest object to JSON string
        // You can use a library like Jackson or Gson to serialize the object
        // Here's an example using Jackson:
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(orderRequest);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    private String readResponse(InputStream inputStream) throws IOException {
        // Read the JSON response from the InputStream
        StringBuilder response = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        }
        return response.toString();
    }
}







