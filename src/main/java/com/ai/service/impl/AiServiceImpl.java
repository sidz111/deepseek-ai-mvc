package com.ai.service.impl;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import com.ai.service.AiService;
import reactor.core.publisher.Flux;

@Service
public class AiServiceImpl implements AiService {

    private final ChatClient chatClient;

    public AiServiceImpl(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @Override
    public Flux<String> askMe(String question) {
        return chatClient.prompt().user(question).stream().content();
    }
}
