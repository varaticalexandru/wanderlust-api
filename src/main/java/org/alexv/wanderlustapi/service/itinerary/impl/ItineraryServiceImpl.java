package org.alexv.wanderlustapi.service.itinerary.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.alexv.wanderlustapi.api.dto.itinerary.ItinerariesDto;
import org.alexv.wanderlustapi.api.dto.itinerary.ItineraryDto;
import org.alexv.wanderlustapi.api.exception.ResourceNotFoundException;
import org.alexv.wanderlustapi.model.persistence.entity.Itinerary;
import org.alexv.wanderlustapi.model.persistence.entity.UserInfo;
import org.alexv.wanderlustapi.model.persistence.repository.ItineraryRepository;
import org.alexv.wanderlustapi.model.persistence.repository.UserInfoRepository;
import org.alexv.wanderlustapi.service.itinerary.ItineraryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ItineraryServiceImpl implements ItineraryService {

    ItineraryRepository itineraryRepository;
    ModelMapper modelMapper;
    private final UserInfoRepository userInfoRepository;

    public ItinerariesDto getAllItineraries() {

        List<Itinerary> itineraries = itineraryRepository.findAll();
        List<ItineraryDto> itineraryDtos = itineraries.stream()
                .map(itinerary -> modelMapper.map(itinerary, ItineraryDto.class))
                .toList();

        return ItinerariesDto.builder().itineraries(itineraryDtos).build();
    }

    public ItinerariesDto getItinerariesByUserId(String userId) {

        List<Itinerary> itineraries = itineraryRepository.findItinerariesByUserId(userId);
        List<ItineraryDto> itineraryDtos = itineraries.stream()
                .map(itinerary -> modelMapper.map(itinerary, ItineraryDto.class))
                .toList();

        return ItinerariesDto.builder().itineraries(itineraryDtos).build();
    }

    public ItinerariesDto getItinerariesByUserEmail(String userEmail) {

        List<Itinerary> itineraries = itineraryRepository.findItinerariesByUserEmail(userEmail);
        List<ItineraryDto> itineraryDtos = itineraries.stream()
                .map(itinerary -> modelMapper.map(itinerary, ItineraryDto.class))
                .toList();

        return ItinerariesDto.builder().itineraries(itineraryDtos).build();
    }


    public ItineraryDto saveItinerary(String userId, ItineraryDto itinerary) {

        UserInfo user = userInfoRepository.findById(userId).orElseThrow(() -> new ResourceAccessException("User not found: " + userId));

        Itinerary itineraryEntity = modelMapper.map(itinerary, Itinerary.class);

        itineraryEntity.setUser(user);
        Itinerary savedItinerary = itineraryRepository.save(itineraryEntity);

        List<Itinerary> userItineraries = user.getItineraries();
        userItineraries.add(savedItinerary);
        user.setItineraries(userItineraries);
        UserInfo savedUser = userInfoRepository.save(user);

        ItineraryDto itineraryDto = modelMapper.map(savedItinerary, ItineraryDto.class);
        return itineraryDto;
    }

    public ItineraryDto saveItineraryByUserEmail(String userEmail, ItineraryDto itinerary) {

        UserInfo user = userInfoRepository.findByEmail(userEmail).orElseThrow(() -> new ResourceNotFoundException("User not found: " + userEmail));

        Itinerary itineraryEntity = modelMapper.map(itinerary, Itinerary.class);

        itineraryEntity.setUser(user);
        Itinerary savedItinerary = itineraryRepository.save(itineraryEntity);

        List<Itinerary> userItineraries = user.getItineraries();
        userItineraries.add(savedItinerary);
        user.setItineraries(userItineraries);
        UserInfo savedUser = userInfoRepository.save(user);

        ItineraryDto itineraryDto = modelMapper.map(savedItinerary, ItineraryDto.class);
        return itineraryDto;
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
