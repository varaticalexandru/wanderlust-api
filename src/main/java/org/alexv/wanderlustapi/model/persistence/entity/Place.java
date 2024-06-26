package org.alexv.wanderlustapi.model.persistence.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Place {
    String id;
    String name;
    String description;
    Location location;
    Object content;
}
