package com.exalt.library.repositories;

import com.exalt.library.models.reservation.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReservationRepository extends MongoRepository<Reservation, String> {
}
