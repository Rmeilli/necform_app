package org.sid.necform.ai.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HuggingFaceRequest {
    private String inputs;
    private Map<String, Object> parameters;

    public HuggingFaceRequest() {
    }

    public HuggingFaceRequest(String inputs, Map<String, Object> parameters) {
        this.inputs = inputs;
        this.parameters = parameters;
    }

    public String getInputs() {
        return inputs;
    }

    public void setInputs(String inputs) {
        this.inputs = inputs;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }
}
