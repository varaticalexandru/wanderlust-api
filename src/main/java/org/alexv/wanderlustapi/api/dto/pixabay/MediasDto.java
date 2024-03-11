package org.alexv.wanderlustapi.api.dto.pixabay;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MediasDto {
    Integer total;
    Integer totalHits;
    List<HitDto> hits;
}
