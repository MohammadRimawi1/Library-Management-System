package com.exalt.library.services.operations;

import com.exalt.library.models.Borrower;
import com.exalt.library.models.libraryitems.LibraryItem;
import com.exalt.library.models.reservation.Reservation;

import java.util.List;

/**
 * an interface representing the operations for a Reservation in a library
 * implemented inside ReservationServices
 * @author Mohammad Rimawi
 */
public interface ReservationOperations {
    /**
     * a method used to find a specific reservation based on its id
     *  implemented inside ReservationServices
     * @param reservations
     * @param id
     * @return a reservation
     * @throws com.exalt.library.exceptions.ReservationNotFoundException if no reservation was found
     */
    Reservation findReservationById(List<Reservation> reservations, int id);

    /**
     * a method used to let a borrower reserve a specific item regardless of its availability
     * implemented inside ReservationServices
     * @param reservations
     * @param items
     * @param borrowers
     * @param borrowerId
     * @param itemId
     * @return
     */
    Reservation reserve(List<Reservation> reservations, List<LibraryItem> items, List<Borrower> borrowers, int borrowerId, int itemId);

    /**
     * a method for checking the next waiting reservation for an item
     * implemented inside ReservationServices
     * @param reservations
     * @param item
     * @return
     */
    Reservation findNextWaitingReservation(List<Reservation> reservations, LibraryItem item);

    /**
     * a method for handling the expiration of a reservation
     * implemented inside ReservationServices
     * @param reservations
     * @param reservation
     */
    void checkAndHandleExpiration(List<Reservation> reservations, Reservation reservation);

    /**
     * a method for canceling a specific reservation and deleting it
     * implemented inside ReservationServices
     * @param reservations
     * @param reservation
     * @return
     */
    boolean cancelReservation(List<Reservation> reservations, Reservation reservation);
}
