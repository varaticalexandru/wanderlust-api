package org.alexv.wanderlustapi.api.controller.openai;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.alexv.wanderlustapi.api.dto.openai.RecommendationsRequestDto;
import org.alexv.wanderlustapi.api.dto.openai.RecommendationsResponseDto;
import org.alexv.wanderlustapi.client.openai.RecommendationsClient;
import org.alexv.wanderlustapi.service.openai.OpenaiService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recommendations")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class OpenaiController {

    RecommendationsClient recommendationsClient;
    OpenaiService openaiService;

    @PostMapping()
    public RecommendationsResponseDto getRecommendations(
            @RequestBody RecommendationsRequestDto request
    ) {

        return openaiService.getRecommendations(request);
    }
}
