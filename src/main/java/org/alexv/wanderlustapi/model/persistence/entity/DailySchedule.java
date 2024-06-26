package org.alexv.wanderlustapi.model.persistence.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.alexv.wanderlustapi.api.dto.openai.PlaceDto;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class DailySchedule {

    Integer day;
    String description;
    List<Place> recommendations;
    String color;
}
