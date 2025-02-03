package com.ai.service;

import reactor.core.publisher.Flux;

public interface AiService {
    Flux<String> askMe(String question);
}
