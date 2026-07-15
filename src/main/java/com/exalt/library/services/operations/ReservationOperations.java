package com.exalt.library.services.operations;

import com.exalt.library.models.Borrower;
import com.exalt.library.exceptions.BorrowerNotFoundException;
import com.exalt.library.exceptions.ItemNotFoundException;
import com.exalt.library.exceptions.ReservationNotFoundException;
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
     * @throws ReservationNotFoundException if no reservation was found
     */
    Reservation findReservationById(List<Reservation> reservations, int id);

    /**
     * Finds an active reservation matching the borrower and the item ids
     * implemented inside ReservationServices
     * @param reservations
     * @param borrowerId
     * @param itemId
     * @return the active reservation
     * @throws ReservationNotFoundException if it doesn't exist
     */
    Reservation findActiveReservation(List<Reservation> reservations, int borrowerId, int itemId);

    /**
     * a method for checking if the item exists
     * implemented inside ReservationServices
     * @param items
     * @param itemId
     * @return an item if found
     * @throws ItemNotFoundException
     */
    LibraryItem checkForLibraryItem(List<LibraryItem> items, int itemId);

    /**
     * a method for checking for the borrower if he exists or not
     * implemented inside ReservationServices
     * @param borrowers
     * @param borrowerId
     * @return borrower if found
     * @throws BorrowerNotFoundException if not found
     */
    Borrower checkForBorrower(List<Borrower> borrowers, int borrowerId);


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

    /**
     * a method to close an active reservation so the item becomes available again,
     * and hold it for the next waiting reservation (if any)
     * @param reservation
     * @param libraryItem
     * @param reservations
     */
    void closeReservation(Reservation reservation, LibraryItem libraryItem, List<Reservation> reservations);

    /**
     * a method which returns a borrowed item and closes its active reservation
     * implemented inside ReservationServices
     * @param reservations
     * @param libraryItem
     * @param borrower
     * @return true if the reservation was closed
     * @throws ReservationNotFoundException if no active reservation is found
     */
    boolean returnItem(List<Reservation> reservations, LibraryItem libraryItem, Borrower borrower);

    /**
     * a method for a borrower to claim their READY reservation
     * the item that was being held for them, activating it
     * @param reservation the READY reservation being claimed
     * @return the now-ACTIVE reservation
     * @throws IllegalStateException if the reservation isn't in READY status
     */
    Reservation claimReservation(Reservation reservation);
}
