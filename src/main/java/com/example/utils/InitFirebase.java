package com.example.utils;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import java.io.FileInputStream;
import java.io.IOException;

@WebServlet(loadOnStartup = 1)
public class InitFirebase extends HttpServlet {

    @Override
    public void init() throws ServletException {
        try {
            FileInputStream serviceAccount = new FileInputStream("/Users/olawale/Desktop/exchange/src/main/java/com/example/utils/exchange-a72f2-firebase-adminsdk-jk9ui-67e8bcc733.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setStorageBucket("exchange-a72f2.appspot.com")
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            throw new ServletException("Error initializing Firebase", e);
        }
    }
}

