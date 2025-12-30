package org.example.engrareinterviewtest1.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfiguration {

    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        // Eğer Firebase daha önce başlatıldıysa, eskisini döndür
        if (!FirebaseApp.getApps().isEmpty()) {
            return FirebaseApp.getInstance();
        }

        InputStream serviceAccount;

        // 1. ÖNCE RENDER ORTAMINI KONTROL ET
        // Render secret dosyalarını genellikle bu yola koyar.
        File renderFile = new File("/etc/secrets/firebase-service-account.json");

        if (renderFile.exists()) {
            System.out.println("Render ortamı algılandı, dosya /etc/secrets/ altından okunuyor...");
            serviceAccount = new FileInputStream(renderFile);
        }
        // 2. DOSYA ORADA YOKSA LOCAL (BİLGİSAYARIN) ORTAMINI KONTROL ET
        else {
            System.out.println("Local ortam algılandı, dosya resources altından okunuyor...");
            serviceAccount = getClass()
                    .getClassLoader()
                    .getResourceAsStream("firebase-service-account.json");
        }

        // Dosya iki yerde de yoksa hata fırlat
        if (serviceAccount == null) {
            throw new IOException("firebase-service-account.json dosyası ne /etc/secrets altında ne de resources altında bulunamadı!");
        }

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        return FirebaseApp.initializeApp(options);
    }
}