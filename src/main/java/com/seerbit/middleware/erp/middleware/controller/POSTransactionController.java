package com.seerbit.middleware.erp.middleware.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.seerbit.middleware.erp.middleware.model.FirestorePOSTransactionRepository;
import com.seerbit.middleware.erp.middleware.model.POSTransaction;
import com.seerbit.middleware.erp.middleware.model.POSTransactionRepository;
import com.seerbit.services.UserService;

import java.util.List;
import java.util.concurrent.ExecutionException;

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
    public ResponseEntity<POSTransaction> createposTransaction(@RequestBody POSTransaction user) {
        POSTransaction createdUser = posTransactionRepository.save(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<POSTransaction> getposTransactionById(@PathVariable String id) {
        POSTransaction user = posTransactionRepository.findById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
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

