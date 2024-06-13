package org.alexv.wanderlustapi.api.dto.openai;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OpenaiRequestDto {

    String model;

    @JsonProperty("response_format")
    Map<String, String> responseFormat;

    List<Map<String, String>> messages;
    Double temperature;

    @JsonProperty("max_tokens")
    Integer maxTokens;

    @JsonProperty("top_p")
    Double topP;

    @JsonProperty("frequency_penalty")
    Double frequencyPenalty;

    @JsonProperty("presence_penalty")
    Double presencePenalty;
}
