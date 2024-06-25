package org.alexv.wanderlustapi.api.controller.itinerary;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.alexv.wanderlustapi.api.dto.itinerary.ItinerariesDto;
import org.alexv.wanderlustapi.api.dto.itinerary.ItineraryDto;
import org.alexv.wanderlustapi.service.itinerary.impl.ItineraryServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/itineraries")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ItineraryController {

    ItineraryServiceImpl itineraryService;

    @GetMapping
    public ItinerariesDto getAllItineraries() {

        return itineraryService.getAllItineraries();
    }

    @GetMapping("/{id}")
    public ItineraryDto getItineraryById(
            @PathVariable String id) {

        return itineraryService.getItineraryById(id);
    }

    @PostMapping("/user/{userId}")
    public ItineraryDto createItinerary(
            @RequestBody ItineraryDto itineraryDto,
            @PathVariable("userId") String userId) {


        return itineraryService.saveItinerary(userId, itineraryDto);
    }

    @GetMapping("/user/{userId}")
    public ItinerariesDto getItineraries(
            @PathVariable("userId") String userId) {

        return itineraryService.getItinerariesByUserId(userId);
    }


    @DeleteMapping("/{id}")
    public Boolean deleteItinerary(
            @PathVariable String id) {

        return itineraryService.deleteItinerary(id);
    }
}
