package org.alexv.wanderlustapi.model.persistence.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.alexv.wanderlustapi.api.dto.openai.DailyScheduleDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("itineraries")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Itinerary {

    @Id
    String id;

    String placeId;
    String name;

    @Indexed
    String cityName;

    String countryName;
    Double latitude;
    Double longitude;
    Integer tripLength;
    String startDate;
    String endDate;
    Integer dailyRecommendationsNumber;
    String summary;
    String budget;
    String companion;
    Boolean children;
    Boolean pets;
    List<DailySchedule> schedule;

    @DBRef
    UserInfo user;

    public Itinerary(String placeId, String name, String cityName, String countryName, Double latitude, Double longitude, Integer tripLength, String startDate, String endDate, Integer dailyRecommendationsNumber, String summary, String budget, String companion, Boolean children, Boolean pets, List<DailySchedule> schedule) {
        this.placeId = placeId;
        this.name = name;
        this.cityName = cityName;
        this.countryName = countryName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.tripLength = tripLength;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dailyRecommendationsNumber = dailyRecommendationsNumber;
        this.summary = summary;
        this.budget = budget;
        this.companion = companion;
        this.children = children;
        this.pets = pets;
        this.schedule = schedule;
    }
}
