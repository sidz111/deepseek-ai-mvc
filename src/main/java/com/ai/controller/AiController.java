package com.ai.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.ai.service.AiService;
import reactor.core.publisher.Flux;

@Controller
public class AiController {

    private final AiService aiService;

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    // Serve the main home page
    @GetMapping("/")
    public String homePage() {
        return "index"; // Returns 'index.html'
    }

    // AI response streaming endpoint (preserving spaces)
    @GetMapping(value = "/ask", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> askMeAnything(@RequestParam(value = "query", required = false, defaultValue = "How are you?") String query) {
        return aiService.askMe(query)
                .map(response -> response.replace(" ", " ")); // Use non-breaking spaces to preserve formatting
    }
}
