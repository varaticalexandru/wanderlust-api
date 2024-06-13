package org.alexv.wanderlustapi.api.dto.openai;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChoiceDto {

    Integer index;
    MessageDto message;

    @JsonProperty("logprobs")
    String logProbs;

    @JsonProperty("finish_reason")
    String finishReason;
}
