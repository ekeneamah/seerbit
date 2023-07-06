package com.seerbit.middleware.erp.middleware.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.seerbit.middleware.erp.middleware.model.POSRefData;
import com.seerbit.middleware.erp.middleware.model.POSTransaction;
import com.seerbit.middleware.erp.middleware.model.POSTransactionRepository;
import com.seerbit.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/postransaction")
public class POSTransactionController {
    private final POSTransactionRepository posTransactionRepository;
    private final UserService userService;

    @Autowired
    public POSTransactionController(POSTransactionRepository posTransactionRepository,UserService userService) {
        this.posTransactionRepository = posTransactionRepository;
        this.userService = userService;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<POSTransaction> createposTransaction(@RequestBody POSTransaction tr) {
        POSTransaction createdPOSTransaction = posTransactionRepository.save(tr);
        return new ResponseEntity<>(createdPOSTransaction, HttpStatus.CREATED);
       /*  POSTransaction trs = posTransactionRepository.findById(createdPOSTransaction.getId(),createdPOSTransaction.getPosid());
        if (trs != null) {
            return new ResponseEntity<>(trs, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(trs,HttpStatus.NOT_FOUND);
        }*/
    }

    @PostMapping("/verifytrasactionbyposid")
    @ResponseBody
    public ResponseEntity<String> verifytrasactionbyposid(@RequestBody POSRefData requestData) {
        POSTransaction user = posTransactionRepository.findById(requestData.getTransactionId(),requestData.getPosid());
        if (user != null) {
            String publicKey = requestData.getPubkey();
        String bearerToken = requestData.getToken();
        String reference = requestData.getTransactionId();
//https://paymentlink.seerbitapi.com/paymentlink/v2/payLinks/paymentLinkId/77439439

        String url = "https://seerbitapi.com/api/v2/payments/query/" + user.getTransactionRef();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(bearerToken);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        
            return response;
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<POSTransaction>> getAllPOSTransaction() {
        List<POSTransaction> tr = posTransactionRepository.findAll();
        return new ResponseEntity<>(tr, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteposTransaction(@PathVariable String id) {
        posTransactionRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    
}

