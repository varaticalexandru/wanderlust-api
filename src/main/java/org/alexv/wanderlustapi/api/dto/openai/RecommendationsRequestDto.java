package org.alexv.wanderlustapi.api.dto.openai;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecommendationsRequestDto {

    DestinationDto destination;
    PeriodDto period;
    String companion;
    Boolean pets;
    Boolean children;
    String budget;
    List<String> interests;
    Integer dailyRecommendationsNumber;
}
