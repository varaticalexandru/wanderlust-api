package org.alexv.wanderlustapi.model.persistence.repository;

import org.alexv.wanderlustapi.model.persistence.entity.Itinerary;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItineraryRepository extends MongoRepository<Itinerary, String> {
}
