package org.example.engrareinterviewtest1.config;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.service.AiServices;
import org.example.engrareinterviewtest1.service.QuizAiClient; // Birazdan oluşturacağız
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class GeminiConfiguration {

    @Value("${gemini.api.key}")
    private String myApiKey;

    @Bean
    public ChatLanguageModel geminiModel() {

        return GoogleAiGeminiChatModel.builder()
                .apiKey(myApiKey)
                // DEĞİŞİKLİK BURADA:
                // Listede gördüğün "models/" kısmını atıp sadece ismini yazıyoruz.
                .modelName("gemini-2.5-flash")
                .logRequestsAndResponses(true)
                .timeout(java.time.Duration.ofMinutes(2))
                .build();
    }

    @Bean
    public QuizAiClient quizAiClient(ChatLanguageModel model) {
        // LangChain4j'in sihirli değneği: Interface'i alıp çalışan bir servise dönüştürür
        return AiServices.create(QuizAiClient.class, model);
    }
}
