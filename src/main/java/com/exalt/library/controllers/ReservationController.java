package com.exalt.library.controllers;

import com.exalt.library.controllers.dto.ReserveRequest;
import com.exalt.library.models.Borrower;
import com.exalt.library.models.SingletonLibrary;
import com.exalt.library.models.libraryitems.LibraryItem;
import com.exalt.library.models.reservation.Reservation;
import com.exalt.library.services.BorrowerServices;
import com.exalt.library.services.LibraryItemServices;
import com.exalt.library.services.ReservationServices;
import com.exalt.library.services.borrowtype.InHandBorrowStrategyService;
import com.exalt.library.services.borrowtype.OnlineBorrowStrategyService;
import com.exalt.library.services.factory.BorrowStrategyFactory;
import com.exalt.library.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
     * exists on: /api/reservations
     * @return
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> findAll() {
        List<Reservation> reservations = SingletonLibrary.getInstance().getReservations();
        reservations.forEach(reservation -> reservationServices.checkAndHandleExpiration(reservations, reservation));
        return ResponseEntity.ok(ApiResponse.success(200, reservations));
    }

    /**
     * a method for fetching a specific reservation based on a specific id
     * exists on: /api/reservations/{id}
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable int id) {
        List<Reservation> reservations = SingletonLibrary.getInstance().getReservations();
        Reservation reservation = reservationServices.findReservationById(reservations, id);
        reservationServices.checkAndHandleExpiration(reservations, reservation);
        return ResponseEntity.ok(ApiResponse.success(200, reservation));
    }

    /**
     * a method for fetching active reservations
     * exists on: /api/reservations/active?borrowerId={id}&itemId={id}
     * @param borrowerId
     * @param itemId
     * @return
     */
    @GetMapping("/active")
    public ResponseEntity<Map<String, Object>> findActive(@RequestParam int borrowerId, @RequestParam int itemId) {
        Reservation reservation = reservationServices.findActiveReservation(SingletonLibrary.getInstance().getReservations(), borrowerId, itemId);
        return ResponseEntity.ok(ApiResponse.success(200, reservation));
    }

    /**
     * a method for creating a serving request for a reservation
     * exists on: /api/reservations
     * @param request
     * @return
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> reserve(@RequestBody ReserveRequest request) {
        Reservation reservation = reservationServices.reserve(
                SingletonLibrary.getInstance().getReservations(),
                SingletonLibrary.getInstance().getLibraryItems(),
                SingletonLibrary.getInstance().getBorrowers(),
                request.getBorrowerId(),
                request.getItemId());
        return ResponseEntity.status(201).body(ApiResponse.success(201, reservation));
    }

    /**
     * a method for returning an item after being reserved
     * exists on: /api/reservations/return
     * @param request
     * @return
     */
    @PostMapping("/return")
    public ResponseEntity<Map<String, Object>> returnItem(@RequestBody ReserveRequest request) {
        List<Reservation> reservations = SingletonLibrary.getInstance().getReservations();
        LibraryItem item = reservationServices.checkForLibraryItem(SingletonLibrary.getInstance().getLibraryItems(), request.getItemId());
        Borrower borrower = reservationServices.checkForBorrower(SingletonLibrary.getInstance().getBorrowers(), request.getBorrowerId());

        boolean closed = reservationServices.returnItem(reservations, item, borrower);
        return ResponseEntity.ok(ApiResponse.success(200, Map.of("returned", closed)));
    }

    /**
     * a method for claiming a specific reservation
     * exists on: /api/reservations/{id}/claim
     * @param id
     * @return
     */
    @PostMapping("/{id}/claim")
    public ResponseEntity<Map<String, Object>> claim(@PathVariable int id) {
        Reservation reservation = reservationServices.findReservationById(SingletonLibrary.getInstance().getReservations(), id);
        Reservation claimed = reservationServices.claimReservation(reservation);
        return ResponseEntity.ok(ApiResponse.success(200, claimed));
    }

    /**
     * a method for canceling a specific reservation
     * exists on: /api/reservations/{id}
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> cancel(@PathVariable int id) {
        List<Reservation> reservations = SingletonLibrary.getInstance().getReservations();
        Reservation reservation = reservationServices.findReservationById(reservations, id);
        boolean cancelled = reservationServices.cancelReservation(reservations, reservation);
        return ResponseEntity.ok(ApiResponse.success(200, Map.of("cancelled", cancelled)));
    }
}
