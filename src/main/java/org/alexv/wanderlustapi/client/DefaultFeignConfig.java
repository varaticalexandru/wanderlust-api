package org.alexv.wanderlustapi.client;

import feign.Logger;
import feign.okhttp.OkHttpClient;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class DefaultFeignConfig {

    @Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
