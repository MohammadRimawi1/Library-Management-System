package com.exalt.library.services;

import com.exalt.library.services.operations.BorrowerOperations;
import com.exalt.library.services.operations.LibraryItemOperations;
import com.exalt.library.services.operations.ReservationOperations;
import com.exalt.library.services.strategies.BorrowStrategy;
import com.exalt.library.services.factory.BorrowStrategyFactory;
import com.exalt.library.services.strategies.Reservable;
import com.exalt.library.exceptions.ReservationNotFoundException;
import com.exalt.library.models.Borrower;
import com.exalt.library.models.libraryitems.LibraryItem;
import com.exalt.library.models.libraryitems.onlineitems.OnlineItem;
import com.exalt.library.models.reservation.Reservation;
import com.exalt.library.models.reservation.ReservationStatus;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * a class representing the services for the reservations
 * implements the interface ReservationOperations
 * @author Mohammad Rimawi
 */
public class ReservationServices implements ReservationOperations {
    LibraryItemOperations libraryItemOperations; // Defines the library item operations
    BorrowerOperations borrowerOperations; // Defines the borrower operations
    BorrowStrategyFactory borrowStrategyFactory; // Defines the borrower strategy factory

    /**
     * a default constructor for instantiation
     */
    public ReservationServices() { }

//    ==== SETTERS ====
    /**
     * a method for setting the library item operations in the reservation services
     * @param libraryItemOperations
     */
    public void setLibraryItemOperations(LibraryItemOperations libraryItemOperations) {
        this.libraryItemOperations = libraryItemOperations;
    }

    /**
     * a method for setting the borrower operations in the reservation services
     * @param borrowerOperations
     */
    public void setBorrowerOperations(BorrowerOperations borrowerOperations) {
        this.borrowerOperations = borrowerOperations;
    }

    /**
     * a method for setting the borrower strategy factory in the reservation services
     * @param borrowStrategyFactory
     */
    public void setBorrowStrategyFactory(BorrowStrategyFactory borrowStrategyFactory) {
        this.borrowStrategyFactory = borrowStrategyFactory;
    }
//    ==== SETTERS ====

    /**
     * a method used to find a specific reservation based on its id
     *  implemented inside ReservationServices
     * @param reservations
     * @param id
     * @return a reservation
     * @throws com.exalt.library.exceptions.ReservationNotFoundException if no reservation was found
     */
    @Override
    public Reservation findReservationById(List<Reservation> reservations, int id) {
        return reservations.stream() // this turns the reservations into a stream
                .filter(reservation -> reservation.getId() == id)// This filters the stream and gets what matches the condition
                .findFirst() // This returns an optional describing the first element of the stream
                .orElseThrow(() -> new ReservationNotFoundException("reservation was not found!")); // This should throw an exception if there was reservation found
    }

    /**
     * a method used to let a borrower reserve a specific item regardless of its availability
     * @param reservations
     * @param items
     * @param borrowers
     * @param borrowerId
     * @param itemId
     * @return
     */
    @Override
    public Reservation reserve(List<Reservation> reservations, List<LibraryItem> items, List<Borrower> borrowers, int borrowerId, int itemId) {
        LibraryItem item = libraryItemOperations.findItemById(items, itemId);
        Borrower borrower = borrowerOperations.findBorrowerById(borrowers, borrowerId);

        if (item instanceof OnlineItem) {
            throw new IllegalArgumentException("Online items cannot be reserved — they are always available");
        }

        Reservation reservation = new Reservation();
        reservation.setLibraryItem(item);
        reservation.setBorrower(borrower);
        reservation.setStartDate(new Date());

        if (item.isAvailable()) {
            BorrowStrategy strategy = borrowStrategyFactory.resolve(item);
            if (strategy instanceof Reservable reservableStrategy) {
                reservableStrategy.holdItem(item);
            }
        }

        reservations.add(reservation);
        return reservation;
    }

    /**
     * a method for checking the next waiting reservation for an item
     * @param reservations
     * @param item
     * @return
     */
    @Override
    public Reservation findNextWaitingReservation(List<Reservation> reservations, LibraryItem item) {
        return reservations.stream()
                .filter(reservation -> reservation.getLibraryItem().getId() == item.getId() &&
                        reservation.getStatus() == ReservationStatus.WAITING)
                .min(Comparator.comparing(Reservation::getStartDate))
                .orElseThrow(() -> new ReservationNotFoundException("No waiting reservation for this item"));
    }

    /**
     * a method for handling the expiration of a reservation
     * @param reservations
     * @param reservation
     */
    @Override
    public void checkAndHandleExpiration(List<Reservation> reservations, Reservation reservation) {
        if (reservation.getStatus() == ReservationStatus.READY
                && reservation.getEndDate() != null
                && new Date().after(reservation.getEndDate())) {

            reservation.setStatus(ReservationStatus.EXPIRED);
        }
    }

    /**
     * a method for canceling a specific reservation and deleting it
     * @param reservations
     * @param reservation
     * @return
     */
    @Override
    public boolean cancelReservation(List<Reservation> reservations, Reservation reservation) {
        reservation.setStatus(ReservationStatus.CANCELLED);
        return reservations.remove(reservation);
    }
}
