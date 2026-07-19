package com.exalt.library.controllers;

import com.exalt.library.controllers.dto.ReserveDTO;
import com.exalt.library.models.Borrower;
import com.exalt.library.models.libraryitems.LibraryItem;
import com.exalt.library.models.reservation.Reservation;
import com.exalt.library.models.reservation.ReservationStatus;
import com.exalt.library.services.ReservationServices;
import com.exalt.library.util.ApiResponse;
import jakarta.validation.Valid;
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
    private final ReservationServices reservationServices; // defines the reservation services

    /**
     * constructor injection
     * @param reservationServices
     */
    public ReservationController(ReservationServices reservationServices) {
        this.reservationServices = reservationServices;
    }

    /**
     * A method for fetching all the reservations
     * exists on: /api/reservations
     * @return
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> findAll() {
        return ResponseEntity.ok(ApiResponse.success(200, reservationServices.getAllReservations()));
    }

    /**
     * a method for fetching a specific reservation based on a specific id
     * exists on: /api/reservations/{id}
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable String id) {
        Reservation reservation = reservationServices.findReservationById(id);
        reservationServices.checkAndHandleExpiration(reservation);
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
    public ResponseEntity<Map<String, Object>> findActive(@RequestParam String borrowerId, @RequestParam String itemId) {
        Reservation reservation = reservationServices.findActiveReservation(borrowerId, itemId);
        return ResponseEntity.ok(ApiResponse.success(200, reservation));
    }

    /**
     * A method for fetching the reservations for a specific borrower
     * @param borrowerId
     * @return
     */
    @GetMapping("/borrower/{borrowerId}")
    public ResponseEntity<Map<String, Object>> findReservationByBorrower(@PathVariable String borrowerId) {
        List<Reservation> reservations = reservationServices.findReservationsByBorrower(borrowerId);
        return ResponseEntity.ok(ApiResponse.success(200, reservations));
    }

    /**
     * A method for fetching the reservations with a specific status
     * @param status
     * @return
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<Map<String, Object>> findReservationsByStatus(@PathVariable String status) {
        ReservationStatus reservationStatus = ReservationStatus.valueOf(status.toUpperCase());
        List<Reservation> reservations = reservationServices.findReservationsByStatus(reservationStatus);
        return ResponseEntity.ok(ApiResponse.success(200, reservations));
    }

    /**
     * a method for creating a serving request for a reservation
     * exists on: /api/reservations
     * @param reserveDTO
     * @return
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> reserve(@RequestBody ReserveDTO reserveDTO) {
        Reservation reservation = reservationServices.reserve(reserveDTO);
        return ResponseEntity.status(201).body(ApiResponse.success(201, reservation));
    }

    /**
     * a method for returning an item after being reserved
     * exists on: /api/reservations/return
     * @param reserveDTO
     * @return
     */
    @PostMapping("/return")
    public ResponseEntity<Map<String, Object>> returnItem(@RequestBody ReserveDTO reserveDTO) {
        boolean closed = reservationServices.returnItem(reserveDTO);
        return ResponseEntity.ok(ApiResponse.success(200, Map.of("returned", closed)));
    }

    /**
     * a method for claiming a specific reservation
     * exists on: /api/reservations/{id}/claim
     * @param id
     * @return
     */
    @PostMapping("/{id}/claim")
    public ResponseEntity<Map<String, Object>> claim(@PathVariable String id) {
        Reservation reservation = reservationServices.findReservationById(id);
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
    public ResponseEntity<Map<String, Object>> cancel(@PathVariable String id) {
        Reservation reservation = reservationServices.findReservationById(id);
        boolean cancelled = reservationServices.cancelReservation(reservation);
        return ResponseEntity.ok(ApiResponse.success(200, Map.of("cancelled", cancelled)));
    }
}
