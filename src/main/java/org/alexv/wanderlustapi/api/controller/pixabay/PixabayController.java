package org.alexv.wanderlustapi.api.controller.pixabay;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.alexv.wanderlustapi.api.dto.pixabay.MediasDto;
import org.alexv.wanderlustapi.client.pixabay.MediaClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pixabay")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PixabayController {

    MediaClient mediaClient;

    public static final String FETCH_MEDIAS = "/media";

    @GetMapping(FETCH_MEDIAS)
    public MediasDto fetchMedias(
            @RequestParam("q") String query,
            @RequestParam("image_type") String imageType,
            @RequestParam("orientation") String orientation,
            @RequestParam("category") String category,
            @RequestParam("per_page") Integer perPage
    ) {
        return mediaClient.getMedias(
                query,
                imageType,
                orientation,
                category,
                perPage
        );
    }
}
