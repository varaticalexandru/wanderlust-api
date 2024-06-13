package org.alexv.wanderlustapi.api.dto.openai;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OpenaiResponseDto {

    String id;
    String object;
    Long created;
    String model;
    List<ChoiceDto> choices;
    Map<String, Double> usage;

    @JsonProperty("system_fingerprint")
    String systemFingerprint;
}
