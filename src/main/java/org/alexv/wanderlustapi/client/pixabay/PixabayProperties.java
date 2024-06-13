package org.alexv.wanderlustapi.client.pixabay;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "pixabay-api")
public class PixabayProperties {

    private String key;
    private Map<String, String> urls;
}
