package com.seerbit.middleware.erp.middleware.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.seerbit.middleware.erp.middleware.model.PaymentLinkRequestData;
import com.seerbit.middleware.erp.middleware.model.VirtualAccountPayload;


@RestController
@RequestMapping("/api/v1")
public class VirtualAccountController {
    @PostMapping("/virtualAccount/create")
    @ResponseBody
    public String createVirtualAccount(@RequestBody VirtualAccountPayload payload) {
        // Set the URL
        String url = "https://seerbitapi.com/api/v2/virtual-accounts/";

        // Set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(payload.getToken());

        // Set the request entity (URL, method, headers, and payload)
        HttpEntity<VirtualAccountPayload> requestEntity = new HttpEntity<>(payload, headers);

        // Create a RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Send the POST request
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        // Process the response
        return responseEntity.getBody();
    }


    @PostMapping("/virtualAccount/byreferenceno")
    @ResponseBody
    public String getVirtualAccount(@RequestBody PaymentLinkRequestData payload) {
        // Set the URL
        String url = "https://seerbitapi.com/api/v2/virtual-accounts/"+ payload.getPaymentreference();

        // Set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(payload.getBearertoken());

        // Set the request entity (URL, method, headers, and payload)
        HttpEntity<VirtualAccountPayload> requestEntity = new HttpEntity<>(headers);

        // Create a RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Send the POST request
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

        // Process the response
        return responseEntity.getBody();
    }

    @PostMapping("/virtualAccount/delete")
    @ResponseBody
    public String deleteVirtualAccount(@RequestBody PaymentLinkRequestData payload) {
        // Set the URL
        String url = "https://seerbitapi.com/api/v2/virtual-accounts/"+ payload.getPaymentreference();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(payload.getBearertoken());

        // Set the request entity (URL, method, headers, and payload)
        HttpEntity<VirtualAccountPayload> requestEntity = new HttpEntity<>(headers);

        // Create a RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Send the POST request
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class);

        // Process the response
        return responseEntity.getBody();
    }
}
