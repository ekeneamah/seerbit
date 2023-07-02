package com.seerbit.middleware.erp.middleware.model;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class FirestorePOSTransactionRepository implements POSTransactionRepository {
    private final Firestore firestore;
    private final CollectionReference usersCollection;

    public FirestorePOSTransactionRepository(Firestore firestore) {
        //this.firestore = firestore;
        this.firestore = firestore;
        this.usersCollection = firestore.collection("transactions");
    }

    @Override
    public POSTransaction save(POSTransaction user) {
      //  DocumentReference docRef = firestore.collection("users").document();
        DocumentReference docRef = firestore.collection("transactions").document("postransactions").collection(user.getPosid()).document();

        user.setId(docRef.getId());
        docRef.set(user);
        return user;
    }

    @Override
    public POSTransaction findById(String id,String posid) {
        DocumentReference docRef = firestore.collection("transactions").document("postransactions").collection(posid).document(id);
        DocumentSnapshot snapshot;
        try {
            snapshot = docRef.get().get();
        } catch (InterruptedException | ExecutionException e) {
            // Handle exception
            return null;
        }
        if (snapshot.exists()) {
            return snapshot.toObject(POSTransaction.class);
        } else {
            return null;
        }
    }

    @Override
    public List<POSTransaction> findAll() {
        CollectionReference collectionRef = firestore.collection("users");
        QuerySnapshot snapshot;
        try {
            snapshot = collectionRef.get().get();
        } catch (InterruptedException | ExecutionException e) {
            // Handle exception
            return new ArrayList<>();
        }
        List<POSTransaction> users = new ArrayList<>();
        for (QueryDocumentSnapshot doc : snapshot) {
            users.add(doc.toObject(POSTransaction.class));
        }
        return users;
    }

    @Override
    public void delete(String id) {
        DocumentReference docRef = firestore.collection("users").document(id);
        docRef.delete();
    }

    public POSTransaction getTransactionByTrxRef(String email) throws ExecutionException, InterruptedException {
        ApiFuture<DocumentSnapshot> documentSnapshotFuture = usersCollection.document(email).get();
        DocumentSnapshot document = documentSnapshotFuture.get();

        if (document.exists()) {
            return document.toObject(POSTransaction.class);
        }

        return null; // User with the given email not found
    }
}
