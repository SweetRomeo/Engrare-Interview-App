package org.example.engrareinterviewtest1.controller;

import org.example.engrareinterviewtest1.model.Quiz;
import org.example.engrareinterviewtest1.model.QuizRequest;
import org.example.engrareinterviewtest1.service.QuizService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quiz")
@CrossOrigin(origins = "*") // Frontend erişimi için
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("/generate")
    public Quiz generateQuiz(@RequestBody QuizRequest request) {
        // Controller'ın işi sadece trafiği yönlendirmektir.
        // İş mantığını Service katmanına devreder.
        return quizService.processQuizGeneration(request);
    }
}