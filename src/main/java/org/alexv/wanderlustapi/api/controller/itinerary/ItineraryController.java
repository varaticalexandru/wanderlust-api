package org.alexv.wanderlustapi.api.controller.itinerary;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.alexv.wanderlustapi.api.dto.itinerary.ItinerariesDto;
import org.alexv.wanderlustapi.api.dto.itinerary.ItineraryDto;
import org.alexv.wanderlustapi.service.itinerary.ItineraryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/itineraries")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ItineraryController {

    ItineraryService itineraryService;

    @GetMapping
    public ItinerariesDto getAllItineraries() {

        return itineraryService.getAllItineraries();
    }

    @GetMapping("/{id}")
    public ItineraryDto getItineraryById(
            @PathVariable String id) {

        return itineraryService.getItineraryById(id);
    }

    @PostMapping
    public ItineraryDto createItinerary(
            @RequestBody ItineraryDto itineraryDto) {

        return itineraryService.saveItinerary(itineraryDto);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteItinerary(
            @PathVariable String id) {

        return itineraryService.deleteItinerary(id);
    }
}
