package com.exalt.library.services.strategies;

import com.exalt.library.models.libraryitems.LibraryItem;
import com.exalt.library.models.reservation.Reservation;

/**
 * a strategy interface representing the different ways library items can be borrowed
 * @author Mohammad Rimawi
 */
public interface BorrowStrategy {

    /**
     * a method for activating an existing reservation - i.e. handing the item
     * over to the borrower and marking the reservation as active
     * @param reservation the reservation to activate
     * @return the activated reservation
     */
    Reservation activate(Reservation reservation);

    /**
     * The borrowing strategy for a type of borrowing
     * @param reservation the reservation to activate
     * @return the activated reservation
     */
    Reservation borrow(Reservation reservation);

    /**
     * a method for returning the item
     * @param libraryItem
     */
    void returnItem(LibraryItem libraryItem);
}
