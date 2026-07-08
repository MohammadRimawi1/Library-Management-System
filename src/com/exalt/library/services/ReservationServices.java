package com.exalt.library.services;

import com.exalt.library.services.operations.BorrowerOperations;
import com.exalt.library.services.operations.LibraryItemOperations;
import com.exalt.library.services.operations.ReservationOperations;
import com.exalt.library.services.strategies.BorrowStrategy;
import com.exalt.library.services.factory.BorrowStrategyFactory;
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
     * Finds an active reservation matching the borrower and the item ids
     * @param reservations
     * @param borrowerId
     * @param itemId
     * @return the active reservation
     * @throws ReservationNotFoundException if it doesn't exist
     */
    @Override
    public Reservation findActiveReservation(List<Reservation> reservations, int borrowerId, int itemId) {
        return reservations.stream()
                .filter(reservation -> reservation.getStatus() == ReservationStatus.ACTIVE &&
                        reservation.getBorrower().getId() == borrowerId &&
                        reservation.getLibraryItem().getId() == itemId)
                .findFirst()
                .orElseThrow(() -> new ReservationNotFoundException("Active reservation doesn't exist"));
    }

    /**
     * a method for checking if the library item exists
     * @param items
     * @param itemId
     * @return a library item if found
     */
    @Override
    public LibraryItem checkForLibraryItem(List<LibraryItem> items, int itemId) {
        return libraryItemOperations.findItemById(items, itemId);
    }

    /**
     * a method for checking for the borrower if he exists or not
     * @param borrowers
     * @param borrowerId
     * @return borrower if found
     */
    @Override
    public Borrower checkForBorrower(List<Borrower> borrowers, int borrowerId) {
        return borrowerOperations.findBorrowerById(borrowers, borrowerId);
    }

    /**
     * a method used to let a borrower reserve a specific item.
     * if the item is available, the reservation is activated immediately (this replaces the old "loan" path)
     * if not, the reservation is queued as WAITING until the item comes back
     * @param reservations
     * @param items
     * @param borrowers
     * @param borrowerId
     * @param itemId
     * @return the created reservation
     */
    @Override
    public Reservation reserve(List<Reservation> reservations, List<LibraryItem> items, List<Borrower> borrowers, int borrowerId, int itemId) {
        LibraryItem item = checkForLibraryItem(items, itemId);
        Borrower borrower = checkForBorrower(borrowers, borrowerId);

        if (item instanceof OnlineItem) {
            throw new IllegalArgumentException("Online items cannot be reserved — they are always available");
        }

        Reservation reservation = new Reservation();
        reservation.setLibraryItem(item);
        reservation.setBorrower(borrower);
        reservation.setStartDate(new Date());

        reservations.add(reservation);

        BorrowStrategy strategy = borrowStrategyFactory.resolve(item);
        if (item.isAvailable()) {
            strategy.activate(reservation);
        }

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

    /**
     * a method to close an active reservation so the item becomes available again,
     * and promote the next waiting reservation (if any) into READY status
     * @param reservation
     * @param libraryItem
     * @param reservations
     */
    @Override
    public void closeReservation(Reservation reservation, LibraryItem libraryItem, List<Reservation> reservations) {
        reservation.setStatus(ReservationStatus.RETURNED);
        borrowStrategyFactory.resolve(libraryItem).returnItem(libraryItem);

        Reservation next = findNextWaitingReservationOrNull(reservations, libraryItem);
        if (next != null) {
            BorrowStrategy strategy = borrowStrategyFactory.resolve(libraryItem);
            strategy.activate(next);
            next.setAvailableFrom(new Date());
        }
    }

    /**
     * a method which returns a borrowed item and closes its active reservation
     * @param reservations
     * @param libraryItem
     * @param borrower
     * @return true if the reservation was closed
     * @throws ReservationNotFoundException if no active reservation is found
     */
    @Override
    public boolean returnItem(List<Reservation> reservations, LibraryItem libraryItem, Borrower borrower) {
        Reservation reservation = findActiveReservation(reservations, borrower.getId(), libraryItem.getId());
        closeReservation(reservation, libraryItem, reservations);

        return true;
    }

    /**
     * a method for a borrower to claim their READY reservation - actually picking up
     * the item that was being held for them
     */
    @Override
    public Reservation claimReservation(Reservation reservation) {
        if (reservation.getStatus() != ReservationStatus.READY) {
            throw new IllegalStateException("Reservation is not ready to be claimed");
        }

        borrowStrategyFactory.resolve(reservation.getLibraryItem()).activate(reservation);
        return reservation;
    }

    /**
     * helper so findNextWaitingReservation's throwing behavior doesn't blow up closeReservation
     * when there's simply nobody waiting
     */
    private Reservation findNextWaitingReservationOrNull(List<Reservation> reservations, LibraryItem item) {
        try {
            return findNextWaitingReservation(reservations, item);
        } catch (ReservationNotFoundException e) {
            return null;
        }
    }
}
