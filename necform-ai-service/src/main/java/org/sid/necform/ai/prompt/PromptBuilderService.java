package org.sid.necform.ai.prompt;

import org.springframework.stereotype.Service;

@Service
public class PromptBuilderService {

    private static final String SYSTEM_PROMPT = """
        You are the official AI assistant of Necform.
        
        Necform is a professional training organization.
        
        You must ONLY answer questions related to Necform.
        
        If the information is unavailable, say you do not know.
        
        Never invent formations.
        
        Never invent prices.
        
        Never invent durations.
        
        Always answer in French.
        """;

    public String buildPrompt(String question, String context) {
        StringBuilder prompt = new StringBuilder(SYSTEM_PROMPT);
        if (context != null && !context.isBlank()) {
            prompt.append("\n\nContext:\n").append(context);
        }
        prompt.append("\n\nUser question: ").append(question);
        return prompt.toString();
    }

    public String buildChatPrompt(String userMessage) {
        return buildPrompt(userMessage, null);
    }

    public String buildRecommendationPrompt(String goal) {
        return SYSTEM_PROMPT + "\n\nUser goal: " + goal + 
               "\n\nProvide training recommendations based on this goal.";
    }

    public String buildSummaryPrompt(String formationDetails) {
        return SYSTEM_PROMPT + "\n\nFormation details: " + formationDetails + 
               "\n\nProvide a summary of this formation.";
    }

    public String buildComparisonPrompt(String formationA, String formationB) {
        return SYSTEM_PROMPT + "\n\nFormation A: " + formationA + 
               "\nFormation B: " + formationB + 
               "\n\nCompare these two formations.";
    }

    public String buildPrerequisitesPrompt(String formation) {
        return SYSTEM_PROMPT + "\n\nFormation: " + formation + 
               "\n\nWhat are the prerequisites for this formation?";
    }
}
