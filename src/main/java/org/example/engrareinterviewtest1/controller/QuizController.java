package org.example.engrareinterviewtest1.controller;

import org.example.engrareinterviewtest1.model.Quiz;
import org.example.engrareinterviewtest1.model.QuizRequest;
import org.example.engrareinterviewtest1.service.GeminiManualService;
import org.example.engrareinterviewtest1.service.FirebaseService; // Yeni servisi ekledik
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quiz")
@CrossOrigin(origins = "*")
public class QuizController {

    private final GeminiManualService geminiService;
    private final FirebaseService firebaseService; // Firebase servisini tanımla

    // Constructor Injection (İkisini de içeri alıyoruz)
    public QuizController(GeminiManualService geminiService, FirebaseService firebaseService) {
        this.geminiService = geminiService;
        this.firebaseService = firebaseService;
    }

    @PostMapping("/generate")
    public ResponseEntity<?> generateQuiz(@RequestBody QuizRequest request) {
        try {
            // 1. ADIM: Gemini'ye git, Quiz'i oluştur
            System.out.println("Gemini çalışıyor...");
            Quiz generatedQuiz = geminiService.generateQuizManually(request);

            // 2. ADIM: Oluşan Quiz'i Firebase'e kaydet
            System.out.println("Firebase'e kaydediliyor...");
            String firestoreId = firebaseService.saveQuiz(generatedQuiz, request.videoUrl());
            System.out.println("Kaydedildi! ID: " + firestoreId);

            // 3. ADIM: Sonucu kullanıcıya (veya Frontend'e) dön
            return ResponseEntity.ok(generatedQuiz);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body("Hata oluştu: " + e.getMessage());
        }
    }
}