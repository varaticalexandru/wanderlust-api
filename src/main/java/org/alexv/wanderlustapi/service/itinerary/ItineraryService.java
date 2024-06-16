package org.alexv.wanderlustapi.service.itinerary;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.alexv.wanderlustapi.api.dto.itinerary.ItinerariesDto;
import org.alexv.wanderlustapi.api.dto.itinerary.ItineraryDto;
import org.alexv.wanderlustapi.model.persistence.entity.Itinerary;
import org.alexv.wanderlustapi.model.persistence.repository.ItineraryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ItineraryService {

    ItineraryRepository itineraryRepository;
    ModelMapper modelMapper;

    public ItinerariesDto getAllItineraries() {

        List<Itinerary> itineraries = itineraryRepository.findAll();
        List<ItineraryDto> itineraryDtos = itineraries.stream()
                .map(itinerary -> modelMapper.map(itinerary, ItineraryDto.class))
                .toList();

        return ItinerariesDto.builder().itineraries(itineraryDtos).build();
    }

    public ItineraryDto saveItinerary(ItineraryDto itineraryDto) {

        Itinerary itinerary = modelMapper.map(itineraryDto, Itinerary.class);
        Itinerary savedItinerary = itineraryRepository.save(itinerary);

        return modelMapper.map(savedItinerary, ItineraryDto.class);
    }

    public ItineraryDto getItineraryById(String id) {

        Itinerary itinerary = itineraryRepository.findById(id).orElseThrow();

        return modelMapper.map(itinerary, ItineraryDto.class);
    }

    public Boolean deleteItinerary(String id) {
        itineraryRepository.deleteById(id);

        return true;
    }
}
