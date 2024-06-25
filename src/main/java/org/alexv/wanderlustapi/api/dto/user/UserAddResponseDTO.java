package org.alexv.wanderlustapi.api.dto.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAddResponseDTO {

    String id;
    String email;
}
