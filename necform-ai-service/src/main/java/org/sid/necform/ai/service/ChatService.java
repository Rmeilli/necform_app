package org.sid.necform.ai.service;

import org.sid.necform.ai.client.HuggingFaceClient;
import org.sid.necform.ai.prompt.PromptBuilderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ChatService {

    private static final Logger logger = LoggerFactory.getLogger(ChatService.class);

    private final HuggingFaceClient huggingFaceClient;
    private final PromptBuilderService promptBuilderService;

    public ChatService(HuggingFaceClient huggingFaceClient, 
                       PromptBuilderService promptBuilderService) {
        this.huggingFaceClient = huggingFaceClient;
        this.promptBuilderService = promptBuilderService;
    }

    public Mono<String> chat(String userMessage) {
        logger.info("Processing chat request: {}", userMessage);
        
        String prompt = promptBuilderService.buildChatPrompt(userMessage);
        
        long startTime = System.currentTimeMillis();
        return huggingFaceClient.generateContent(prompt)
                .doOnNext(response -> {
                    long duration = System.currentTimeMillis() - startTime;
                    logger.info("Hugging Face response received in {}ms", duration);
                })
                .doOnError(error -> {
                    logger.error("Error calling Hugging Face API", error);
                });
    }
}
