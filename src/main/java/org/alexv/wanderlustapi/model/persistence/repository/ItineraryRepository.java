package org.alexv.wanderlustapi.model.persistence.repository;

import org.alexv.wanderlustapi.model.persistence.entity.Itinerary;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ItineraryRepository extends MongoRepository<Itinerary, String> {

    List<Itinerary> findItinerariesByUserEmail(String userEmail);
    List<Itinerary> findItinerariesByUserId(String userId);
}
