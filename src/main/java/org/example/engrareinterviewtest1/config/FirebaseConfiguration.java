package org.example.engrareinterviewtest1.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfiguration {

    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        // Eğer Firebase daha önce başlatıldıysa (restart durumlarında), eskisini kullan
        if (FirebaseApp.getApps().isEmpty()) {
            // Resources klasöründeki dosyayı oku
            InputStream serviceAccount = getClass()
                    .getClassLoader()
                    .getResourceAsStream("firebase-service-account.json");

            if (serviceAccount == null) {
                throw new IOException("firebase-service-account.json dosyası resources altında bulunamadı!");
            }

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            return FirebaseApp.initializeApp(options);
        }
        return FirebaseApp.getInstance();
    }
}