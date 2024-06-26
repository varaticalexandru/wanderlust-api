package org.alexv.wanderlustapi.api.dto.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateDto {
    String id;
    String firstName;
    String lastName;
    String email;
    String currentPassword;
    String newPassword;
}
