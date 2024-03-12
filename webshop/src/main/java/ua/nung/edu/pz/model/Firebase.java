package ua.nung.edu.pz.model;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Firebase {
    private String firebaseConfigPath;
    private String firebaseName;
    private static Firebase firebase = new Firebase();
    private Firebase() {
    }

    public static Firebase getInstance() {
        return firebase;
    }

    public void init() {
        FileInputStream refreshToken = null;
        try {
            refreshToken = new FileInputStream(firebaseConfigPath);

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(refreshToken))
                    .setDatabaseUrl(firebaseName)
                    .build();
            if(FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setFirebaseConfigPath(String firebaseConfigPath) {
        this.firebaseConfigPath = firebaseConfigPath;
    }

    public void setFirebaseName(String firebaseName) {
        this.firebaseName = firebaseName;
    }
}
