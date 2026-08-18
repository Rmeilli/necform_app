package org.sid.necform.ai.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class HuggingFaceConfig {

    @Value("${ai.huggingface.api-key}")
    private String apiKey;

    @Value("${ai.huggingface.base-url}")
    private String baseUrl;

    @Value("${ai.huggingface.model}")
    private String model;

    @Value("${ai.huggingface.timeout}")
    private String timeout;

    @Bean
    public WebClient huggingFaceWebClient() {
        return WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .build();
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getModel() {
        return model;
    }

    public String getTimeout() {
        return timeout;
    }
}
