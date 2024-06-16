package org.alexv.wanderlustapi.api.dto.itinerary;

import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class PlaceDto {
    String name;
    String description;
    String id;
    LocationDto location;
    String content;
}
