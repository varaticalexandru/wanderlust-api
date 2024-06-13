package org.alexv.wanderlustapi.client.openai;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "openai-api")
public class OpenaiProperties {

    private String key;
    private Map<String, String> headers;
    private String assistantId;
    private String threadId;
    private Map<String, String> urls;
    private String model;
    private Map<String, String> responseFormat;
    private String systemMessage;
    private Map<String, Object> parameters;
}
