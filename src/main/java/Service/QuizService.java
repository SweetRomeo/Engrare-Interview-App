package Service;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.VideoContent;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.model.output.Response;

import java.util.List;

// Ana Sınıf (Dosya adıyla aynı olmalı, örn: QuizService)
public class QuizService {

    // 1. Record Tanımları (Java 17'de bunları sınıfın içine 'static' olarak veya sınıfın dışına koyabilirsin)
    // En temiz yöntem sınıfın içinde static olarak tanımlamaktır.
    public record QuizQuestion(String question, List<String> options, String correctAnswer) {}
    public record Quiz(List<QuizQuestion> questions) {}

    // Yapay Zeka Servis Arayüzü (LangChain4j için gerekli)
    interface QuizAiService {
        Quiz generateQuiz(UserMessage userMessage);
    }

    public static void run() {

        String apiKey = "BURAYA_GOOGLE_API_KEY_GELECEK";
        String videoUrl = "https://path-to-your-video/video.mp4"; // Veya yerel dosya path'i

        // 2. Modeli Hazırla
        ChatLanguageModel model = GoogleAiGeminiChatModel.builder()
                .apiKey(apiKey)
                .modelName("gemini-1.5-flash")
                .logRequestsAndResponses(true) // Hata ayıklamak için logları açtık
                .build();

        // 3. AI Servisini Oluştur
        QuizAiService quizService = AiServices.create(QuizAiService.class, model);

        // 4. Prompt ve Video İçeriğini Hazırla
        UserMessage userMessage = UserMessage.from(
                TextContent.from("Bu videoyu analiz et ve üniversite seviyesinde, 5 soruluk, 4 şıklı bir quiz oluştur."),
                VideoContent.from(videoUrl)
        );

        // 5. Servisi Çağır ve Sonucu Al
        try {
            System.out.println("Video analiz ediliyor, lütfen bekleyin...");
            Quiz quiz = quizService.generateQuiz(userMessage);

            // Sonuçları Ekrana Yazdır
            quiz.questions().forEach(q -> {
                System.out.println("Soru: " + q.question());
                System.out.println("Şıklar: " + q.options());
                System.out.println("Cevap: " + q.correctAnswer());
                System.out.println("-----------------------------");
            });

        } catch (Exception e) {
            System.err.println("Bir hata oluştu: " + e.getMessage());
        }
    }
}
