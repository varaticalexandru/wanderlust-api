package org.alexv.wanderlustapi.client.pixabay;

import feign.RequestInterceptor;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.alexv.wanderlustapi.client.DefaultFeignConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PixabayFeignConfig extends DefaultFeignConfig {

    final PixabayProperties pixabayProperties;

    @Bean
    public RequestInterceptor pixabayRequestInterceptor() {
        String key = pixabayProperties.getKey();
        return requestTemplate -> requestTemplate.query("key", key);
    }
}
