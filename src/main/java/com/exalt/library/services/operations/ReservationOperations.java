package com.exalt.library.services.operations;

import com.exalt.library.dto.ReserveDTO;
import com.exalt.library.models.Borrower;
import com.exalt.library.exceptions.BorrowerNotFoundException;
import com.exalt.library.exceptions.ItemNotFoundException;
import com.exalt.library.exceptions.ReservationNotFoundException;
import com.exalt.library.models.libraryitems.LibraryItem;
import com.exalt.library.models.reservation.Reservation;
import com.exalt.library.models.reservation.ReservationStatus;

import java.util.List;

/**
 * an interface representing the operations for a Reservation in a library
 * implemented inside ReservationServices
 * @author Mohammad Rimawi
 */
public interface ReservationOperations {
    /**
     * a method for returning all the reservations
     * @return
     */
    List<Reservation> getAllReservations();

    /**
     * a method used to find a specific reservation based on its id
     *  implemented inside ReservationServices
     * @param id
     * @return a reservation
     * @throws ReservationNotFoundException if no reservation was found
     */
    Reservation findReservationById(String id);

    /**
     * Finds an active reservation matching the borrower and the item ids
     * implemented inside ReservationServices
     * @param borrowerId
     * @param itemId
     * @return the active reservation
     * @throws ReservationNotFoundException if it doesn't exist
     */
    Reservation findActiveReservation(String borrowerId, String itemId);

    /**
     * a method for checking if the item exists
     * implemented inside ReservationServices
     * @param itemId
     * @return an item if found
     * @throws ItemNotFoundException
     */
    LibraryItem checkForLibraryItem(String itemId);

    /**
     * a method for checking for the borrower if he exists or not
     * implemented inside ReservationServices
     * @param borrowerId
     * @return borrower if found
     * @throws BorrowerNotFoundException if not found
     */
    Borrower checkForBorrower(String borrowerId);


    /**
     * a method used to let a borrower reserve a specific item regardless of its availability
     * implemented inside ReservationServices
     * @param reserveDTO
     * @return
     */
    Reservation reserve(ReserveDTO reserveDTO);

    /**
     * a method for checking the next waiting reservation for an item
     * implemented inside ReservationServices
     * @param item
     * @return
     */
    Reservation findNextWaitingReservation( LibraryItem item);

    /**
     * a method for handling the expiration of a reservation
     * implemented inside ReservationServices
     * @param reservation
     */
    void checkAndHandleExpiration(Reservation reservation);

    /**
     * a method for canceling a specific reservation and deleting it
     * implemented inside ReservationServices
     * @param reservation
     * @return
     */
    boolean cancelReservation(Reservation reservation);

    /**
     * a method to close an active reservation so the item becomes available again,
     * and hold it for the next waiting reservation (if any)
     * @param reservation
     * @param libraryItem
     */
    void closeReservation(Reservation reservation, LibraryItem libraryItem);

    /**
     * a method which returns a borrowed item and closes its active reservation
     * implemented inside ReservationServices
     * @param reserveDTO
     * @return true if the reservation was closed
     * @throws ReservationNotFoundException if no active reservation is found
     */
    boolean returnItem(ReserveDTO reserveDTO);

    /**
     * a method for a borrower to claim their READY reservation
     * the item that was being held for them, activating it
     * @param reservation the READY reservation being claimed
     * @return the now-ACTIVE reservation
     * @throws IllegalStateException if the reservation isn't in READY status
     */
    Reservation claimReservation(Reservation reservation);

    /**
     * A method for retrieving reservations for a specific borrower
     * @param borrowerId
     * @return
     */
    List<Reservation> findReservationsByBorrower(String borrowerId);

    /**
     * a method for retrieving reservations with a specific status
     * @param status
     * @return
     */
    List<Reservation> findReservationsByStatus(ReservationStatus status);
}
