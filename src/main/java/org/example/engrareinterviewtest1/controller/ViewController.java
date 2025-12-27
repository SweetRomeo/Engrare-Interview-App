package org.example.engrareinterviewtest1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String showQuizPage() {
        return "index"; // templates/index.html'i bulup çalıştırır
    }
}