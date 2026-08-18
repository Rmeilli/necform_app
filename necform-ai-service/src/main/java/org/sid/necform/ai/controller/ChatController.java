package org.sid.necform.ai.controller;

import org.sid.necform.ai.dto.ChatRequest;
import org.sid.necform.ai.dto.ChatResponse;
import org.sid.necform.ai.service.ChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/ai")
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/chat")
    public Mono<ResponseEntity<ChatResponse>> chat(@RequestBody ChatRequest request) {
        logger.info("Received chat request: {}", request.getMessage());
        
        return chatService.chat(request.getMessage())
                .map(response -> ResponseEntity.ok(new ChatResponse(response)))
                .onErrorResume(error -> {
                    logger.error("Error processing chat request", error);
                    return Mono.just(ResponseEntity.internalServerError()
                            .body(new ChatResponse("Error processing request: " + error.getMessage())));
                });
    }
}
