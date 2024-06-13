package org.alexv.wanderlustapi.api.dto.openai;

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
}
