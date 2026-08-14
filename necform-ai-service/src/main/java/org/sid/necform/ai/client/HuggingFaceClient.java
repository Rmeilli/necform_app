package org.sid.necform.ai.client;

import org.sid.necform.ai.config.HuggingFaceConfig;
import org.sid.necform.ai.dto.HuggingFaceRequest;
import org.sid.necform.ai.dto.HuggingFaceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Component
public class HuggingFaceClient {

    private static final Logger logger = LoggerFactory.getLogger(HuggingFaceClient.class);
    private final WebClient webClient;
    private final HuggingFaceConfig huggingFaceConfig;
    private final boolean useMock;

    public HuggingFaceClient(WebClient huggingFaceWebClient, HuggingFaceConfig huggingFaceConfig) {
        this.webClient = huggingFaceWebClient;
        this.huggingFaceConfig = huggingFaceConfig;
        // Use real API
        this.useMock = false;
    }

    public Mono<String> generateContent(String prompt) {
        if (useMock) {
            return generateMockResponse(prompt);
        }

        String systemPrompt = "You are the official AI assistant of Necform. Necform is a professional training organization. You must ONLY answer questions related to Necform. If the information is unavailable, say you do not know. Never invent formations. Never invent prices. Never invent durations. Always answer in French.";
        String fullPrompt = systemPrompt + "\n\nUser: " + prompt;

        Map<String, Object> parameters = Map.of(
                "max_new_tokens", 500,
                "temperature", 0.7,
                "return_full_text", false
        );

        HuggingFaceRequest request = new HuggingFaceRequest(fullPrompt, parameters);

        return webClient.post()
                .uri("/models/" + huggingFaceConfig.getModel())
                .bodyValue(request)
                .retrieve()
                .bodyToMono(String[].class)
                .doOnNext(response -> logger.debug("Hugging Face response received"))
                .map(response -> {
                    if (response != null && response.length > 0) {
                        return response[0];
                    }
                    return "No response from Hugging Face";
                })
                .onErrorResume(WebClientResponseException.class, e -> {
                    logger.error("Hugging Face API error: status={}, body={}", e.getStatusCode(), e.getResponseBodyAsString(), e);
                    return Mono.just("Error from Hugging Face API: " + e.getMessage());
                })
                .onErrorResume(Exception.class, e -> {
                    logger.error("Unexpected error calling Hugging Face API", e);
                    return Mono.just("Unexpected error: " + e.getMessage());
                });
    }

    private Mono<String> generateMockResponse(String prompt) {
        logger.info("Using mock response for prompt: {}", prompt);
        
        String mockResponse = "Je suis l'assistant IA de Necform. Necform est un organisme de formation professionnelle. " +
                "Je réponds uniquement aux questions liées à Necform. Si l'information n'est pas disponible, je vous le dirai. " +
                "Je n'invente jamais de formations, de prix ou de durées. Je réponds toujours en français.";
        
        return Mono.just(mockResponse);
    }
}
