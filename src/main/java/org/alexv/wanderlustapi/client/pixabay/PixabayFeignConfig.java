package org.alexv.wanderlustapi.client.pixabay;

import feign.RequestInterceptor;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.alexv.wanderlustapi.client.DefaultFeignConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PixabayFeignConfig extends DefaultFeignConfig {

    @Value("${pixabay.api.key}")
    String apiKey;

    @Bean
    public RequestInterceptor pixabayRequestInterceptor() {
        return requestTemplate -> requestTemplate.query("key", apiKey);
    }
}
