package com.exalt.library.controllers;

import com.exalt.library.controllers.dto.ReserveRequest;
import com.exalt.library.models.SingletonLibrary;
import com.exalt.library.models.reservation.Reservation;
import com.exalt.library.services.BorrowerServices;
import com.exalt.library.services.LibraryItemServices;
import com.exalt.library.services.ReservationServices;
import com.exalt.library.services.borrowtype.InHandBorrowStrategyService;
import com.exalt.library.services.borrowtype.OnlineBorrowStrategyService;
import com.exalt.library.services.factory.BorrowStrategyFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * a reservation controller that gets a request from the client, does a specific job then returns the response
 * @author Mohammad Rimawi
 */
@RestController
@RequestMapping("/api/reservations")
public class ReservationController  {
    ReservationServices reservationServices = initializeReservationServices();

    /**
     * a method for initializing the reservation services, where we can use the logic it delivers
     * @return the reservation services
     */
    private ReservationServices initializeReservationServices() {
        ReservationServices reservationServices = new ReservationServices();

        BorrowStrategyFactory factory = new BorrowStrategyFactory();
        factory.setInHandStrategy(new InHandBorrowStrategyService());
        factory.setOnlineStrategy(new OnlineBorrowStrategyService());

        reservationServices.setLibraryItemOperations(new LibraryItemServices());
        reservationServices.setBorrowerOperations(new BorrowerServices());
        reservationServices.setBorrowStrategyFactory(factory);

        return reservationServices;
    }

    /**
     * A method for fetching all the reservations
     * exists on: http://localhost:8080/api/reservations
     * @return
     */
    @GetMapping
    public List<Reservation> findAll() {
        List<Reservation> reservations = SingletonLibrary.getInstance().getReservations();
        reservations.forEach(reservation -> reservationServices.checkAndHandleExpiration(reservations, reservation));
        return reservations;
    }

    /**
     * a method for fetching a specific reservation based on a specific id
     * exists on: http://localhost:8080/api/reservations/{id}
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Reservation findById(@PathVariable int id) {
        List<Reservation> reservations = SingletonLibrary.getInstance().getReservations();
        Reservation reservation = reservationServices.findReservationById(reservations, id);
        reservationServices.checkAndHandleExpiration(reservations, reservation);
        return reservation;
    }

    /**
     * a method for fetching active reservations
     * exists on: http://localhost:8080/api/reservations/active?borrowerId={id}&itemId={id}
     * @param borrowerId
     * @param itemId
     * @return
     */
    @GetMapping("/active")
    public Reservation findActive(@RequestParam int borrowerId, @RequestParam int itemId) {
        return reservationServices.findActiveReservation(SingletonLibrary.getInstance().getReservations(), borrowerId, itemId);
    }

    /**
     * a method for creating a serving request for a reservation
     * exists on: http://localhost:8080/api/reservations
     * @param request
     * @return
     */
    @PostMapping
    public Reservation reserve(@RequestBody ReserveRequest request) {
        return reservationServices.reserve(
                SingletonLibrary.getInstance().getReservations(),
                SingletonLibrary.getInstance().getLibraryItems(),
                SingletonLibrary.getInstance().getBorrowers(),
                request.getBorrowerId(),
                request.getItemId());
    }
}
