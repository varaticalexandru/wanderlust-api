package org.alexv.wanderlustapi.client.openai;

import com.theokanning.openai.function.FunctionExecutorManager;
import com.theokanning.openai.service.OpenAiService;
import feign.Feign;
import feign.RequestInterceptor;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.alexv.wanderlustapi.client.DefaultFeignConfig;
import org.alexv.wanderlustapi.service.openai.OpenaiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequiredArgsConstructor
public class OpenaiFeignConfig extends DefaultFeignConfig {

    OpenaiProperties openaiProperties;

    @Bean
    public FunctionExecutorManager functionExecutorManager() {
        return new FunctionExecutorManager();
    }

    @Bean
    public OpenAiService openAiService() {
        return new OpenAiService(openaiProperties.getKey());
    }

    @Bean
    public RequestInterceptor openaiRequestInterceptor() {

        Map<String, String> headers = openaiProperties.getHeaders();

        if (!headers.containsKey("Authorization") || !headers.containsKey("OpenAI-Beta")) {
            throw new RuntimeException("Missing headers");
        }

        String authToken = headers.get("Authorization") + " " + openaiProperties.getKey();
        String betaHaderVal = headers.get("OpenAI-Beta");

        return requestTemplate -> {
            requestTemplate.header("Authorization", authToken);
            requestTemplate.header("OpenAI-Beta", betaHaderVal);
        };
    }
}
