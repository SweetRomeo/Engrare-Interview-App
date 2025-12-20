package org.example.engrareinterviewtest1.controller;

import org.example.engrareinterviewtest1.model.Quiz;
import org.example.engrareinterviewtest1.model.QuizRequest;
// DİKKAT: Yeni servisi import et
import org.example.engrareinterviewtest1.service.GeminiManualService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quiz")
@CrossOrigin(origins = "*")
public class QuizController {

    // Eski servis yerine yenisini kullanıyoruz
    private final GeminiManualService geminiService;

    public QuizController(GeminiManualService geminiService) {
        this.geminiService = geminiService;
    }

    @PostMapping("/generate")
    public ResponseEntity<?> generateQuiz(@RequestBody QuizRequest request) {
        try {
            System.out.println("MANUEL İstek alındı. URL: " + request.videoUrl());

            // Yeni metodumuzu çağırıyoruz
            Quiz result = geminiService.generateQuizManually(request);

            return ResponseEntity.ok(result);

        } catch (Exception e) {
            System.err.println("!!! HATA OLUŞTU !!!");
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body("Sunucu Hatası: " + e.getMessage());
        }
    }
}