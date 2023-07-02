package com.seerbit.services;


    import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.seerbit.middleware.erp.middleware.model.POSTransaction;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {
    private final Firestore firestore;

    public UserService(Firestore firestore) {
        this.firestore = firestore;
    }

    public List<POSTransaction> getAllUsers() throws ExecutionException, InterruptedException {
        List<POSTransaction> users = new ArrayList<>();
        ApiFuture<QuerySnapshot> querySnapshot = firestore.collection("users").get();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            POSTransaction user = document.toObject(POSTransaction.class);
            users.add(user);
        }
        return users;
    }

    public POSTransaction addUser(POSTransaction user) {
        //return firestore.collection("users").add(user);

        DocumentReference docRef = firestore.collection("users").document();
        user.setId(docRef.getId());
        docRef.set(user);
        return user;
    }
}

