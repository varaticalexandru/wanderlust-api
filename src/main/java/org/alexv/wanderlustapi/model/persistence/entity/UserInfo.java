package org.alexv.wanderlustapi.model.persistence.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserInfo {

    @Id
    String id;

    String firstName;
    String lastName;

    @Indexed(unique = true)
    String email;
    String password;
    String roles;

    @DBRef
    List<Itinerary> itineraries = new ArrayList<>();
}
