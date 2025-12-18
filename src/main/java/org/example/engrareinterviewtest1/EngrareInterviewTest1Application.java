package org.example.engrareinterviewtest1;

import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.data.message.VideoContent;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class EngrareInterviewTest1Application {

    public static void main(String[] args) {
        SpringApplication.run(EngrareInterviewTest1Application.class, args);
    }

    // --- 1. Veri Modelleri (Records) ---
    public record QuizQuestion(String question, List<String> options, String correctAnswer) {}
    public record Quiz(List<QuizQuestion> questions) {}

    // --- 2. AI Servis Arayüzü ---
    // LangChain4j bu arayüzü çalışma zamanında implemente edecek.
    public interface QuizGeneratorService {
        Quiz generateQuiz(UserMessage userMessage);
    }

    // --- 3. Spring Bean Konfigürasyonları ---

    @Bean
    // application.properties dosyasından API key'i okur
    public ChatLanguageModel geminiModel() {
        String myApiKey = "AIzaSyD6I9N43YhJdQ9HCrXtQjeU0Jg_04fN09w";

        return GoogleAiGeminiChatModel.builder()
                .apiKey(myApiKey)
                .modelName("gemini-1.5-flash-001")
                .logRequestsAndResponses(true)
                .timeout(java.time.Duration.ofMinutes(2))
                .build();
    }

    @Bean
    // AI Servisini Spring Context'ine enjekte eder
    public QuizGeneratorService quizGeneratorService(ChatLanguageModel model) {
        return AiServices.create(QuizGeneratorService.class, model);
    }

    // --- 4. Uygulama Çalışınca Ne Yapılacak? (Runner) ---

    @Bean
    public CommandLineRunner run(QuizGeneratorService quizService) {
        return args -> {
            System.out.println("--- Spring Boot Uygulaması Başladı ---");

            // NOT: Buraya geçerli bir video URL'si veya yerel dosya yolu (file:/...) yazmalısın.
            // Örnek Google Cloud Storage URL'si veya herkese açık bir MP4 linki.
            String videoUrl = "https://storage.googleapis.com/generativeai-downloads/images/GreatRedSpot.mp4";

            System.out.println("Video analiz ediliyor: " + videoUrl);

            UserMessage userMessage = UserMessage.from(
                    TextContent.from("Bu videoyu analiz et ve Türkçe olarak 3 adet çoktan seçmeli soru hazırla."),
                    VideoContent.from(videoUrl)
            );

            try {
                Quiz quiz = quizService.generateQuiz(userMessage);

                System.out.println("\n--- OLUŞTURULAN QUİZ ---");
                for (QuizQuestion q : quiz.questions()) {
                    System.out.println("Soru: " + q.question());
                    System.out.println("Şıklar: " + q.options());
                    System.out.println("Doğru Cevap: " + q.correctAnswer());
                    System.out.println("-------------------------");
                }

            } catch (Exception e) {
                System.err.println("Hata oluştu: " + e.getMessage());
                e.printStackTrace();
            }
        };
    }
}