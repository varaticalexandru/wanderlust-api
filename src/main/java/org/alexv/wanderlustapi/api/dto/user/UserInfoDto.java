package org.alexv.wanderlustapi.api.dto.user;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.alexv.wanderlustapi.api.dto.itinerary.ItineraryDto;
import org.alexv.wanderlustapi.model.persistence.entity.Itinerary;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserInfoDto {

    String firstName;
    String lastName;
    String email;
    String password;
}
