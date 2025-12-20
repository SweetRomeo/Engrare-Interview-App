package org.example.engrareinterviewtest1.service;

import dev.langchain4j.data.message.UserMessage;
import org.example.engrareinterviewtest1.model.Quiz;

public interface QuizAiClient {
    Quiz generateQuiz(UserMessage userMessage);
}