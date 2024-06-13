package org.alexv.wanderlustapi.service.openai;

import org.alexv.wanderlustapi.api.dto.openai.RecommendationsRequestDto;
import org.alexv.wanderlustapi.api.dto.openai.RecommendationsResponseDto;

public interface OpenaiService {
    RecommendationsResponseDto getRecommendations(RecommendationsRequestDto request);
    RecommendationsResponseDto recommendationsAssistantCall(RecommendationsRequestDto request);
}
