package org.alexv.wanderlustapi.api.dto.pixabay;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HitDto {
    Integer id;
    String pageURL;
    String type;
    String tags;
    String previewURL;
    Integer previewWidth;
    Integer previewHeight;
    String webformatURL;
    Integer webformatWidth;
    Integer webformatHeight;
    String largeImageURL;
    Integer imageWidth;
    Integer imageHeight;
    Integer imageSize;
    Integer views;
    Integer downloads;
    Integer collections;
    Integer likes;
    Integer comments;
    Long user_id;
    String user;
    String userImageURL;
}
