package org.alexv.wanderlustapi.client.pixabay;

import org.alexv.wanderlustapi.api.dto.pixabay.MediasDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "pixabay-media", configuration = PixabayFeignConfig.class, url = "${pixabay-api.urls.media}")
public interface MediaClient {
    @GetMapping()
    MediasDto getMedias(
            @RequestParam("q") String query,
            @RequestParam("image_type") String imageType,
            @RequestParam("orientation") String orientation,
            @RequestParam("category") String category,
            @RequestParam("per_page") Integer perPage
    );
}
