
package com.sahidDev.managerSystem;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import jakarta.annotation.PostConstruct;

import java.io.FileInputStream;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;

@Service
public class FBInitializer {

    Logger logger = LoggerFactory.getLogger(FBInitializer.class);

    @PostConstruct
    public void initialize() {
        try {
            FileInputStream serviceAccount = new FileInputStream("./serviceAccountKey.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl(System.getenv("DATABASE_URL"))
                    .build();

            FirebaseApp.initializeApp(options);
            logger.info("Firebase application has been initialized");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
