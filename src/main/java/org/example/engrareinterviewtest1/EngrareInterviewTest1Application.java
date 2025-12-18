package org.example.engrareinterviewtest1;

import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.data.message.VideoContent;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.model.output.Response;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

// 1. Yapay Zekadan beklediğin çıktı formatını tanımla
record QuizQuestion(String question, List<String> options, String correctAnswer) {}
        record Quiz(List<QuizQuestion> questions) {}

        // 2. Modeli hazırla (Gemini 1.5 Flash video için hızlı ve ucuzdur)
        ChatLanguageModel model = GoogleAiGeminiChatModel.builder()
                .apiKey("SENIN_API_KEYIN")
                .modelName("gemini-1.5-flash")
                .build();

        // 3. Kullanıcıdan gelen videoyu al (File veya Base64)
        String videoUrl = "kullanicinin_yukledigi_video_path";

        // 4. Prompt hazırla
        UserMessage userMessage = UserMessage.from(
                TextContent.from("Bu videoyu analiz et ve üniversite seviyesinde 5 soruluk bir quiz oluştur."),
                VideoContent.from(videoUrl) // Video içeriğini doğrudan modele veriyoruz
        );

        // 5. Cevabı al ve Java nesnesine dönüştür (AIService kullanarak)
        Response<Quiz> response = quizService.generateQuiz(userMessage);

@SpringBootApplication
public class EngrareInterviewTest1Application {

    public static void main(String[] args) {
        SpringApplication.run(EngrareInterviewTest1Application.class, args);
    }

}

