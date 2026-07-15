package com.exalt.library.models;

import com.exalt.library.models.libraryitems.LibraryItem;
import com.exalt.library.models.reservation.Reservation;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * a class representing the library itself
 * Contains a list of library items, borrowers, and loans
 * @author Mohammad Rimawi
 */
@Component
public class SingletonLibrary {
    private List<LibraryItem> items; // Defines a list containing library items
    private List<Borrower> borrowers; // Defines a list containing the borrowers
    private List<Reservation> reservations; // Defines a list containing the reservations

    /**
     * Constructor Injection
     */
    public SingletonLibrary() {
        items = new java.util.ArrayList<>();
        borrowers = new java.util.ArrayList<>();
        reservations = new java.util.ArrayList<>();
    }

//    ==== GETTERS ====
    /**
     * a method for getting the library items
     * @return
     */
    public List<LibraryItem> getLibraryItems() {
        return items;
    }

    /**
     * a method for returning the borrowers
     * @return
     */
    public List<Borrower> getBorrowers() {
        return borrowers;
    }

    /**
     * a method for returning all the reservations
     * @return a list of loans
     */
    public List<Reservation> getReservations() {
        return reservations;
    }
    //    ==== GETTERS ====

//    ==== SETTERS ====
    /**
     * a method for setting the items
     * @param items
     */
    public void setLibraryItems(List<LibraryItem> items) {
        this.items = items;
    }

    /**
     * a method for setting the borrowers
     * @param borrowers
     */
    public void setBorrowers(List<Borrower> borrowers) {
        this.borrowers = borrowers;
    }

    /**
     * a method for setting the reservations
     * @param reservations
     */
    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
    //    ==== SETTERS ====
}
