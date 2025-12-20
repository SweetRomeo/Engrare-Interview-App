package org.example.engrareinterviewtest1.service;

import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.data.message.VideoContent;
import org.example.engrareinterviewtest1.model.Quiz;
import org.example.engrareinterviewtest1.model.QuizRequest;
import org.springframework.stereotype.Service;

@Service
public class QuizService {

    private final QuizAiClient quizAiClient;

    // Config dosyasında oluşturduğumuz Client buraya otomatik gelir
    public QuizService(QuizAiClient quizAiClient) {
        this.quizAiClient = quizAiClient;
    }

    public Quiz processQuizGeneration(QuizRequest request) {
        // 1. Mantık: Varsayılan değerleri kontrol et
        String lang = (request.language() != null) ? request.language() : "Türkçe";
        int count = (request.questionCount() > 0) ? request.questionCount() : 5;

        // 2. Mantık: Prompt'u hazırla
        String promptText = String.format(
                "Bu videoyu analiz et ve %s dilinde, üniversite seviyesinde %d adet çoktan seçmeli soru hazırla.",
                lang, count
        );

        UserMessage userMessage = UserMessage.from(
                TextContent.from(promptText),
                VideoContent.from(request.videoUrl())
        );

        // 3. Mantık: Yapay zekayı çağır
        return quizAiClient.generateQuiz(userMessage);
    }
}
