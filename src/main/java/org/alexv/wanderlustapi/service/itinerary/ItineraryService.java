package org.alexv.wanderlustapi.service.itinerary;

import org.alexv.wanderlustapi.api.dto.itinerary.ItinerariesDto;
import org.alexv.wanderlustapi.api.dto.itinerary.ItineraryDto;

public interface ItineraryService {

    ItinerariesDto getAllItineraries();

    ItinerariesDto getItinerariesByUserId(String userId);

    ItinerariesDto getItinerariesByUserEmail(String userEmail);

    ItineraryDto saveItinerary(String userId, ItineraryDto itineraryDto);

    ItineraryDto saveItineraryByUserEmail(String userEmail, ItineraryDto itineraryDto);

    ItineraryDto getItineraryById(String id);

    Boolean deleteItinerary(String id);
}
