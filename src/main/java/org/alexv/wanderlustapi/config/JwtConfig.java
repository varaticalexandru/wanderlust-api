package org.alexv.wanderlustapi.config;

import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class JwtConfig {

    String secret;
    Long expiration;
}
