package com.seerbit.middleware.erp.middleware.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreException;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.auth.FirebaseAuthException;

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
     try{ //  DocumentReference docRef = firestore.collection("users").document();
        DocumentReference docRef = firestore.collection("transactions").document("postransactions").collection(user.getPosid()).document();

        user.setId(docRef.getId());
        docRef.set(user);
       
    }  catch (FirestoreException e) {
    // Handle Firestore exception
    user.setStatus(e.getMessage());
} catch (IllegalArgumentException e) {
    // Handle invalid arguments exception
    user.setStatus(e.getMessage());
     
    // ...
}  catch (Exception e) {
    // Handle generic exception
    user.setStatus(e.getMessage());
    
}
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
        CollectionReference collectionRef = firestore.collection("transactions").document("postransactions").collection("posid-KHG-hj");
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
