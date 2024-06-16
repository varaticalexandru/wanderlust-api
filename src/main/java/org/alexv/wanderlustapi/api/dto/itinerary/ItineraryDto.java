package org.alexv.wanderlustapi.api.dto.itinerary;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.alexv.wanderlustapi.model.persistence.entity.DailySchedule;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ItineraryDto {

    String id;

    String placeId;
    String name;
    String cityName;
    String countryName;
    Double latitude;
    Double longitude;
    Integer tripLength;
    String startDate;
    String endDate;
    Integer dailyRecommendationsNumber;
    String budget;
    String companion;
    Boolean children;
    Boolean pets;
    String summary;
    List<DailySchedule> schedule;
}
