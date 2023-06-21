package com.seerbit.middleware.erp.middleware.controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seerbit.middleware.erp.middleware.model.Invoice;
import com.seerbit.middleware.erp.middleware.model.PaymentLink;
import com.seerbit.middleware.erp.middleware.model.PaymentLinkPayload;
import com.seerbit.middleware.erp.middleware.model.PaymentLinkRequestData;



@RestController
@RequestMapping("/api/v1")
public class PaymentLinkController {
    
    @PostMapping("/paymentlink/get")
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

    @PostMapping("/paymentlink")
    public ResponseEntity<String> getPaymentLink(@RequestBody PaymentLinkRequestData requestData) {
        String publicKey = requestData.getPublickey();
        String bearerToken = requestData.getBearertoken();
//https://paymentlink.seerbitapi.com/paymentlink/v2/payLinks/paymentLinkId/77439439
        String url = "https://paymentlink.seerbitapi.com/paymentlink/v2/payLinks/api/" + publicKey;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(bearerToken);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

        // You can process the response or return it as-is
        return response;
    }

    @PostMapping("/paymentlink/byreferenceno")
    public ResponseEntity<String> getPaymentLinkbyRefid(@RequestBody PaymentLinkRequestData requestData) {
        String publicKey = requestData.getPublickey();
        String bearerToken = requestData.getBearertoken();
        String reference = requestData.getPaymentreference();
//https://paymentlink.seerbitapi.com/paymentlink/v2/payLinks/paymentLinkId/77439439
        String url = "https://paymentlink.seerbitapi.com/paymentlink/v2/payLinks/paymentLinkId/" + reference;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(bearerToken);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

        // You can process the response or return it as-is
        return response;
    }

    //update payment link 

    
    @PostMapping("/paymentlink/update")
    @ResponseBody
    public String updatePaymentLink(@RequestBody PaymentLinkPayload payload) {
        // Set the URL
        String url = "https://paymentlink.seerbitapi.com/paymentlink/v2/payLinks/api";

        // Set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(payload.getToken());

        // Set the request entity (URL, method, headers, and payload)
        HttpEntity<PaymentLinkPayload> requestEntity = new HttpEntity<>(payload, headers);

        // Create a RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Send the PUT request
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);

        // Process the response
        if (response.getStatusCode().is2xxSuccessful()) {
            return "Payment link updated successfully!";
        } else {
            return "Failed to update payment link.";
        }
    }


    @PostMapping("/paymentlink/delete/{paymentLinkId}")
    @ResponseBody
    public String deletePaymentLink(@RequestBody PaymentLinkRequestData requestData, @PathVariable String paymentLinkId) {
        // Set the URL
        String url = "https://paymentlink.seerbitapi.com/paymentlink/v2/payLinks/api/deleteLink/" + paymentLinkId;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(requestData.getBearertoken());

        // Set the request entity (URL, method, headers, and payload)
        HttpEntity<PaymentLinkPayload> requestEntity = new HttpEntity<>(headers);
        // Create a RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Send the DELETE request
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class);

        // Process the response
        if (response.getStatusCode().is2xxSuccessful()) {
            return "Payment link deleted successfully!";
        } else {
            return "Failed to delete payment link.";
        }
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







