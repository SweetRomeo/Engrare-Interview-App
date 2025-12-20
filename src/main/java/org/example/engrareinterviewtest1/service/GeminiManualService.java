package org.example.engrareinterviewtest1.service;

import org.example.engrareinterviewtest1.model.Quiz;
import org.example.engrareinterviewtest1.model.QuizQuestion;
import org.example.engrareinterviewtest1.model.QuizRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GeminiManualService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final RestClient restClient;
    private final ObjectMapper objectMapper;

    public GeminiManualService(RestClient.Builder builder, ObjectMapper objectMapper) {
        // HTTP/1.1'e zorlamak zorunda değiliz, Spring'in Client'ı daha stabildir.
        // Ama timeout süresini burada devasa veriyoruz.
        this.restClient = builder
                .baseUrl("https://generativelanguage.googleapis.com/v1beta")
                .requestFactory(new org.springframework.http.client.SimpleClientHttpRequestFactory() {{
                    setConnectTimeout(60000); // 1 dakika bağlanma süresi
                    setReadTimeout(600000);   // 10 dakika cevap bekleme süresi (Video için şart)
                }})
                .build();
        this.objectMapper = objectMapper;
    }

    public Quiz generateQuizManually(QuizRequest request) {
        try {
            // 1. Prompt Hazırla
            String prompt = String.format(
                    "Bu videoyu izle. %s dilinde, %d adet çoktan seçmeli soru hazırla. " +
                            "Cevabı SADECE şu JSON formatında ver, başka hiçbir yazı yazma: " +
                            "{ \"questions\": [ { \"question\": \"...\", \"options\": [\"a\", \"b\", \"c\", \"d\"], \"correctAnswer\": \"...\" } ] }",
                    request.language(), request.questionCount()
            );

            // 2. İstek Gövdesini (JSON) Oluştur
            Map<String, Object> requestBody = Map.of(
                    "contents", List.of(
                            Map.of("parts", List.of(
                                    Map.of("text", prompt),
                                    Map.of("file_data", Map.of(
                                            "mime_type", "video/mp4",
                                            "file_uri", request.videoUrl()
                                    ))
                            ))
                    )
            );

            // 3. Modeli Seç (Senin listende olan 2.5 modeli)
            String modelName = "gemini-2.5-flash";

            // 4. İsteği Gönder (POST)
            String response = restClient.post()
                    .uri("/models/" + modelName + ":generateContent?key=" + apiKey)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(requestBody)
                    .retrieve()
                    .body(String.class);

            // 5. Gelen Karışık JSON'ı Temizle ve Parse Et
            return parseGeminiResponse(response);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Gemini bağlantı hatası: " + e.getMessage());
        }
    }

    private Quiz parseGeminiResponse(String rawResponse) {
        try {
            JsonNode root = objectMapper.readTree(rawResponse);
            // Gemini cevabı "candidates -> content -> parts -> text" içindedir
            String jsonText = root.path("candidates").get(0)
                    .path("content").path("parts").get(0)
                    .path("text").asText();

            // Bazen Markdown ```json ... ``` blokları içinde verir, onları temizleyelim
            jsonText = jsonText.replace("```json", "").replace("```", "").trim();

            // Temizlenen metni Quiz nesnesine çevir
            return objectMapper.readValue(jsonText, Quiz.class);

        } catch (Exception e) {
            throw new RuntimeException("Gemini cevabı okunamadı: " + e.getMessage());
        }
    }
}