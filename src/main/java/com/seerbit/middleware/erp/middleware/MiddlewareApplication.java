package com.seerbit.middleware.erp.middleware;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.seerbit.middleware.erp.middleware.model.FirestorePOSTransactionRepository;
import com.seerbit.middleware.erp.middleware.model.POSTransactionRepository;
import com.seerbit.services.UserService;

@SpringBootApplication
public class MiddlewareApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiddlewareApplication.class, args);
	}

	@Bean
    public Firestore firestore() throws IOException {
        InputStream serviceAccount = getClass().getResourceAsStream("/serviceAccountKey.json");
    FirestoreOptions firestoreOptions = FirestoreOptions.newBuilder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .setProjectId("seerbitpos")
            .build();
    return firestoreOptions.getService();
    }

    @Bean
    public POSTransactionRepository posTransactionRepository(Firestore firestore) {
        return new FirestorePOSTransactionRepository(firestore);
    }

    @Bean
    public UserService userService(Firestore firestore) {
        return new UserService(firestore);
    }

}
