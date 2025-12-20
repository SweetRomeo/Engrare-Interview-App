package org.example.engrareinterviewtest1.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.service.AiServices;
import org.example.engrareinterviewtest1.service.QuizAiClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration; // Bu importun olduğundan emin ol

@Configuration
public class GeminiConfiguration {

    @Value("${gemini.api.key}")
    private String myApiKey;

    @Bean
    public ChatLanguageModel geminiModel() {
        return GoogleAiGeminiChatModel.builder()
                .apiKey(myApiKey)
                .modelName("gemini-2.5-flash")
                .logRequestsAndResponses(true)
                // HATA BURADAYDI: Süre yetmiyordu.
                // Video analizi için süreyi 10 dakikaya çıkarıyoruz.
                .timeout(Duration.ofMinutes(10))
                .build();
    }

    @Bean
    public QuizAiClient quizAiClient(ChatLanguageModel model) {
        return AiServices.create(QuizAiClient.class, model);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}