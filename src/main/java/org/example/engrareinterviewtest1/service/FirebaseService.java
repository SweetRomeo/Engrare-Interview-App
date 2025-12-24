package org.example.engrareinterviewtest1.service;

import com.fasterxml.jackson.core.type.TypeReference; // <-- Bunu ekle
import com.fasterxml.jackson.databind.ObjectMapper;   // <-- Bunu ekle
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.example.engrareinterviewtest1.model.Quiz;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class FirebaseService {

    // Jackson ObjectMapper'ı çağırıyoruz (Dönüştürücü)
    private final ObjectMapper objectMapper;

    public FirebaseService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String saveQuiz(Quiz quiz, String videoUrl) {
        try {
            Firestore db = FirestoreClient.getFirestore();

            // 1. Quiz record'unu Jackson kullanarak Map'e çeviriyoruz.
            // Bu sayede "get" metodu sorunu ortadan kalkıyor.
            Map<String, Object> quizMap = objectMapper.convertValue(quiz, new TypeReference<Map<String, Object>>() {});

            // 2. Eklemek istediğimiz diğer verileri bu Map'e ekliyoruz
            // (Not: quizMap değiştirilebilir olmalı, o yüzden yeni bir HashMap içine alabiliriz veya direkt ekleriz)
            Map<String, Object> finalData = new HashMap<>(quizMap);

            finalData.put("id", UUID.randomUUID().toString());
            finalData.put("videoUrl", videoUrl);
            finalData.put("createdAt", System.currentTimeMillis());

            // Not: 'quizMap' içinden gelen 'questions' alanı zaten otomatik eklendi.

            // 3. Veritabanına kaydet
            ApiFuture<DocumentReference> addedDocRef = db.collection("quizzes").add(finalData);

            return addedDocRef.get().getId();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Firebase'e kayıt başarısız: " + e.getMessage());
        }
    }
}