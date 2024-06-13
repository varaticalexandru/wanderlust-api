package org.alexv.wanderlustapi.client.openai;


import feign.Headers;
import feign.RequestLine;
import org.alexv.wanderlustapi.api.dto.openai.OpenaiRequestDto;
import org.alexv.wanderlustapi.api.dto.openai.OpenaiResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "openai-recommendations", url = "${openai-api.urls.completions}", configuration = OpenaiFeignConfig.class)
public interface RecommendationsClient {

    @PostMapping
    OpenaiResponseDto getRecommendations(OpenaiRequestDto request);
}
