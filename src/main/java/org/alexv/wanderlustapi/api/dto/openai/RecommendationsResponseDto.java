package org.alexv.wanderlustapi.api.dto.openai;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class RecommendationsResponseDto {

    String cityName;
    String countryName;
    Double latitude;
    Double longitude;
    Integer tripLength;
    String startDate;
    String endDate;
    String budget;
    String companion;
    String children;
    String pets;
    Integer dailyRecommendationsNumber;
    String summary;
    List<DailyScheduleDto> schedule;
}
